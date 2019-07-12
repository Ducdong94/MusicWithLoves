package com.management.service.controller;

import com.management.service.entity.Category;
import com.management.service.entity.Playlist;
import com.management.service.entity.Song;
import com.management.service.repository.CategoryRepository;
import com.management.service.repository.PlayListRepsitory;
import com.management.service.repository.SongRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RenderController {
    @Autowired
    PlayListRepsitory playListRepsitory;
    @Autowired
    SongRepsitory songRepsitory;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String renderHome(Model model) {
        try {
            Playlist playlist = playListRepsitory.findById("https://m.zingmp3.vn/album/Top-100-Bai-Hat-Nhac-Tre-Hay-Nhat-Various-Artists/ZWZB969E.html").orElse(null);
            if (playlist == null) {
                return "error";
            }
            List<Song> songs = playlist.getPlaylistSong();
            model.addAttribute("top15_left", songs.subList(0, 5));
            model.addAttribute("top15_mid", songs.subList(6, 10));
            model.addAttribute("top15_right", songs.subList(11, 15));
            List<Playlist> playlists = playListRepsitory.findAll();
            model.addAttribute("playlist1", playlists.get(0));
            model.addAttribute("playlist2", playlists.get(1));
            model.addAttribute("playlist3", playlists.get(2));
            model.addAttribute("playlist4", playlists.get(3));
            model.addAttribute("playlist5", playlists.get(4));
            model.addAttribute("playlist6", playlists.get(5));
            model.addAttribute("playlist7", playlists.get(6));
            model.addAttribute("playlist8", playlists.get(7));
            model.addAttribute("playlist9", playlists.get(8));
            model.addAttribute("playlist10", playlists.get(9));

            return "index";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    @GetMapping("/playlists")
    public String renderPlaylists(Model model) {
        try {
            model.addAttribute("playlists", playListRepsitory.findByIdLike("https%"));
            return "playlists";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    @GetMapping("/detail")
    public String renderPlaylistDetail(Model model, @RequestParam String id) {
        try {
            Playlist playlist = playListRepsitory.findById(id).orElse(null);
            if (playlist == null) {
                return "error";
            }
            model.addAttribute("playlist", playlist);
            return "songs";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    @GetMapping("/gift")
    public String renderGift(Model model, @RequestParam String songid, @RequestParam String note) {
        try {
            Song song = songRepsitory.findById(songid).orElse(null);
            if (song == null) {
                return "error";
            }
            model.addAttribute("song", song);
            model.addAttribute("note", note);
            return "gift";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }
}
