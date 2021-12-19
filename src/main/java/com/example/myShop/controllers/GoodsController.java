package com.example.myShop.controllers;

import com.example.myShop.models.Goods;
import com.example.myShop.services.CategoryService;
import com.example.myShop.services.ClientService;
import com.example.myShop.services.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class GoodsController {
    private final GoodsService goodsService;
    private final CategoryService categoryService;
    private final ClientService clientService;

    public GoodsController(GoodsService goodsService, CategoryService categoryService, ClientService clientService) {
        this.goodsService = goodsService;
        this.categoryService = categoryService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String goods(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
        model.addAttribute("goods", goodsService.listGoods(name));
        model.addAttribute("client", clientService.getClientByPrincipal(principal));
        return "goods";
    }

    @GetMapping("/good/{id}")
    public String goodsInfo(@PathVariable Integer id, Model model, Principal principal) {
        Goods good = goodsService.getGoodsById(id);
        model.addAttribute("good", good);
        model.addAttribute("goodCategory", categoryService
                .getCategoryById(good.getCategoryID()));
        model.addAttribute("client", clientService.getClientByPrincipal(principal));
        return "good-info";
    }
}
