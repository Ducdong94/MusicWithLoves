package com.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.service.entity.Playlist;

import java.util.List;

@Repository
public interface PlayListRepsitory extends JpaRepository<Playlist, String> {
    List<Playlist> findByName(String name);
    List<Playlist> findByIdLike(String name);
}
