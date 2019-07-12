package com.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.service.entity.Song;
@Repository
public interface SongRepsitory extends JpaRepository<Song, String> {
	
}
