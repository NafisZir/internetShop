package com.example.myShop.controller;

import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;
}
