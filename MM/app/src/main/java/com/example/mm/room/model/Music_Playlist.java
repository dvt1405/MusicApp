package com.example.mm.room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "music_playlist")
public class Music_Playlist {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_music")
    @ForeignKey(entity = Music.class
            ,parentColumns = "id"
            ,childColumns = "id_music",onDelete = CASCADE)
    private int id_music;

    @ColumnInfo(name = "id_playlist")
    @ForeignKey(entity = Playlist.class
            ,parentColumns = "id"
            ,childColumns = "id_playlist",onDelete = CASCADE)
    private int id_playlist;
    @ColumnInfo(name = "position")
    private int position;

    public Music_Playlist() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_music() {
        return id_music;
    }

    public void setId_music(int id_music) {
        this.id_music = id_music;
    }

    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
