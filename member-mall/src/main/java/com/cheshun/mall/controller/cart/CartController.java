package com.cheshun.mall.controller.cart;

import com.cheshun.common.result.Result;
import com.cheshun.mall.service.CartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Api(tags = "购物车业务接口")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 获取当前登录用户ID（实际项目中应从Session或Token中获取）
     */
    private Long getCurrentUserId(HttpSession session) {
        // 实际项目中这里应该从Session或JWT Token中获取用户ID
        return (Long) session.getAttribute("userId");
    }

    /**
     * 获取购物车Key
     */
    private String getCartKey(HttpSession session) {
        Long userId = getCurrentUserId(session);
        String sessionId = session.getId();
        return cartService.generateCartKey(userId, sessionId);
    }

    /**
     * 查看购物车
     */
    @GetMapping("/list")
    public Result cartList(Model model, HttpSession session) {
        String cartKey = getCartKey(session);
        Result result = cartService.getCartDetail(cartKey);
        return result;
    }

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addToCart(@RequestParam Integer productId,
                                         @RequestParam Integer quantity,
                                         HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String cartKey = getCartKey(session);
            cartService.addToCart(cartKey, productId, quantity);
            result.put("success", true);
            result.put("totalCount", cartService.getCartItemCount(cartKey));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 更新购物车商品数量
     */
    @PostMapping("/update/quantity")
    @ResponseBody
    public Map<String, Object> updateQuantity(@RequestParam Integer productId,
                                              @RequestParam Integer quantity,
                                              HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String cartKey = getCartKey(session);
            cartService.updateCartItemQuantity(cartKey, productId, quantity);
            result.put("success", true);
            result.put("totalPrice", cartService.getCartTotalPrice(cartKey));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 更新商品选中状态
     */
    @PostMapping("/update/selected")
    @ResponseBody
    public Map<String, Object> updateSelected(@RequestParam Integer productId,
                                              @RequestParam Boolean selected,
                                              HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String cartKey = getCartKey(session);
            cartService.updateCartItemSelected(cartKey, productId, selected);
            result.put("success", true);
            result.put("totalPrice", cartService.getCartTotalPrice(cartKey));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 删除购物车商品
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteItem(@RequestParam Integer productId,
                                          HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String cartKey = getCartKey(session);
            cartService.removeFromCart(cartKey, productId);
            result.put("success", true);
            result.put("totalCount", cartService.getCartItemCount(cartKey));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 清空购物车
     */
    @PostMapping("/clear")
    @ResponseBody
    public Map<String, Object> clearCart(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            String cartKey = getCartKey(session);
            cartService.clearCart(cartKey);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}
