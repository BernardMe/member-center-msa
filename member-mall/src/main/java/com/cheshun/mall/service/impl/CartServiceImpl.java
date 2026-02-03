package com.cheshun.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.domain.entity.CartItem;
import com.cheshun.mall.domain.entity.GoodsInfos;
import com.cheshun.mall.domain.mapper.GoodsInfosMapper;
import com.cheshun.mall.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements CartService {

    private static final String CART_PREFIX = "cart:";
    private static final long CART_EXPIRE_DAYS = 7;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GoodsInfosMapper productMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
        public void addToCart(String cartKey, Integer productId, Integer quantity) {
            // 查询商品信息
            GoodsInfos product = productMapper.selectById(productId);
            if (product == null) {
                throw new RuntimeException("商品不存在");
            }

            // 构造购物车项
            CartItem cartItem = new CartItem();
            cartItem.setGoodsId(product.getGoodsId());
            cartItem.setProductName(product.getProductName());
            cartItem.setPrice(product.getRetailPrice());
            cartItem.setImage(product.getMainImage());

            // 检查是否已存在该商品
            String itemKey = cartKey + ":" + productId;
            Object redisFacetedSearchObj = redisTemplate.opsForValue().get(itemKey);
            CartItem existingItem = JSONObject.parseObject(String.valueOf(redisFacetedSearchObj), new TypeReference<CartItem>(){});

        if (existingItem != null) {
            // 已存在则增加数量
            cartItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItem.setSelected(existingItem.getSelected());
        } else {
            cartItem.setQuantity(quantity);
        }

        // 保存到Redis
        redisTemplate.opsForValue().set(itemKey, JSONObject.toJSONString(cartItem), CART_EXPIRE_DAYS, TimeUnit.DAYS);

        // 将商品ID添加到购物车集合中，用于快速获取购物车中的所有商品
        redisTemplate.opsForSet().add(cartKey + ":ids", productId.toString());
        redisTemplate.expire(cartKey + ":ids", CART_EXPIRE_DAYS, TimeUnit.DAYS);
    }

    @Override
    public void removeFromCart(String cartKey, Integer productId) {
        String itemKey = cartKey + ":" + productId;
        redisTemplate.delete(itemKey);
        redisTemplate.opsForSet().remove(cartKey + ":ids", productId.toString());
    }

    @Override
    public void updateCartItemQuantity(String cartKey, Integer productId, Integer quantity) {
        String itemKey = cartKey + ":" + productId;
        Object redisFacetedSearchObj = redisTemplate.opsForValue().get(itemKey);
        CartItem cartItem = JSONObject.parseObject(String.valueOf(redisFacetedSearchObj), new TypeReference<CartItem>(){});
        //CartItem cartItem = (CartItem) redisTemplate.opsForValue().get(itemKey);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            redisTemplate.opsForValue().set(itemKey, cartItem, CART_EXPIRE_DAYS, TimeUnit.DAYS);
        }
    }

    @Override
    public void updateCartItemSelected(String cartKey, Integer productId, Boolean selected) {
        String itemKey = cartKey + ":" + productId;
        Object redisFacetedSearchObj = redisTemplate.opsForValue().get(itemKey);
        CartItem cartItem = JSONObject.parseObject(String.valueOf(redisFacetedSearchObj), new TypeReference<CartItem>(){});
        //CartItem cartItem = (CartItem) redisTemplate.opsForValue().get(itemKey);
        if (cartItem != null) {
            cartItem.setSelected(selected);
            redisTemplate.opsForValue().set(itemKey, cartItem, CART_EXPIRE_DAYS, TimeUnit.DAYS);
        }
    }

    @Override
    public Result getCartDetail(String cartKey) {
        Map<String, Object> map = new HashMap<>();

        map.put("cartItems", getCartItems(cartKey));
        map.put("totalCount", getCartItemCount(cartKey));
        map.put("totalPrice", getCartTotalPrice(cartKey));
        return ResultUtil.result(map);
    }

    @Override
    public List<CartItem> getCartItems(String cartKey) {
        List<CartItem> cartItems = new ArrayList<>();

        Set<Object> productIds = redisTemplate.opsForSet().members(cartKey + ":ids");
        if (productIds != null) {
            for (Object id : productIds) {
                String itemKey = cartKey + ":" + id;
                Object redisFacetedSearchObj = redisTemplate.opsForValue().get(itemKey);
                CartItem cartItem = JSONObject.parseObject(String.valueOf(redisFacetedSearchObj), new TypeReference<CartItem>(){});
                //CartItem cartItem = (CartItem) redisTemplate.opsForValue().get(itemKey);
                if (cartItem != null) {
                    cartItems.add(cartItem);
                }
            }
        }

        return cartItems;
    }

    @Override
    public Integer getCartItemCount(String cartKey) {
        Integer count = 0;
        List<CartItem> cartItems = getCartItems(cartKey);
        for (CartItem item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    @Override
    public Double getCartTotalPrice(String cartKey) {
        Double totalPrice = 0.0;
        List<CartItem> cartItems = getCartItems(cartKey);
        for (CartItem item : cartItems) {
            if (item.getSelected()) {
                totalPrice += item.getTotalPrice().doubleValue();
            }
        }
        return totalPrice;
    }

    @Override
    public void clearCart(String cartKey) {
        Set<Object> productIds = redisTemplate.opsForSet().members(cartKey + ":ids");
        if (productIds != null) {
            for (Object id : productIds) {
                String itemKey = cartKey + ":" + id;
                redisTemplate.delete(itemKey);
            }
        }
        redisTemplate.delete(cartKey + ":ids");
    }

    @Override
    public void mergeCart(String tempCartKey, String userCartKey) {
        List<CartItem> tempCartItems = getCartItems(tempCartKey);
        if (!tempCartItems.isEmpty()) {
            for (CartItem item : tempCartItems) {
                // 将临时购物车商品添加到用户购物车
                addToCart(userCartKey, item.getGoodsId(), item.getQuantity());
                // 更新选中状态
                updateCartItemSelected(userCartKey, item.getGoodsId(), item.getSelected());
            }
            // 清空临时购物车
            clearCart(tempCartKey);
        }
    }

    @Override
    public String generateCartKey(Long userId, String sessionId) {
        if (userId != null) {
            return CART_PREFIX + "user:" + userId;
        } else if (!StringUtils.isEmpty(sessionId)) {
            return CART_PREFIX + "session:" + sessionId;
        }
        throw new RuntimeException("无法生成购物车key");
    }
}

