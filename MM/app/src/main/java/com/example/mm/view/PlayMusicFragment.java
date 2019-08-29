package com.example.mm.view;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mm.R;
import com.example.mm.room.model.Music;
import com.example.mm.service.PlayMusicThread;

import java.io.IOException;

public class PlayMusicFragment extends Fragment {
    private TextView textView;
    private SeekBar seekBar;
    private Music music;
    MediaPlayer mediaPlayer;
    PlayMusicThread playMusicThread;
    private static PlayMusicFragment INSTANCE;
    private PlayMusicFragment(){}
    public static PlayMusicFragment getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayMusicFragment();
        }
        return INSTANCE;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_music_fragment,container,false);
        initView(view);
        initAction();
        return view;
    }
    public void initView(View view) {
        textView = view.findViewById(R.id.textViewPla);
        seekBar = view.findViewById(R.id.seekBarPlayMusic);
        mediaPlayer = new MediaPlayer();
    }
    public void initAction() {
        Bundle bundle = getArguments();
        if(bundle!=null) {
            music = bundle.getParcelable("music");
            textView.setText(music.getName()+music.getPath());
        }
        try {
            mediaPlayer.setDataSource(music.getPath());
            mediaPlayer.prepare();
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();
            playMusicThread = PlayMusicThread.getInstance();
            playMusicThread.setMediaPlayer(mediaPlayer);
            Handler handler = new Handler();
            playMusicThread.setHandler(handler);
            playMusicThread.setSeekBar(seekBar);
            playMusicThread.run();
            handler.postDelayed(playMusicThread::run, 50);


        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                playMusicThread.getMediaPlayer().seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
        mediaPlayer.setOn
    }

}
