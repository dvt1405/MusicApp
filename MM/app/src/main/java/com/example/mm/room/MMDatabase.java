package com.example.mm.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mm.room.dao.MusicDAO;
import com.example.mm.room.dao.Music_PlaylistDAO;
import com.example.mm.room.dao.PlaylistDAO;
import com.example.mm.room.model.Music;
import com.example.mm.room.model.Music_Playlist;
import com.example.mm.room.model.Playlist;

@Database(entities = {Music.class, Music_Playlist.class, Playlist.class},version = 1,exportSchema = false)
public abstract class MMDatabase extends RoomDatabase {
    public abstract MusicDAO musicDAO();
    public abstract Music_PlaylistDAO musicPlaylistDAO();
    public abstract PlaylistDAO playlistDAO();
    private static MMDatabase INSTANCE;
    public static MMDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (MMDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            ,MMDatabase.class,"mm_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
