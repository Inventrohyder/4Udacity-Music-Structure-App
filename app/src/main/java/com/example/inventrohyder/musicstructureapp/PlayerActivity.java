package com.example.inventrohyder.musicstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class PlayerActivity extends AppCompatActivity {

    // Allows the play button to switch between play and pause icons
    boolean play = true;

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
        // The default int value for the image is the idea of the application icon
        int imageResourceId = intent.getIntExtra(MainActivity.PODCAST_ART, R.drawable.ic_launcher);

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

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switching between play and pause buttons when clicked
                if (play) {
                    imageButton.setImageResource(pauseImageID);
                } else {
                    imageButton.setImageResource(playImageID);
                }
                play = !play;
            }
        });
    }
}
