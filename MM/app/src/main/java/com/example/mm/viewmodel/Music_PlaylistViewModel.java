package com.example.mm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mm.repository.Music_PlaylistRepository;
import com.example.mm.room.model.Music_Playlist;

import java.util.List;

public class Music_PlaylistViewModel extends AndroidViewModel {
    private LiveData<List<Music_Playlist>> liveData;
    private Music_PlaylistRepository music_playlistRepository;

    public Music_PlaylistViewModel(@NonNull Application application) {
        super(application);
        music_playlistRepository = new Music_PlaylistRepository(application);
        liveData = music_playlistRepository.getListLiveData();
    }
    public void insertMusic_Playlist(Music_Playlist music_playlist) {
        this.music_playlistRepository.insertPlaylist_Music(music_playlist);
    }
    public void insertMusic_Playlist(List<Music_Playlist> list) {
        this.music_playlistRepository.insertPlaylist_Music(list);
    }
    public void updateMusic_Playlist(Music_Playlist music_playlist) {
        this.music_playlistRepository.updatePlaylist_Music(music_playlist);
    }
    public void deleteMusic_Playlist(Music_Playlist music_playlist) {
        this.music_playlistRepository.deletePlaylist_Music(music_playlist);
    }
    public void deleteAll() {
        this.music_playlistRepository.deleteAll();
    }
}
