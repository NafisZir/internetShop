package com.example.myShop.controllers;

import com.example.myShop.models.Client;
import com.example.myShop.models.Ordering;
import com.example.myShop.services.ClientService;
import com.example.myShop.services.GoodsService;
import com.example.myShop.services.OrderingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;
    private final OrderingService orderingService;
    private final GoodsService goodsService;

    public ClientController(ClientService clientService, OrderingService orderingService, GoodsService goodsService) {
        this.clientService = clientService;
        this.orderingService = orderingService;
        this.goodsService = goodsService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createClient(Client client, Model model) {
        if(!clientService.createClient(client)){
            model.addAttribute("errorMessage", "The same user is already existed");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }


    @GetMapping("/client")
    public String client(Principal principal, Model model){
        Client client = clientService.getClientByPrincipal(principal);
        List<Ordering> orderList = orderingService.getOrderListByClient_ID(client.getClient_ID());

        int id;
        String name;
        List<OrderClient> orderClientList = new ArrayList<>();
        for(Ordering order: orderList){
            id = order.getOrder_ID();
            name = goodsService.getGoodsById(order.getGoodsID()).getName();
            orderClientList.add(new OrderClient(name, id));
        }

        model.addAttribute("client", client);
        model.addAttribute("orderClient", orderClientList);

        return "client";
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
}
