package com.example.myShop.controller;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Status;
import com.example.myShop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "orders")
public class OrderController {
    private final OrderService orderService;
    private final GoodsService goodsService;
    private final ReceivingService receivingService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final StatusService statusService;

    @GetMapping("/new/{goodId}")
    public String newOrder(@PathVariable(name = "goodId") Integer id, Principal principal, Model model){
        if(principal == null) return "login";

        Goods good = goodsService.get(id);
        model.addAttribute("good", good);
        model.addAttribute("receivings", receivingService.getReceivings());
        model.addAttribute("payments", paymentService.getPayments());

        return "order";
    }

    @PostMapping("/{goodId}")
    public String create(@PathVariable(name = "goodId") Integer id, Order order, Model model){
        String message = orderService.create(order, id);

        if(message.equals("Wrong count")){
            model.addAttribute("message", message);
            return "goods";
        }

        return "redirect:/users";
    }

    @GetMapping("/{orderId}")
    public String get(@PathVariable(name = "orderId") Integer id, Model model, Principal principal){
        Order order = orderService.get(id);

        model.addAttribute("order", order);
        model.addAttribute("good", goodsService.get(order.getGoodsID()));
        model.addAttribute("receive", receivingService.get(order.getReceiveID()));
        model.addAttribute("client", userService.getUserByPrincipal(principal));

        return "order-info";
    }

    @PatchMapping("{orderId}")
    public String update(@PathVariable(name = "orderId") Integer id){
        return "order-info";
    }

    @GetMapping("/edit-order-status/{orderId}")
    public String editOrderStatus(@PathVariable("orderId") Integer id, Model model){
        model.addAttribute("order", orderService.get(id));
        model.addAttribute("statuses", statusService.getStatuses());
        return "edit-order-status";
    }

    @PatchMapping("/update-order-status/{orderId}")
    public String updateOrderStatus(@PathVariable("orderId") Integer id, Status status){
        Order order2 = orderService.get(id);
        order2.setStatus(status.getStatus());
        orderService.update(order2);
        return "redirect:/orders/admin";
    }

    @DeleteMapping("{orderId}")
    public String delete(@PathVariable(name = "orderId") Integer id){
        orderService.delete(id);
        return "redirect:/orders/admin";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("clients", userService);
        model.addAttribute("goods", goodsService);
        return "admin-orderings";
    }
}
