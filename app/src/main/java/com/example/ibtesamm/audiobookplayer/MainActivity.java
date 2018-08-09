package com.example.ibtesamm.audiobookplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //View Elements
    RecyclerView chapterView;
    Button playButton;
    SeekBar playBack;

    //Support
    ChapterAdapter chapterAdapter;
    ArrayList<ChapterInfo> chapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views defined
        chapterView = findViewById(R.id.chapterView);
        playBack = findViewById(R.id.playBack);
        playButton = findViewById(R.id.play);

        //Defines the chapters list
        chapters = new ArrayList<>();

        defineChpaters();

    }

    //Adds the chapter cards to the recycler view
    private void defineChpaters(){

        //Sets the orientarion
        chapterView.setLayoutManager(new LinearLayoutManager(this) );

        //Creates the adapter
        ChapterAdapter chapterAdapter = new ChapterAdapter(this, chapters);

        //Defines that the play buttons on the cards do
        chapterAdapter.setOnButtonClickListener(new ChapterAdapter.onButtonClickListener() {
            @Override
            public void onButtonClick(Button b, View v, ChapterInfo c, int pos) {
                //When button clicked
            }
        });

        chapterView.setAdapter(chapterAdapter); //Sets the adapter to the recycler view and shows the cards

    }
}
