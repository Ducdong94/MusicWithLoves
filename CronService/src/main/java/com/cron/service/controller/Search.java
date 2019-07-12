package com.cron.service.controller;

import com.cron.service.entity.Song;
import com.cron.service.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Scope("request")
public class Search {
    @Autowired
    SongRepository songRepository;

    @GetMapping("/search")
    public List<Song> searchSongName(@RequestParam(value = "key", required = true) String key){
        List<Song> respon = songRepository.findAllByTitleContaining(key);

        return respon;
    }
}
