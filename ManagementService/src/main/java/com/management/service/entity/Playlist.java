package com.management.service.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Playlist {
    @Id
    private String id;
    private String image;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "songPlaylist")
    private List<Song> playlistSong = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getPlaylistSong() {
        return playlistSong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlaylistSong(List<Song> playlistSong) {
        this.playlistSong = playlistSong;
    }
}
