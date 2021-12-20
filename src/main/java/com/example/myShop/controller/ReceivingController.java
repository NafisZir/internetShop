package com.example.myShop.controller;

import com.example.myShop.domain.entity.Receiving;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "receivings")
@RequiredArgsConstructor
public class ReceivingController {
    private final ReceivingService receivingService;

    @GetMapping("/admin")
    public String getReceivings(Model model){
        model.addAttribute("receiving", receivingService.getReceivings());

        return "admin-receiving";
    }

    @PostMapping()
    public String create(Receiving receiving){
        receivingService.create(receiving);
        return "redirect:/receivings/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("receive", receivingService.get(id));
        return "edit-receive";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, Receiving receiving){
        Receiving receiving1 = receivingService.get(id);

        receiving1.setAddress(receiving.getAddress());
        receiving1.setReceiveMethod(receiving.getReceiveMethod());

        receivingService.create(receiving1);

        return "redirect:/receivings/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        receivingService.delete(id);
        return "redirect:/receivings/admin";
    }
}
