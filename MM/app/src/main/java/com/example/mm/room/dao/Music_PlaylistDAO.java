package com.example.mm.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mm.room.model.Music_Playlist;
import com.example.mm.room.model.Playlist;

import java.util.List;
@Dao
public interface Music_PlaylistDAO  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Music_Playlist music_playlist);
    @Query("DELETE  FROM music_playlist")
    void delete();
    @Query("DELETE FROM music_playlist WHERE id = :id")
    void delete(int id);
    @Update
    void update(Music_Playlist music_playlist);
    @Query("SELECT * FROM music_playlist WHERE id_playlist = :idPlaylist")
    LiveData<List<Music_Playlist>> getMusicByIdPlaylist(int idPlaylist);
    @Query("SELECT * FROM music_playlist")
    LiveData<List<Music_Playlist>> getAllPlaylist_Music();
}
