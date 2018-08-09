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
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterHolder cChapterHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChapterHolder extends RecyclerView.ViewHolder{

        TextView chapterName;
        Button actionButton;

        public ChapterHolder(View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.chapterName);
            actionButton = itemView.findViewById(R.id.actionBtn);
        }
    }
}
