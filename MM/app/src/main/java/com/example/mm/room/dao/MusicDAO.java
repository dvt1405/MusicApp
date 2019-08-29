package com.example.mm.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mm.room.model.Music;
import com.example.mm.room.model.Music_Playlist;

import java.util.List;
@Dao
public interface MusicDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Music music);
    @Query("DELETE  FROM music")
    void delete();
    @Query("DELETE FROM music WHERE id = :id")
    void delete(int id);
    @Update
    void update(Music music);
    @Query("SELECT * FROM music WHERE music_name = :name")
    LiveData<List<Music>> getMusicByName(String name);
    @Query("SELECT music.* FROM music " +
            "LEFT JOIN music_playlist " +
            "ON music_playlist.id_playlist = :idPlaylist " +
            "WHERE music.id = music_playlist.id_music")
    LiveData<List<Music>> getListMusicByIDPlaylist(int idPlaylist);
    @Query("SELECT * FROM music")
    LiveData<List<Music>> getAllMusic();
}
