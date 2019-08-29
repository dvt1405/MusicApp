package com.example.mm.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mm.room.MMDatabase;
import com.example.mm.room.dao.Music_PlaylistDAO;
import com.example.mm.room.dao.PlaylistDAO;
import com.example.mm.room.model.Playlist;

import java.util.List;

public class PlaylistRepository {
    private PlaylistDAO playlistDAO;
    private Music_PlaylistDAO music_playlistDAO;
    private LiveData<List<Playlist>> getListPlaylist;

    public PlaylistRepository(Application application) {
        MMDatabase mmDatabase = MMDatabase.getDatabase(application);
        playlistDAO = mmDatabase.playlistDAO();
        music_playlistDAO = mmDatabase.musicPlaylistDAO();
    }

    public LiveData<List<Playlist>> getGetListPlaylist() {
        return getListPlaylist;
    }

    public void insertPlaylist(Playlist playlist) {
        new query(playlistDAO,Constants.INSERT).execute(playlist);
    }
    public void insertPlaylist(List<Playlist> listPlaylist) {
        for( int i=0; i<listPlaylist.size();i++) {
            new query(playlistDAO,Constants.INSERT).execute(listPlaylist.get(i));
        }
    }
    public void deletePlaylists() {
        new query(playlistDAO,Constants.DELETE).execute();
    }
    public void deletePlaylist(Playlist playlist) {
        new query(playlistDAO,Constants.DELETE_ONE).execute(playlist);
    }
    public void updatePlaylist(Playlist playlist) {
        new query(playlistDAO,Constants.UPDATE).execute(playlist);
    }



    public static class query extends AsyncTask<Playlist,Void,Void> {
        private PlaylistDAO playlistDAO;
        private String opstions;

        public query(PlaylistDAO playlistDAO, String opstions) {
            this.playlistDAO = playlistDAO;
            this.opstions = opstions;
        }

        @Override
        protected Void doInBackground(Playlist... playlists) {
            switch (this.opstions) {
                case Constants.INSERT:
                    playlistDAO.insert(playlists[0]);
                    break;
                case Constants.DELETE:
                    playlistDAO.delete();
                    break;
                case Constants.GETALL:
                    break;
                case Constants.UPDATE:
                    playlistDAO.update(playlists[0]);
                    break;
                case Constants.DELETE_ONE:
                    playlistDAO.delete(playlists[0].getId());
            }
            return null;
        }
    }
}
