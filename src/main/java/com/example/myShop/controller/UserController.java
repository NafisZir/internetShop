package com.example.myShop.controller;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.User;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final GoodsService goodsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String registration() {
        return "registration";
    }

    @PostMapping()
    public String create(User user, Model model) {
        if(!userService.create(user)){
            model.addAttribute("errorMessage", "The same user is already existed");
            return "registration";
        }
        return "redirect:users/login";
    }

    @GetMapping()
    public String get(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        List<Order> orderList = orderService.getOrdersByClientID(user.getId());

        int id;
        String name;
        List<OrderClient> orderClientList = new ArrayList<>();
        for(Order order: orderList){
            id = order.getId();
            name = goodsService.get(order.getGoodsID()).getName();
            orderClientList.add(new OrderClient(name, id));
        }

        model.addAttribute("client", user);
        model.addAttribute("orderClient", orderClientList);

        return "client";
    }

    @PatchMapping("/{userId}")
    public String update(@PathVariable(name = "userId") Integer id){
        return "client";
    }

    @DeleteMapping("{userId}")
    public String delete(@PathVariable(name = "userId") Integer id){
        userService.delete(id);
        return "redirect:/users/admin";
    }

    public class OrderClient{
        private String goodName;
        private int orderID;

        public OrderClient(String name, int id){
            this.goodName = name;
            this.orderID = id;
        }

        public String getGoodName() {
            return goodName;
        }

        public int getOrderID() {
            return orderID;
        }
    }

    @GetMapping("/admin")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "admin-clients";
    }
}
