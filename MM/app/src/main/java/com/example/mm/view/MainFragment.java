package com.example.mm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mm.R;

public class MainFragment extends Fragment {
    Button btnMusic, btnPlaylist;
    private static MainFragment INSTANCE;
    private MainFragment() {
    }
    public static MainFragment getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new MainFragment();
        }
        return INSTANCE;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        initView(view);
        initAction();
        return view;
    }
    public void initView(View view) {
        btnMusic = view.findViewById(R.id.btnMusicMainFragment);
        btnPlaylist = view.findViewById(R.id.btnPlaylistMainFragment);
    }
    public void initAction() {
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameFragment,ListMusicFragment.getInstance())
                        .addToBackStack("mainStack")
                        .commit();
            }
        });
    }
}

