package com.cron.service.controller;

import com.cron.service.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    SongRepository songRepository;

    @GetMapping("/check")
    public long test() {
        System.out.println("check");
        return songRepository.count();
    }
}
