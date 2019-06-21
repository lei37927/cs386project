package com.example.cs386project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class lullaby extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer1 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lullaby);
        Button play1 = (Button) findViewById(R.id.play1);
        Button pause1 = (Button) findViewById(R.id.pause1);
        play1.setOnClickListener(this);
        pause1.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(lullaby.this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(lullaby.this, new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            initMediaPlayer(); //初始化MediaPlayer
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.play1:
                if(!mediaPlayer1.isPlaying()) {
                    mediaPlayer1.start(); //开始播放
                }
                break;
            case R.id.pause1:
                if(mediaPlayer1.isPlaying()) {
                    mediaPlayer1.pause(); //暂停播放
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer1 != null){
            mediaPlayer1.stop();
            mediaPlayer1.release();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "forest.mp3");
            mediaPlayer1.setDataSource("/mnt/sdcard/1/123/forest.mp3"); //指定音频文件的路径
            mediaPlayer1.prepare(); //让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}