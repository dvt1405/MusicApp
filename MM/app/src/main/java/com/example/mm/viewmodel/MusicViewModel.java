package com.example.mm.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mm.repository.MusicRepository;
import com.example.mm.room.model.Music;

import java.util.List;

public class MusicViewModel extends AndroidViewModel {
    private LiveData<List<Music>> musicLiveData;
    private MusicRepository musicRepository;

    public MusicViewModel(@NonNull Application application) {
        super(application);
        musicRepository = new MusicRepository(application);
        musicLiveData = musicRepository.getListAllMusicLiveData();
    }

    public void insertMusic(Music music) {
        this.musicRepository.insertMusic(music);
    }

    public void insertMusic(List<Music> list) {
        this.musicRepository.insertMusic(list);
    }

    public void deleteAll() {
        this.musicRepository.deleteAll();
    }
    public void deleteMusic(Music music) {
        this.musicRepository.deleteMusic(music);
    }
    public void updateMusic(Music music) {
        this.musicRepository.update(music);
    }

    public LiveData<List<Music>> getMusicLiveData() {
        return musicLiveData;
    }

    public void setMusicLiveData(LiveData<List<Music>> musicLiveData) {
        this.musicLiveData = musicLiveData;
    }

    public MusicRepository getMusicRepository() {
        return musicRepository;
    }

    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
}
