package com.example.myShop.controller;

import com.example.myShop.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nafis
 * @since 05.02.2022
 */

@RestController
@AllArgsConstructor
@RequestMapping(path = "statuses")
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/index")
    public List<String> index(){
        return statusService.getAll();
    }
}
