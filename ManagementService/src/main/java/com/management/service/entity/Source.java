package com.management.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Source {
    @Id
    private String id;
    @NotNull
    private String quality;
    @NotNull
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Song sourceSong;

    public Source() {
    }

    public Source(String id, String quality, String path) {
        this.id = id;
        this.quality = quality;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song getSong() {
        return sourceSong;
    }

    public void setSong(Song song) {
        this.sourceSong = song;
    }
}
