package com.example.mm.repository;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mm.room.MMDatabase;
import com.example.mm.room.dao.MusicDAO;
import com.example.mm.room.model.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicRepository {
    private MusicDAO musicDAO;
    private LiveData<List<Music>> listAllMusicLiveData;
    ContentResolver contentResolver;

    public MusicRepository(Application application) {
        contentResolver = application.getContentResolver();
        MMDatabase mmDatabase = MMDatabase.getDatabase(application);
        musicDAO = mmDatabase.musicDAO();
        // Insert music from storage to music table
        this.insertMusic(this.getListSongFromStorage());
        listAllMusicLiveData = musicDAO.getAllMusic();
    }

    public LiveData<List<Music>> getListAllMusicLiveData() {
        return listAllMusicLiveData;
    }

    public void insertMusic(Music music) {
        new query(musicDAO, Constants.INSERT).execute(music);
    }

    public void insertMusic(List<Music> musics) {
        for (int i = 0; i < musics.size(); i++) {
            new query(musicDAO, Constants.INSERT).execute(musics.get(i));
        }
    }

    public MusicDAO getMusicDAO() {
        return musicDAO;
    }

    public void deleteMusic(Music music) {
        new query(musicDAO, Constants.DELETE_ONE).execute(music);
    }

    public void update(Music music) {
        new query(musicDAO, Constants.UPDATE).execute(music);
    }

    public void deleteAll() {
        new query(musicDAO, Constants.DELETE).execute();
    }

    public List<Music> getListSongFromStorage() {
        List<Music> listMusic = new ArrayList<>();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            int position = 0;
            do {
                position++;
                int id = musicCursor.
                        getInt(musicCursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = musicCursor.
                        getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer = musicCursor
                        .getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String composer = musicCursor
                        .getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER));
                String path = musicCursor
                        .getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                Music music = new Music.Builder(title)
                        .id(id)
                        .singer(singer)
                        .position(position)
                        .composer(composer)
                        .path(path)
                        .build();
                music.setSinger(path);
                listMusic.add(music);
            } while (musicCursor.moveToNext());
        }
        return listMusic;
    }

    public List<File> findPathByName(File file, String name) {
        ArrayList<File> listFile = new ArrayList<>();
        File[] arrFile = file.listFiles();
        for (File singleFile : arrFile) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                listFile.addAll(findPathByName(singleFile, name));
            } else {
                if (singleFile.getName().contains(name)) {
                    listFile.add(singleFile);
                }
            }
        }
        return listFile;
    }

    public List<File> findSongFromStorage(File file) {
        ArrayList<File> listFile = new ArrayList<>();
        File[] arrFile = file.listFiles();
        for (File singleFile : arrFile) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                listFile.addAll(findSongFromStorage(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3")
                ) {
                    listFile.add(singleFile);
                }
            }
        }
        return listFile;
    }

    public List<Music> mapMusicFileToObject() {
        ArrayList<File> listFileMusic = (ArrayList<File>) findSongFromStorage(Environment.getExternalStorageDirectory());
        List<Music> listMusic = new ArrayList<>();
        for (File file : listFileMusic) {
            String title = file.getName().toString().replace(".mp3", "");
            String path = file.getPath();
            Music music = new Music.Builder(title)
                    .path(path)
                    .build();
            listMusic.add(music);
        }
        return listMusic;
    }

    public static class query extends AsyncTask<Music, String, Void> {
        private MusicDAO musicDAO;
        private String options;

        public query(MusicDAO musicDAO, String options) {
            this.musicDAO = musicDAO;
            this.options = options;
        }

        @Override
        protected Void doInBackground(Music... music) {
            switch (options) {
                case Constants.INSERT:
                    musicDAO.insert(music[0]);
                    break;
                case Constants.DELETE:
                    musicDAO.delete();
                    break;
                case Constants.GETALL:
                    musicDAO.getAllMusic();
                    break;
                case Constants.UPDATE:
                    musicDAO.update(music[0]);
                    break;
                case Constants.DELETE_ONE:
                    musicDAO.delete(music[0].getId());
            }
            return null;
        }
    }

}
