package com.example.inventrohyder.musicstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // {@link final} and {@link static} {@link String} names to pass in explicit intents and access
    // them from other activities
    public static final String PODCAST_TITLE = "PODCAST_TITLE";
    public static final String PODCAST_AUTHOR = "PODCAST_AUTHOR";
    public static final String PODCAST_ART = "PODCAST_ART";
    public static final String PODCAST_AUDIO = "PODCAST_AUDIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podcast_list);

        // {@link ArrayList} containing {@link Podcast} objects
        final ArrayList<Podcast> podcasts = new ArrayList<>();
        podcasts.add(new Podcast(getString(R.string.the_angel), getString(R.string.the_angel_author),
                R.drawable.the_angel, R.raw.the_angel));
        podcasts.add(new Podcast(getString(R.string.the_heat),
                getString(R.string.the_heat_author), R.raw.the_heat));
        podcasts.add(new Podcast(getString(R.string.the_house),
                getString(R.string.the_house_author), R.drawable.the_house, R.raw.the_house));
        podcasts.add(new Podcast(getString(R.string.the_extra),
                getString(R.string.the_extra_author), R.drawable.the_extra, R.raw.the_extra));
        podcasts.add(new Podcast(getString(R.string.the_rocker),
                getString(R.string.the_rocker_author), R.raw.the_rocker));
        podcasts.add(new Podcast(getString(R.string.the_egyptian),
                getString(R.string.the_egyptian_author),
                R.drawable.the_egyptian, R.raw.the_egyptian));
        podcasts.add(new Podcast(getString(R.string.the_waffles),
                getString(R.string.the_waffles_author), R.drawable.the_waffles, R.raw. the_waffles));
        podcasts.add(new Podcast(getString(R.string.the_beat),
                getString(R.string.the_beat_author), R.raw.the_beat));
        podcasts.add(new Podcast(getString(R.string.the_king),
                getString(R.string.the_king_author), R.drawable.the_king, R.raw.the_king));
        podcasts.add(new Podcast(getString(R.string.the_jupyter_notebook),
                getString(R.string.the_jupyter_notebook_author),
                R.drawable.the_jupyter_notebook, R.raw.the_jupyter_notebook));
        podcasts.add(new Podcast(getString(R.string.the_stone),
                getString(R.string.the_stone_author), R.drawable.the_stone, R.raw.the_stone));
        podcasts.add(new Podcast(getString(R.string.the_hit),
                getString(R.string.the_hit_author), R.raw.the_hit));

        // Custom {@link ArrayAdapter} to handle the different views required for the {@link Podcast} objects
        PodcastAdapter podcastAdapter = new PodcastAdapter(getApplicationContext(), podcasts);

        // The {@link ListView} that will have our custom adapter
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(podcastAdapter);


        // {@link OnItemClickListener} to handle events when each specific list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Podcast podcast = podcasts.get(position);

                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra(PODCAST_TITLE, podcast.getTitle());
                intent.putExtra(PODCAST_AUTHOR, podcast.getAuthor());
                intent.putExtra(PODCAST_ART, podcast.getImageResourceId());
                intent.putExtra(PODCAST_AUDIO, podcast.getAudioResourceId());
                startActivity(intent);
            }
        });
    }
}
