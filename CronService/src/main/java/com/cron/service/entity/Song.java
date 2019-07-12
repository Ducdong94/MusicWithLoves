package com.cron.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Song {
    @Id
    private String id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String title;
    private String code;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String artistsNames;
    private String link;
    private String thumbnail;
    private int duration;
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Source> source;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "song_cate", joinColumns = {@JoinColumn(name = "song_id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id")})
    private Set<Category> songCategories = new HashSet<Category>();
    @NotNull
    private long time;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "song_playlist", joinColumns = {@JoinColumn(name = "song_id")}, inverseJoinColumns = {
            @JoinColumn(name = "playlist_id")})
    private Set<Playlist> songPlaylist = new HashSet<Playlist>();

    public Song(String id, String title, String code, String artistsNames, String link, String thumbnail, int duration) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.artistsNames = artistsNames;
        this.link = link;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.time = System.currentTimeMillis();
    }

    public Song() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Category> getSongCategories() {
        return songCategories;
    }

    public void setSongCategories(Set<Category> songCategories) {
        this.songCategories = songCategories;
    }

    public Set<Playlist> getSongPlaylist() {
        return songPlaylist;
    }

    public void setSongPlaylist(Set<Playlist> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }
}
