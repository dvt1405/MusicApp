package com.example.mm.room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;
@Entity(tableName = "playlist")
public class Playlist {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String Description;
    @ColumnInfo(name = "date_created")
    private String dateCreated;
    @ColumnInfo(name = "date_modified")
    private String dateModified;

    public Playlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
    public static class Builder{
        private String name, date_create, date_modify,description;

        public Builder(String name) {
            this.name = name;
        }
        public Builder dateCreate(String date_create) {
            this.date_create = date_create;
            return this;
        }
        public Builder dateModify(String date_modify) {
            this.date_modify = date_modify;
            return this;
        }
        public Builder Description(String description) {
            this.description = description;
            return this;
        }
        public Playlist build() {
            return new Playlist(this);
        }
    }
    private Playlist(Builder builder) {
        this.name = builder.name;
        this.dateCreated = builder.date_create;
        this.dateModified = builder.date_modify;
        this.Description = builder.description;
    }
}
