package com.management.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.service.PlayListService;
import com.management.service.entity.Playlist;
import com.management.service.model.response.Result;

@RestController
@RequestMapping("/admin/play-list")
public class AdminPlayListController {
	@Autowired
	private PlayListService playListService;

	@PostMapping("/add-song/{idPlayList}/{idSong}")
	public Result addSongToPlayList(@PathVariable String idPlayList, @PathVariable String idSong) {
		Result result = playListService.addSong(idPlayList, idSong);
		return result;
	}

	@GetMapping("/edit/{idPlayList}")
	public Result editPlayList(@PathVariable String idPlayList) {
		Result result = playListService.getPlayListById(idPlayList);
		return result;
	}

	@PutMapping("/edit")
	public Result editPlayList(@RequestBody Playlist playList) {
		Result result = playListService.update(playList);
		return result;
	}

	@PostMapping("/create")
	public Result createPlayList(@RequestBody Playlist playList) {
		Result result = playListService.createPlayList(playList);
		return result;
	}

	@GetMapping("/delete/{idPlayList}")
	public Result deletePlayListPlayList(@PathVariable String idPlayList) {
		Result result = playListService.deletePlayList(idPlayList);
		return result;
	}
}
