package com.cheshun.mall.service;

import com.cheshun.common.result.Result;
import com.cheshun.mall.domain.entity.CartItem;

import java.util.List;

/**
 * 购物车Service接口
 * @author wangzhuo
 */
public  interface CartService {


    /**
     * 添加商品到购物车
     */
    void addToCart(String cartKey, Integer productId, Integer quantity);

    /**
     * 从购物车删除商品
     */
    void removeFromCart(String cartKey, Integer productId);

    /**
     * 更新购物车商品数量
     */
    void updateCartItemQuantity(String cartKey, Integer productId, Integer quantity);

    /**
     * 更新购物车商品选中状态
     */
    void updateCartItemSelected(String cartKey, Integer productId, Boolean selected);

    /**
     * 获取购物车详情API
     */
    Result getCartDetail(String cartKey);

    /**
     * 获取购物车所有商品
     */
    List<CartItem> getCartItems(String cartKey);

    /**
     * 获取购物车商品总数
     */
    Integer getCartItemCount(String cartKey);

    /**
     * 获取购物车总价
     */
    Double getCartTotalPrice(String cartKey);

    /**
     * 清空购物车
     */
    void clearCart(String cartKey);

    /**
     * 合并购物车（登录时）
     */
    void mergeCart(String tempCartKey, String userCartKey);

    /**
     * 生成购物车key
     */
    String generateCartKey(Long userId, String sessionId);

}
