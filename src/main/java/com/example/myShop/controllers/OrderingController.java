package com.example.myShop.controllers;

import com.example.myShop.models.Goods;
import com.example.myShop.models.Ordering;
import com.example.myShop.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class OrderingController {
    private final OrderingService orderingService;
    private final GoodsService goodsService;
    private final ReceivingService receivingService;
    private final PaymentService paymentService;
    private final ClientService clientService;
    private final StatusService statusService;

    public OrderingController(OrderingService orderingService, GoodsService goodsService, ReceivingService receivingService, PaymentService paymentService, ClientService clientService, StatusService statusService) {
        this.orderingService = orderingService;
        this.goodsService = goodsService;
        this.receivingService = receivingService;
        this.paymentService = paymentService;
        this.clientService = clientService;
        this.statusService = statusService;
    }

    @GetMapping("/order/{id}")
    public String ordering(@PathVariable Integer id, Principal principal, Model model){
        if(principal == null) return "login";

        Goods good = goodsService.getGoodsById(id);
        model.addAttribute("good", good);
        model.addAttribute("receivings", receivingService.receivingList());
        model.addAttribute("payments", paymentService.paymentList());

        return "order";
    }

    @PostMapping("/order/{id}")
    public String createOrder(@PathVariable("id") Integer id, Ordering order, Principal principal, Model model){
        Goods goods = goodsService.getGoodsById(id);

        // count must be less or equal of good's availability
        int count = order.getCount();
        if(count > goods.getAvailability()){
            model.addAttribute("message", "Wrong count");
            return "order";
        }

        int price = goodsService.getGoodsById(id).getPrice() * count;

        //Reduce availability for good
        goods.decAvailability(count);
        goodsService.saveGoods(goods);

        order.setGoodsID(id);
        order.setClientID(clientService.getClientByPrincipal(principal).getClient_ID());
        order.setPrice(price);
        order.setStatusName(statusService.getPrimaryStatus().getStatus_Name());

        orderingService.saveOrder(order);

        return "redirect:/client";
    }

    @GetMapping("/order-info/{id}")
    public String orderInfo(@PathVariable Integer id, Model model, Principal principal){
        Ordering order = orderingService.getOrderById(id);

        model.addAttribute("order", order);
        model.addAttribute("good", goodsService.getGoodsById(order.getGoodsID()));
        model.addAttribute("receive", receivingService.getReceivingById(order.getReceiveID()));
        model.addAttribute("client", clientService.getClientByPrincipal(principal));

        return "order-info";
    }
}
