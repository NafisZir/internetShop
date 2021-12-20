package com.example.myShop.controller;

import com.example.myShop.domain.entity.Status;
import com.example.myShop.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "statuses")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/admin")
    public String getStatuses(Model model){
        model.addAttribute("statuses", statusService.getStatuses());
        return "admin-statuses";
    }

    @PostMapping()
    public String create(Status status){
        statusService.create(status);
        return "redirect:/statuses/admin";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") Integer id, Status status){
        return "redirect:/statuses/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        statusService.delete(id);
        return "redirect:/statuses/admin";
    }
}
