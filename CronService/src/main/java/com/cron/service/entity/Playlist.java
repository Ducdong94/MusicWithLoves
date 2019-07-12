package com.cron.service.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Playlist {
    @Id
    private String id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String image;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "songPlaylist")
    private Set<Song> playlistSong = new HashSet<>();

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Song> getPlaylistSong() {
        return playlistSong;
    }

    public void setPlaylistSong(Set<Song> playlistSong) {
        this.playlistSong = playlistSong;
    }
}
