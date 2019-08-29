package com.example.mm.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Pair;

import androidx.lifecycle.LiveData;

import com.example.mm.room.MMDatabase;
import com.example.mm.room.dao.MusicDAO;
import com.example.mm.room.dao.Music_PlaylistDAO;
import com.example.mm.room.dao.PlaylistDAO;
import com.example.mm.room.model.Music;
import com.example.mm.room.model.Music_Playlist;
import com.example.mm.room.model.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Music_PlaylistRepository {
    private Music_PlaylistDAO music_playlistDAO;
    private MusicDAO musicDAO;
    private PlaylistDAO playlistDAO;
    private LiveData<List<Music_Playlist>> listLiveData;
    private List<Pair<Playlist,List<Music>>> listMusicOfPlaylist;

    public Music_PlaylistRepository(Application application) {
        MMDatabase mmDatabase = MMDatabase.getDatabase(application);
        music_playlistDAO = mmDatabase.musicPlaylistDAO();
        musicDAO = mmDatabase.musicDAO();
        playlistDAO = mmDatabase.playlistDAO();
        listLiveData = music_playlistDAO.getAllPlaylist_Music();

        List<Pair<Playlist,List<Music>>> listPlaylist_ListMusic = new ArrayList<>();
        for( int i=0; i<listLiveData.getValue().size();i++) {
            int idPlaylist = listLiveData.getValue().get(i).getId_playlist();
            // get playlist by id
            Playlist playlist = playlistDAO.getPlaylist(idPlaylist).getValue();
            //get all music of a playlist
            List<Music> listMusic = musicDAO.getListMusicByIDPlaylist(idPlaylist).getValue();

            Pair<Playlist ,List<Music>> pair = new Pair<>(playlist,listMusic);
            listPlaylist_ListMusic.add(pair);
        }
        listMusicOfPlaylist = listPlaylist_ListMusic;
    }

    public LiveData<List<Music_Playlist>> getListLiveData() {
        return listLiveData;
    }
    public void insertPlaylist_Music(Music_Playlist music) {
        new query(music_playlistDAO,Constants.INSERT).execute(music);
    }
    public void insertPlaylist_Music(List<Music_Playlist> musics) {
        for (int i= 0; i<musics.size();i++) {
            new query(music_playlistDAO,Constants.INSERT).execute(musics.get(i));
        }
    }
    public void deleteAll() {
        new query(music_playlistDAO,Constants.DELETE).execute();
    }
    public void deletePlaylist_Music(Music_Playlist music_playlist) {
        new query(music_playlistDAO,Constants.DELETE_ONE).execute(music_playlist);
    }
    public void updatePlaylist_Music(Music_Playlist music) {
        new query(music_playlistDAO,Constants.UPDATE).execute(music);
    }

    public static class query extends AsyncTask<Music_Playlist,Void,Void> {
        private Music_PlaylistDAO music_playlistDAO;
        private String options;

        public query(Music_PlaylistDAO music_playlistDAO, String options) {
            this.music_playlistDAO = music_playlistDAO;
            this.options = options;
        }

        @Override
        protected Void doInBackground(Music_Playlist... music_playlists) {
            switch (options){
                case Constants.INSERT:
                    music_playlistDAO.insert(music_playlists[0]);
                    break;
                case Constants.DELETE:
                    music_playlistDAO.delete();
                    break;
                case Constants.GETALL:
                    music_playlistDAO.getAllPlaylist_Music();
                    break;
                case Constants.UPDATE:
                    music_playlistDAO.update(music_playlists[0]);
                    break;
                case Constants.DELETE_ONE:
                    music_playlistDAO.delete(music_playlists[0].getId());
            }
            return null;
        }
    }
}
