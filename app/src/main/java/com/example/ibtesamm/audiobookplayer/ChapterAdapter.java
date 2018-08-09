package com.example.ibtesamm.audiobookplayer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {
    @NonNull
    @Override
    public ChapterAdapter.ChapterAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ChapterAdapter chapterAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChapterHolder extends RecyclerView.ViewHolder{

        TextView chapterName;
        Button actionButton;

        public ChapterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
