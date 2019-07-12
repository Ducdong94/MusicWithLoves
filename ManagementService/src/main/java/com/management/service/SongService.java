package com.management.service;

import java.util.List;

import com.management.service.entity.Song;

public interface SongService {
	
	void deleteSong(String id);
	
	Song createSong(Song song);
	
	Song updateSong(Song song);
	
	Song viewSong(String id);
	
	List<Song> getAllSong();
}
