package com.example.ibtesamm.audiobookplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaMetadata;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import dm.audiostreamer.AudioStreamingManager;
import dm.audiostreamer.CurrentSessionCallback;
import dm.audiostreamer.MediaMetaData;
import dm.audiostreamer.StreamingManager;

public class MainActivity extends AppCompatActivity implements CurrentSessionCallback {

    //DMAudioStreamer
    private StreamingManager streamingManager;

    //System
    private final String TAG = "MainActivity";

    //View Elements
    private RecyclerView chapterView;
    private ImageButton playButton;
    private SeekBar playBack;

    //RecyclerView Support
    private ArrayList<ChapterInfo> chapters;
    private ChapterAdapter chapterAdapter;
    private int currentSongIndex = 0;

    //Files
    private String booksPath = "/storage/self/primary/Books";

    //Media
//    private MediaPlayer mMediaPlayer;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SYSTEM~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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

        //SeeksBar Manager Thread created
        Thread seekBarThread = new SeekBarThread();
        seekBarThread.start();

        //Seek Bar Setup
        playBack.setMax(0);
        playBack.setProgress(0);
        playBack.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int pos, boolean b) {
                //Sets the progress of the media player to the desired area

//                if(mMediaPlayer != null){
//
//                    mMediaPlayer.seekTo( pos ); //seeks the the changed position
//
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop(); //stops the media player
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CHAPTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
        chapterAdapter = new ChapterAdapter(this, chapters);

        //Defines that the play buttons on the cards do
        chapterAdapter.setOnButtonClickListener(new ChapterAdapter.onButtonClickListener() {
            @Override
            public void onButtonClick(ConstraintLayout b, View v, ChapterInfo c, int pos) {
                currentSongIndex = pos; //Sets the current songs index

//                if(mMediaPlayer != null){
//                    stop();
//                }
//
//                try {
//                    play(c.url);
//                    //Log.e(TAG, "onButtonClick:" );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

        chapterView.setAdapter(chapterAdapter); //Sets the adapter to the recycler view and shows the cards

        //Registers MediaManager
        registerMediaManager();

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MEDIA~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //Plays the media file defined by the given url
    private void play(ChapterInfo chapterInfo) throws IOException {

        MediaMetadata mediaMetadata = createMetaData(ChapterInfo chapterInfo);

//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setDataSource(url); //Finds the url for the chapter
//        mMediaPlayer.prepareAsync(); //Prepares the song Asynchronously
//        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                //Play when chapter is prepared
//                mediaPlayer.start();
//
//                //SeekBar Setup
//                playBack.setProgress(0);
//                playBack.setMax( mediaPlayer.getDuration() );
//
//                //Activate the play/pause button
//                playButton.setImageDrawable(getDrawable(android.R.drawable.ic_media_pause));
//                playButton.setClickable(true);
//            }
//        });
//
//        //Play the next chapter on complete
//        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                currentSongIndex++;
//                if( currentSongIndex == chapters.size() ) stop(); //Stops the media player at the end of the list
//                else{
//                    try {
//                        play(chapters.get(currentSongIndex).url); //Play next chapter
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

    }

    //Stops the media player
    private void stop(){

//        if(mMediaPlayer == null) return;
//
//        mMediaPlayer.stop();
//        mMediaPlayer.reset();
//        mMediaPlayer.release();
//        mMediaPlayer = null;
//
//        //Deactivate the play/pause button
//        playButton.setImageDrawable(getDrawable(android.R.drawable.ic_media_play));
//        playButton.setClickable(false);
//
//    }
//
//    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ONCLICK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
//
//    //Pauses/Plays the chapter
//    public void onPlayBtnPressed(View view){
//
//        if(mMediaPlayer == null) return; //Button doesn't do anything if the media player isn't playing
//
//        if(!mMediaPlayer.isPlaying()){
//            //Play the song
//            mMediaPlayer.start();
//            playButton.setImageDrawable(getDrawable(android.R.drawable.ic_media_pause));
//        }
//
//        else{
//            //Pause the song
//            mMediaPlayer.pause();
//            playButton.setImageDrawable(getDrawable(android.R.drawable.ic_media_play));
//        }

    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PERMISSIONS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DMAudioStreamer~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //Methods

    //Registers the streamingManager
    private void registerMediaManager(){

        streamingManager = AudioStreamingManager.getInstance(this);

    }

    //Creates a MediaMetaDataObject from a SongInfo object
    private MediaMetadata createMetaData(ChapterInfo chapterInfo){
        MediaMetadata mediaMetadata = new MediaMetadata();


    }


    //Interface Items
    @Override
    public void updatePlaybackState(int state) {
        switch (state) {
            case PlaybackStateCompat.STATE_PLAYING:
                break;
            case PlaybackStateCompat.STATE_PAUSED:
                break;
            case PlaybackStateCompat.STATE_NONE:
                break;
            case PlaybackStateCompat.STATE_STOPPED:
                break;
            case PlaybackStateCompat.STATE_BUFFERING:
                break;
        }
    }

    @Override
    public void playSongComplete() {

    }

    @Override
    public void currentSeekBarPosition(int i) {

    }

    @Override
    public void playCurrent(int i, MediaMetaData mediaMetaData) {

    }

    @Override
    public void playNext(int i, MediaMetaData mediaMetaData) {

    }

    @Override
    public void playPrevious(int i, MediaMetaData mediaMetaData) {

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CLASSES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //Manages the SeekBar position on an external thread
    private class SeekBarThread extends Thread{

        @Override
        public void run() {

            while(true){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Manages the seekbar if media player is present
//                if(mMediaPlayer != null){
//
//                    playBack.post(new Runnable() {
//                        @Override
//                        public void run() {
////                            Log.e(TAG, mMediaPlayer.getCurrentPosition() + " | " + playBack.getProgress());
//                            playBack.setMax( mMediaPlayer.getDuration() ); //Fixes the seekbar bug (seek bar wouldnt move)
//                            playBack.setProgress( mMediaPlayer.getCurrentPosition() );
//                        }
//                    });
//
//                }
            }

        }


    }


}
