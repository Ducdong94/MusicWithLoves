package com.management.service.controller;

import com.management.service.entity.Playlist;
import com.management.service.model.response.Result;
import com.management.service.repository.PlayListRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/xhr")
public class PlaylistController {
    @Autowired
    PlayListRepsitory playListRepsitory;

    @GetMapping(value = "/playlist/{id}")
    public Result getPlaylistDetail(@PathVariable String id) {
        try {
            Optional<Playlist> opt = playListRepsitory.findById(id);
            if (!opt.isPresent()) {
                return new Result(404, "PLaylist not found!!", null);
            }
            Playlist playlist = opt.get();
            return new Result(200, "SUCCESSFUL", playlist);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new Result(-1, e.getMessage(), null);
        }
    }
}
