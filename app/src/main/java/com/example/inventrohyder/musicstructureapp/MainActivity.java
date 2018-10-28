package com.example.inventrohyder.musicstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // {@final} and {@static} {@String} names to pass in explicit intents and access them from
    // other activities
    public static final String PODCAST_TITLE = "PODCAST_TITLE";
    public static final String PODCAST_AUTHOR = "PODCAST_AUTHOR";
    public static final String PODCAST_ART = "PODCAST_ART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podcast_list);

        // Arraylist containing podcast objects
        final ArrayList<Podcast> podcasts = new ArrayList<>();
        podcasts.add(new Podcast(getString(R.string.the_angel), getString(R.string.the_angel_author), R.drawable.the_angel));
        podcasts.add(new Podcast(getString(R.string.the_heat), getString(R.string.the_heat_author)));
        podcasts.add(new Podcast(getString(R.string.the_house), getString(R.string.the_house_author), R.drawable.the_house));
        podcasts.add(new Podcast(getString(R.string.the_extra), getString(R.string.the_extra_author), R.drawable.the_extra));
        podcasts.add(new Podcast(getString(R.string.the_rocker), getString(R.string.the_rocker_author)));
        podcasts.add(new Podcast(getString(R.string.the_egyptian), getString(R.string.the_egyptian_author), R.drawable.the_egyptian));
        podcasts.add(new Podcast(getString(R.string.the_waffles), getString(R.string.the_waffles_author), R.drawable.the_waffles));
        podcasts.add(new Podcast(getString(R.string.the_beat), getString(R.string.the_beat_author)));
        podcasts.add(new Podcast(getString(R.string.the_king), getString(R.string.the_king_author), R.drawable.the_king));
        podcasts.add(new Podcast(getString(R.string.the_jupyter_notebook), getString(R.string.the_jupyter_notebook_author), R.drawable.the_jupyter_notebook));
        podcasts.add(new Podcast(getString(R.string.the_stone), getString(R.string.the_stone_author), R.drawable.the_stone));
        podcasts.add(new Podcast(getString(R.string.the_hit), getString(R.string.the_hit_author)));

        // Custom {@ArrayAdapter} to handle the different views required for the podcast objects
        PodcastAdapter podcastAdapter = new PodcastAdapter(getApplicationContext(), podcasts);

        // The {@ListView} that will have our custom adapter
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(podcastAdapter);


        // {@OnItemClickListener} to handle events when each specific list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Podcast podcast = podcasts.get(position);

                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra(PODCAST_TITLE, podcast.getTitle());
                intent.putExtra(PODCAST_AUTHOR, podcast.getAuthor());
                intent.putExtra(PODCAST_ART, podcast.getImageResourceId());
                startActivity(intent);
            }
        });
    }
}
