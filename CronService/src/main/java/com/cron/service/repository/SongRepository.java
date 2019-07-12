package com.cron.service.repository;

import com.cron.service.entity.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, String> {

    List<Song> findAllByTitleContaining(String key);
}
