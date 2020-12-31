package com.example.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView audio_list ;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        audio_list = (ListView) findViewById(R.id.audio_list);

        getPermissionsAtRuntime();
    }


    public void getPermissionsAtRuntime()
    {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                renderList();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();
    }


    public ArrayList<File> findSongs(File f){
        ArrayList<File> arrayList = new ArrayList<>();

        File [] files = f.listFiles();

        for(File singleFile: files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
                arrayList.addAll(findSongs(singleFile));
            else{
                if(singleFile.getName().endsWith(".mp3"))
                    arrayList.add(singleFile);
            }
        }

        return  arrayList;
    }


    public void renderList()
    {
        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());

        items = new String[mySongs.size()];

        for(int i =0;i<mySongs.size();i++)
            items[i] = mySongs.get(i).getName().toString();

        ArrayAdapter  <String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        audio_list.setAdapter(myAdapter);

        audio_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                String songName = audio_list.getItemAtPosition(i).toString();

                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("songs", mySongs).putExtra("songName", songName).putExtra("pos", i));
            }
        });
    }
}