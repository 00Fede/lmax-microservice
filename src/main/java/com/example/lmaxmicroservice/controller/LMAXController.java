package com.example.lmaxmicroservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.lmaxmicroservice.event.LmaxEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class LMAXController {

    @Autowired
    LmaxEventProducer producer;

    @RequestMapping("/process")
    @ResponseBody
    public String process(@RequestParam int value) {
        producer.produce(value);
        return "Processed: " + value;
    }
}
