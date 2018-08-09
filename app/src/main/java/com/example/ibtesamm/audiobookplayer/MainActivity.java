package com.example.ibtesamm.audiobookplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Base
    final String TAG = "MainActivity";

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

    //Checks the /Books directory and adds the books to the list
    //to be later displayed by the recycler view
    private void addChapters() throws MalformedURLException {

        //Sets the path to the /Books folder
        File pathFile = new File(booksPath);

        //Finds all the mp3 files in the directory
        File[] books = pathFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                name = name.toLowerCase();
                return name.endsWith(".mp3");
            }
        });

        //Adds the chapter to the list to be later displayed by the recycler view
        if(books != null){
            for(File chapter : books){
                ChapterInfo chapterInfo = new ChapterInfo(chapter.getName(), chapter.toURI().toURL().toString());
                chapters.add(chapterInfo);
            }
        }

        showChapters();

    }


    //Adds the chapter cards to the recycler view
    private void showChapters(){

        //Sets the orientation
        chapterView.setLayoutManager(new LinearLayoutManager(this) );

        //Creates the adapter
        ChapterAdapter chapterAdapter = new ChapterAdapter(this, chapters);

        //Defines that the play buttons on the cards do
        chapterAdapter.setOnButtonClickListener(new ChapterAdapter.onButtonClickListener() {
            @Override
            public void onButtonClick(Button b, View v, ChapterInfo c, int pos) {
                //Play Sound
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

        } else{
            //Runs this if the permission is already granted
            onPermissionGranted(code);

        }


    }

    //Responds to the change in permission status
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Do nothing if the permission is not granted
        if(grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        onPermissionGranted(requestCode);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //Runs the segment of code indented to run if the permission passed (identified though a code) is granted
    private void onPermissionGranted(int code){

        switch (code){

            case 2501:
                try {
                    addChapters();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return;

        }

    }

}
