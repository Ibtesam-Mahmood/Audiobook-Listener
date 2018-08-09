package com.example.ibtesamm.audiobookplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    //View Elements
    RecyclerView chapterView;
    Button playButton;
    SeekBar playBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views defined
        chapterView = findViewById(R.id.chapterView);
        playBack = findViewById(R.id.playBack);
        playButton = findViewById(R.id.play);

    }
}
