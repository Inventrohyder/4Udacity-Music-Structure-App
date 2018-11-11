package com.example.inventrohyder.musicstructureapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class PlayerActivity extends AppCompatActivity {

    // Allows the play button to switch between play and pause icons
    boolean play = true;

    /**
     * Handles playback of all the sound files
     */
    MediaPlayer mMediaPlayer;

    /**
     * Handles Audio management and controls the audio playback dependant on Audio Focus.
     */
    AudioManager mAudioManager;

    /**
     * The listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        /*
         * Get the chosen {@link Podcast}
         * Get the Title, Author, Image
         * From an explicit intent
         */
        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.PODCAST_TITLE);
        String author = intent.getStringExtra(MainActivity.PODCAST_AUTHOR);
        // The default int value for the image is the id of the application icon
        int imageResourceId = intent.getIntExtra(MainActivity.PODCAST_ART, R.drawable.ic_play_circle_outline_white_120dp);
        // The default int value for the audio is just 0
        int audioResourceId = intent.getIntExtra(MainActivity.PODCAST_AUDIO, 0);

        /*
         * Placing the correct Image, Title, Author and Audio for the specific podcast into the
         * layout.
         */
        ImageView art = findViewById(R.id.player_pod_image);
        art.setImageResource(imageResourceId);
        TextView titleText = findViewById(R.id.player_title);
        titleText.setText(title);
        TextView authorText = findViewById(R.id.player_author);
        authorText.setText(author);


        final ImageButton imageButton = findViewById(R.id.pause_play);
        final int playImageID = R.drawable.ic_play_arrow_white_48dp;
        final int pauseImageID = R.drawable.ic_pause_white_48dp;

        // Create the MediaPlayer object to play the audio resource associated
        // with the current podcast
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), audioResourceId);

        // Setup a listener on the media player, so that we can stop and release the
        // media player once the sounds has finished playing.
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mAudioManager.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                synchronized (this) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            // Implementing ducking which reduces the volume, we have to regaining
                            // the volume
                            mMediaPlayer.setVolume(50, 50);

                            // When play is false perform a button click which sets the right
                            // ImageButton and pause the audio
                            if (!play) {
                                imageButton.performClick();
                            }
                        case AudioManager.AUDIOFOCUS_LOSS:
                            // When the audio focus is lost for an unknown period of time
                            // release the media player to release the memory.
                            releaseMediaPlayer();

                            // Set the button image to play and stop playing the audio
                            imageButton.performClick();

                            // Set play back to true so that the next thing that would run is play
                            // when the button is clicked
                            play = true;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            // When the audio focus is lost for a short amount of time
                            // check if play is false
                            // Perform a click of the pause_play button
                            if (!play) {
                                imageButton.performClick();
                            }
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            // When the audio focus is lost for a short time and it allows ducking
                            // lower the volume
                            mMediaPlayer.setVolume(10, 10);
                    }
                }
            }
        }, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switching between play and pause buttons when clicked
                if (play) {
                    // Set the button image to pause and start playing the audio
                    imageButton.setImageResource(pauseImageID);
                    mMediaPlayer.start();
                } else {
                    // Set the button image to play and stop playing the audio
                    imageButton.setImageResource(playImageID);
                    mMediaPlayer.stop();
                }
                play = !play;
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

}
