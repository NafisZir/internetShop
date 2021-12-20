package com.example.myShop.controller;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.ProducerService;
import com.example.myShop.service.UserService;
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
@RequestMapping(path = "goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProducerService producerService;

    @GetMapping()
    public String getGoods(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
        model.addAttribute("goods", goodsService.getGoods(name));
        model.addAttribute("client", userService.getUserByPrincipal(principal));
        return "goods";
    }

    @GetMapping("/{goodId}")
    public String get(@PathVariable(name = "goodId") Integer id, Model model, Principal principal) {
        Goods good = goodsService.get(id);
        model.addAttribute("good", good);
        model.addAttribute("goodCategory", categoryService
                .get(good.getCategoryID()));
        model.addAttribute("client", userService.getUserByPrincipal(principal));
        return "good-info";
    }

    @GetMapping("/new")
    public String newGood(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("producers", producerService.getProducers());

        return "create-good";
    }

    @PostMapping()
    public String create(Goods goods){
        goodsService.create(goods);
        return "redirect:/goods/admin";
    }

    @GetMapping("/edit/{goodId}")
    public String edit(@PathVariable("goodId") Integer id, Model model) {
        Goods good = goodsService.get(id);
        model.addAttribute("good", good);
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("producers", producerService.getProducers());
        model.addAttribute("goodCategory", categoryService
                .get(good.getCategoryID()));
        return "edit-good";
    }

    @PatchMapping ("/{goodId}")
    public String update(@PathVariable("goodId") Integer id, Goods goods){
        goods.setId(id);
        goodsService.update(goods);
        return "redirect:/goods/admin";
    }

    @DeleteMapping("/{goodId}")
    public String delete(@PathVariable("goodId") Integer id, Model model){
        goodsService.delete(id);
        return "redirect:/goods/admin";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("goods", goodsService.getGoods(name));

        return "admin-goods";
    }
}
