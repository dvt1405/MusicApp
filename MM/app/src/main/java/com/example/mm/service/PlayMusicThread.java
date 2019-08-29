package com.example.mm.service;

import android.media.MediaPlayer;
import android.provider.MediaStore;

import androidx.appcompat.widget.AppCompatSeekBar;

import java.io.IOException;

import android.os.Handler;
import android.widget.SeekBar;

public class PlayMusicThread implements Runnable {
    private Handler handler;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private String pathFile;
    private static PlayMusicThread INSTANCE;

    private PlayMusicThread() {

    }

    public static PlayMusicThread getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayMusicThread();
        }
        return INSTANCE;
    }

    @Override
    public void run() {
        if (!mediaPlayer.isPlaying()) {
            seekBar.setMax(mediaPlayer.getDuration());
        }
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
