package com.example.mm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mm.repository.PlaylistRepository;
import com.example.mm.room.model.Playlist;

import java.util.List;

public class PlaylistViewModel extends AndroidViewModel {
    private LiveData<List<Playlist>> playlistLiveData;
    private PlaylistRepository playlistRepository;

    public PlaylistViewModel(@NonNull Application application) {
        super(application);
        playlistRepository = new PlaylistRepository(application);
        playlistLiveData = playlistRepository.getGetListPlaylist();
    }
    public void inserPlaylist(Playlist playlist) {
        this.playlistRepository.insertPlaylist(playlist);
    }
    public void insertPlaylist(List<Playlist> list) {
        this.playlistRepository.insertPlaylist(list);
    }
    public void deletePlaylist(Playlist playlist) {
        this.playlistRepository.deletePlaylist(playlist);
    }
    public void deleteAll() {
        this.playlistRepository.deletePlaylists();
    }
    public void updatePlaylist(Playlist playlist) {
        this.playlistRepository.updatePlaylist(playlist);
    }
}
