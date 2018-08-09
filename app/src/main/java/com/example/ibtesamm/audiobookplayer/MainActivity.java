package com.example.ibtesamm.audiobookplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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

    //Files
    String booksPath = "/storage/self/primary/Books";


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

        //Checks permissions
        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        requestPermission(permissions, 2501);


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


    //Requests a set of permissions from the user if they are disabled
    //@param permission - the set of permissions you wish to check
    //@param code - a code that the you enter to match the permission request
    public void requestPermission(String[] permission, int code){


        if (ContextCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, code);
            }

        }


    }

    //Responds to the change in permission status
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case 2501: //If external memory reading is changed
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
