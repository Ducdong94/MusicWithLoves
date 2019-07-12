package com.management.service.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    private String id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "songCategories")
    private Set<Song> categorySong = new HashSet<>();

    public Category() {
    }

    public Category(String id, String name) {
        this.name = name;
        this.id = id;
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

    public Set<Song> getCategorySong() {
        return categorySong;
    }

    public void setCategorySong(Set<Song> categorySong) {
        this.categorySong = categorySong;
    }
}
