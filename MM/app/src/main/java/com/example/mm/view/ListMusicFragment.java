package com.example.mm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mm.R;
import com.example.mm.adapter.MusicAdapter;
import com.example.mm.room.model.Music;
import com.example.mm.viewmodel.MusicViewModel;

import java.util.List;

public class ListMusicFragment extends Fragment {
    private RecyclerView recyclerView;
    private MusicViewModel musicViewModel;
    private MusicAdapter musicAdapter;
    private static ListMusicFragment INSTANCE;

    private ListMusicFragment() {
    }

    public static ListMusicFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ListMusicFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        initView(view);
        initAction();
        return view;
    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewMusicFragment);
    }

    public void initAction() {
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);
        musicAdapter = new MusicAdapter(getActivity());
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        musicViewModel.getMusicLiveData().observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(List<Music> music) {
                musicAdapter.setMusicList(music);
            }
        });

    }
}
