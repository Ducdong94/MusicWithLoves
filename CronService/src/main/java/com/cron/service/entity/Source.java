package com.cron.service.entity;

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
    @ManyToOne
    @JoinColumn
    private Song song;

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
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
