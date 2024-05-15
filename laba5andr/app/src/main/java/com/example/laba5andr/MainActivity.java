package com.example.laba5andr;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    VideoView MyvideoPlayer;
    MediaPlayer mediaPlayer;
    Button startButton, pauseButton, stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyvideoPlayer =  (VideoView)findViewById(R.id.videoView);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.videvo);
        MyvideoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        MyvideoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(MyvideoPlayer);
        mediaPlayer = MediaPlayer.create(this, R.raw.muzika);
        mediaPlayer.setOnCompletionListener(mp -> stopPlay());
        startButton = (Button) findViewById(R.id.startVidBtn);
        pauseButton = (Button) findViewById(R.id.pauseVidBtn);
        stopButton = (Button) findViewById(R.id.resumeVidBtn);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);


    }

    private void stopPlay(){
        mediaPlayer.stop();
        MyvideoPlayer.stopPlayback();
        MyvideoPlayer.resume();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void onStartClick(View view)
    {
        mediaPlayer.start();
        MyvideoPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void onPauseClick(View view)
    {
        mediaPlayer.pause();
        MyvideoPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void onStopClick(View view)
    {
        MyvideoPlayer.stopPlayback();
        MyvideoPlayer.resume();
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stopPlay();
        }
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
    }
}