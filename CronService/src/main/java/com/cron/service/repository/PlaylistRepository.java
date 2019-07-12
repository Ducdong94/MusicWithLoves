package com.cron.service.repository;

import com.cron.service.entity.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, String> {

    Playlist findByName(String name);
    boolean existsByName(String name);
}
