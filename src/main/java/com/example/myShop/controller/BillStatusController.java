package com.example.myShop.controller;

/**
 * @author nafis
 * @since 12.02.2022
 */

import com.example.myShop.service.BillStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "bill-statuses")
public class BillStatusController {
    private final BillStatusService billStatusService;

    @GetMapping()
    public List<String> getAll(){
        return billStatusService.getAll();
    }
}
