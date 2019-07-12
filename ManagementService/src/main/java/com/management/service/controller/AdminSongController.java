package com.management.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.service.SongService;
import com.management.service.entity.Song;
import com.management.service.model.response.Result;

@RestController
@RequestMapping("/admin/song")
public class AdminSongController {
	
	@Autowired
	private SongService adminService;
	
	@PutMapping("/update/{id}")
	public Result update(@RequestBody Song song, @PathVariable String id) {
		song.setId(id);
		System.out.println("update: " + song.toString());
		adminService.updateSong(song);
		Result result = new Result(200, "OK", song);
		return result;
	}

	@PostMapping("/create")
	public Result create(@RequestBody Song song) {
		System.out.println("create: " + song.toString());
		adminService.createSong(song);
		Result result = new Result(200, "OK", song);
		return result;
	}

	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable String id) {
		System.out.println("delete song: " + id);
		adminService.deleteSong(id);
		Result result = new Result(200, "OK", id);
		return result;
	}
	
	@GetMapping("/getAll")
	public Result getAll() {
		List<Song> lstSong=adminService.getAllSong();
		Result result = new Result(200, "OK", lstSong);
		return result;
	}
}
