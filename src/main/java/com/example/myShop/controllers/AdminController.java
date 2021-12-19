package com.example.myShop.controllers;

import com.example.myShop.models.*;
import com.example.myShop.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final ClientService clientService;
    private final GoodsService goodsService;
    private final OrderingService orderingService;
    private final CategoryService categoryService;
    private final ProducerService producerService;
    private final StatusService statusService;
    private final PaymentService paymentService;
    private final ReceivingService receivingService;
    private boolean message = false;

    public AdminController(ClientService clientService, GoodsService goodsService, OrderingService orderingService, CategoryService categoryService, ProducerService producerService, StatusService statusService, PaymentService paymentService, ReceivingService receivingService) {
        this.clientService = clientService;
        this.goodsService = goodsService;
        this.orderingService = orderingService;
        this.categoryService = categoryService;
        this.producerService = producerService;
        this.statusService = statusService;
        this.paymentService = paymentService;
        this.receivingService = receivingService;
    }

    @GetMapping("")
    public String admin(){
        return "admin";
    }

    @GetMapping("/clients")
    public String getClients(Model model){
        model.addAttribute("clientList", clientService.clientList());
        return "admin-clients";
    }

    @PostMapping("/delete-client/{id}")
    public String deleteClient(@PathVariable("id") Integer id){
        clientService.deleteClientById(id);
        return "redirect:/admin";
    }

    @GetMapping("/goods")
    public String getGoods(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("goods", goodsService.listGoods(name));
        if(message){
            model.addAttribute("message", "This good has order now!");
            message = false;
        }
        return "admin-goods";
    }

    @PostMapping("/delete-good/{id}")
    public String deleteGood(@PathVariable("id") Integer id, Model model){
        List<Ordering> orderingList = orderingService.getOrderListByGoods_ID(id);
        if(orderingList.isEmpty()){
            goodsService.deleteGoods(id);
        } else {
            message = true;
        }
        return "redirect:/admin/goods";
    }

    @GetMapping("/edit-good/{id}")
    public String editGood(@PathVariable("id") Integer id, Model model) {
        Goods good = goodsService.getGoodsById(id);
        model.addAttribute("good", good);
        model.addAttribute("categories", categoryService.getCategoryList());
        model.addAttribute("producers", producerService.getProducerList());
        model.addAttribute("goodCategory", categoryService
                .getCategoryById(good.getCategoryID()));
        return "edit-good";
    }

    @PostMapping("/editGood/{id}")
    public String editGoodPost(@PathVariable("id") Integer id, Goods goods){
        goods.setGoods_ID(id);
        goodsService.saveGoods(goods);
        return "redirect:/admin/goods";
    }

    @GetMapping("/create-good")
    public String createGood(Model model){
        model.addAttribute("categories", categoryService.getCategoryList());
        model.addAttribute("producers", producerService.getProducerList());

        return "create-good";
    }

    @PostMapping("/createGood")
    public String createGoodPost(Goods goods){
        goodsService.saveGoods(goods);
        return "redirect:/admin/goods";
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        model.addAttribute("orders", orderingService.getOrdersList());
        model.addAttribute("clients", clientService);
        model.addAttribute("goods", goodsService);
        return "admin-orderings";
    }

    @PostMapping("/delete-order/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderingService.deleteOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/edit-order-status/{id}")
    public String editOrderStatus(@PathVariable("id") Integer id, Model model){
        model.addAttribute("order", orderingService.getOrderById(id));
        model.addAttribute("statuses", statusService.getStatusList());
        return "edit-order-status";
    }

    @PostMapping("/editOrderStatus/{id}")
    public String editOrderStatusPost(@PathVariable("id") Integer id, Status status){
        Ordering order2 = orderingService.getOrderById(id);
        order2.setStatusName(status.getStatus_Name());
        orderingService.saveOrder(order2);
        return "redirect:/admin/orders";
    }

    @GetMapping("/categories")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getCategoryList());
        if(message){
            model.addAttribute("message", "This category has goods!");
            message = false;
        }
        return "admin-categories";
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, Model model){
        List<Goods> goodsList = goodsService.getGoodsListByCategory(id);
        if(goodsList.isEmpty()){
            categoryService.deleteCategory(id);
        } else {
            message = true;
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/create-category")
    public String createCategory(Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategories(@PathVariable("id") Integer id, Model model){
        model.addAttribute(categoryService.getCategoryById(id));
        return "edit-category";
    }

    @PostMapping("/editCategory/{id}")
    public String editCategoryPost(@PathVariable("id") Integer id, Category category){
        Category category1 = categoryService.getCategoryById(id);
        category1.setDescr(category.getDescr());
        categoryService.saveCategory(category1);
        return "redirect:/admin/categories";
    }

    @GetMapping("/payments")
    public String getPayments(Model model){
        model.addAttribute("payments", paymentService.paymentList());
        if(message){
            model.addAttribute("message", message);
            message = false;
        }
        return "admin-payments";
    }

    @PostMapping("/create-payment")
    public String createPayment(Payment payment){
        paymentService.savePayment(payment);
        return "redirect:/admin/payments";
    }

    @PostMapping("/delete-payment/{id}")
    public String deletePayment(@PathVariable("id") String id){
        List<Ordering> orderingList = orderingService.getOrderListByPayMethod(id);

        if(orderingList.isEmpty()){
            paymentService.deletePayment(id);
        } else {
            message = true;
        }

        return "redirect:/admin/payments";
    }

    @GetMapping("/receiving")
    public String getReceiving(Model model){
        model.addAttribute("receiving", receivingService.receivingList());
        if(message){
            model.addAttribute("message", message);
            message = false;
        }
        return "admin-receiving";
    }

    @GetMapping("/edit-receive/{id}")
    public String editReceive(@PathVariable("id") Integer id, Model model){
        model.addAttribute("receive", receivingService.getReceivingById(id));
        return "edit-receive";
    }

    @PostMapping("/editReceive/{id}")
    public String editReceivePost(@PathVariable("id") Integer id, Receiving receiving){
        Receiving receiving1 = receivingService.getReceivingById(id);

        receiving1.setAddress(receiving.getAddress());
        receiving1.setReceive_Method(receiving.getReceive_Method());

        receivingService.saveReceive(receiving1);

        return "redirect:/admin/receiving";
    }

    @PostMapping("/create-receive")
    public String createReceive(Receiving receiving){
        receivingService.saveReceive(receiving);
        return "redirect:/admin/receiving";
    }

    @PostMapping("/delete-receive/{id}")
    public String deleteReceive(@PathVariable("id") Integer id){
        List<Ordering> orderingList = orderingService.getOrderListByReceiveId(id);

        if(orderingList.isEmpty()){
            receivingService.deleteReceive(id);
        } else {
            message = true;
        }

        return "redirect:/admin/receiving";
    }

    @GetMapping("/statuses")
    public String getStatuses(Model model){
        model.addAttribute("statuses", statusService.getStatusList());
        if(message){
            model.addAttribute("message", message);
            message = false;
        }
        return "admin-statuses";
    }

    @PostMapping("/create-status")
    public String createStatus(Status status){
        statusService.saveStatus(status);
        return "redirect:/admin/statuses";
    }

    @PostMapping("/delete-status/{id}")
    public String deleteStatus(@PathVariable("id") String id) {
        List<Ordering> orderingList = orderingService.getOrderListByStatusName(id);

        if (orderingList.isEmpty()) {
            statusService.deleteStatus(id);
        } else {
            message = true;
        }

        return "redirect:/admin/statuses";
    }
}
