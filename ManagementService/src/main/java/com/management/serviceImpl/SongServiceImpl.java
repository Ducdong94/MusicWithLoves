package com.management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.service.SongService;
import com.management.service.entity.Song;
import com.management.service.repository.SongRepsitory;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	public SongRepsitory songRepository;

	@Override
	public void deleteSong(String id) {
		songRepository.deleteById(id);
	}

	@Override
	public Song updateSong(Song song) {
		return songRepository.save(song);
	}

	@Override
	public Song viewSong(String id) {
		Song song = songRepository.getOne(id);
		return song;
	}

	@Override	
	public Song createSong(Song song) {
		return songRepository.save(song);
	}

	@Override
	public List<Song> getAllSong() {
		return songRepository.findAll();
	}
}
