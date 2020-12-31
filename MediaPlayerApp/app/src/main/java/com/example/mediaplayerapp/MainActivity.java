package com.example.mediaplayerapp;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button play,pause,stop,next,prev;
    MediaPlayer m;
    SeekBar seekbar ;
    TextView songName;

    int pos;
    ArrayList<File> mySongs;
    Thread updateSeekBar;

    String sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) (findViewById(R.id.stop));

     //   next = (Button) (findViewById(R.id.next));
     //   prev = (Button) (findViewById(R.id.prev));

        seekbar = (SeekBar) findViewById(R.id.seekbar);

        songName = (TextView) findViewById(R.id.songName);


        //m = MediaPlayer.create(this, R.raw.test_audio);

        updateSeekBar = new Thread(){
            @Override
            public void run()
            {
                int total = m.getDuration();
                int cur = 0;

                while(cur < total)
                {
                    try{
                        sleep(500);
                        cur = m.getCurrentPosition();
                        seekbar.setProgress(cur);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        if(m != null){
            m.stop();
            m.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");

        sName = mySongs.get(pos).getName().toString();

        String songNam = i.getStringExtra("songName");

        songName.setText(songNam);
        songName.setSelected(true);

        pos = bundle.getInt("pos", 0);

        Uri u = Uri.parse(mySongs.get(pos).toString());

        m = MediaPlayer.create(getApplicationContext(),u);

        m.start();

        seekbar.setMax(m.getDuration());
        updateSeekBar.start();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                m.seekTo(seekBar.getProgress());
            }
        });


//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                m.stop();
//                m.release();
//                pos=((pos+1)%mySongs.size());
//                Uri u = Uri.parse(mySongs.get( pos).toString());
//                // songNameText.setText(getSongName);
//                m = MediaPlayer.create(getApplicationContext(),u);
//
//                sName = mySongs.get(pos).getName().toString();
//                songName.setText(sName);
//
//                try{
//                    m.start();
//                }catch(Exception e){}
//
//            }
//        });


//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //songNameText.setText(getSongName);
//                m.stop();
//                m.release();
//
//                pos=((pos-1)<0)?(mySongs.size()-1):(pos-1);
//                Uri u = Uri.parse(mySongs.get(pos).toString());
//                m = MediaPlayer.create(getApplicationContext(),u);
//                sName = mySongs.get(pos).getName().toString();
//                songName.setText(sName);
//                m.start();
//            }
//        });



    }

    public void handlePlay(View v)
    {
       m.start();
    }

    public void handlePause(View v)
    {
        m.pause();
    }

    public void handleStop(View v)
    {
        m.stop();
    }
}