package com.example.ibtesamm.audiobookplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    String TAG = "ChapterAdapter";

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
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ChapterHolder chapterHolder, final int i) {

        final ChapterInfo chapter = list.get(i);

        chapterHolder.chapterName.setText( chapter.name ); //Sets the name of the chapter
        chapterHolder.background.setOnClickListener(new View.OnClickListener() { //Sets the onclick listener
            @Override
            public void onClick(View view) {
                if(onButtonClickListener != null)
                    onButtonClickListener.onButtonClick(chapterHolder.background, view, chapter, i);
            }
        });


        //Changes the background depending on touch to emulate the pressing of the button
        chapterHolder.background.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.e(TAG, motionEvent.getAction() + "");

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) //If pressed make darker
                    chapterHolder.background.setBackgroundResource(R.color.cardBackgroundDark);

                else //If released make lighter
                    chapterHolder.background.setBackgroundResource(R.color.cardBackgroundLight);

                return false;
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
        ConstraintLayout background;

        public ChapterHolder(View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.chapterName);
            background = itemView.findViewById(R.id.cardBody);
        }
    }
}
