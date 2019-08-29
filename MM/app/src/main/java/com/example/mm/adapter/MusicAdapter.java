package com.example.mm.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.SaveInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mm.R;
import com.example.mm.room.model.Music;
import com.example.mm.view.PlayMusicFragment;

import java.io.IOException;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Music> musicList;
    private final Context context;
    public MusicAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public MusicAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new MusicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MusicViewHolder holder, final int position) {
        if (musicList != null) {
            final Music current = musicList.get(position);
            holder.textView.setText(current.getName());

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("music",current);
                    PlayMusicFragment playMusicFragment = PlayMusicFragment.getInstance();
                    playMusicFragment.setArguments(bundle);
                    AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                    appCompatActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameFragment, playMusicFragment)
                            .addToBackStack("main")
                            .commit();
                }
            });
        } else {
            Log.i("Status: ","No music!");
        }
    }

    @Override
    public int getItemCount() {
        if (musicList != null)
            return musicList.size();
        else return 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemTextViewMusic);
        }
    }
    public void setMusic(List<Music> list) {
        this.musicList = list;
        notifyDataSetChanged();
    }
}
