package com.example.mm.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mm.room.model.Music;
import com.example.mm.room.model.Playlist;

import java.util.List;

@Dao
public interface PlaylistDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Playlist playlist);
    @Query("DELETE FROM playlist")
    void delete();
    @Query("DELETE FROM playlist WHERE id = :id")
    void delete(int id);
    @Update
    void update(Playlist playlist);
    @Query("SELECT * FROM playlist")
    LiveData<List<Playlist>> getAllPlaylist();
    @Query("SELECT * FROM playlist WHERE id = :idPlaylist")
    LiveData<Playlist> getPlaylist(int idPlaylist);
    @Query("SELECT music.* FROM music " +
            "INNER JOIN music_playlist WHERE music_playlist.id_playlist= :idList")
    LiveData<List<Music>> getListMusicByIDList(int idList);
}
