package com.example.ibtesamm.audiobookplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {

    //Adapter for the chapter recycler view

    ArrayList<ChapterInfo> list; //List that holds all cards
    Context context;

    onButtonClickListener onButtonClickListener;

    public ChapterAdapter(Context context, ArrayList<ChapterInfo> list) {
        this.list = list;
        this.context = context;
    }

    //Creates a holder and attaches it to the chpater_layout
    @NonNull
    @Override
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChapterHolder( LayoutInflater.from(context).inflate(R.layout.chapter_layout, viewGroup, false) );
    }

    //Binds a list item to the holder
    @Override
    public void onBindViewHolder(@NonNull final ChapterHolder chapterHolder, final int i) {

        final ChapterInfo chapter = list.get(i);

        chapterHolder.chapterName.setText( chapter.name ); //Sets the name of the chapter
        chapterHolder.actionButton.setOnClickListener(new View.OnClickListener() { //Sets the onclick listener
            @Override
            public void onClick(View view) {
                if(onButtonClickListener != null)
                    onButtonClickListener.onButtonClick(chapterHolder.actionButton, view, chapter, i);
            }
        });

    }

    //Amount of items
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Sets the onButtonClickListener
    public void setOnButtonClickListener(onButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    //Interface that is used to define the buttons within the cardHolder
    public interface onButtonClickListener{

        void onButtonClick(ConstraintLayout b, View v,  ChapterInfo c, int pos);

    }


    public class ChapterHolder extends RecyclerView.ViewHolder{

        TextView chapterName;
        ConstraintLayout actionButton;

        public ChapterHolder(View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.chapterName);
            actionButton = itemView.findViewById(R.id.cardBody);
        }
    }
}
