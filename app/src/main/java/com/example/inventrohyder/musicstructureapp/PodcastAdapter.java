package com.example.inventrohyder.musicstructureapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PodcastAdapter extends ArrayAdapter<Podcast> {

    // Get the {@link Context} of the app to use when applying custom fonts
    private Context mContext;

    PodcastAdapter(Context context, ArrayList<Podcast> podcastArrayList) {
        super(context, 0, podcastArrayList);
        this.mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder();

            holder.pod_title = convertView.findViewById(R.id.main_title);
            holder.pod_author = convertView.findViewById(R.id.main_author);
            holder.pod_image = convertView.findViewById(R.id.main_pod_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Podcast currentPodcast = getItem(position);

        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.audiowide);

        // Ensure that current {@link Podcast} is not null so that the image and title can be set
        assert currentPodcast != null;
        holder.pod_image.setImageResource(currentPodcast.getImageResourceId());
        holder.pod_title.setText(currentPodcast.getTitle());
        holder.pod_title.setTypeface(typeface);

        holder.pod_author.setText(currentPodcast.getAuthor());
        holder.pod_author.setTypeface(typeface, Typeface.ITALIC);

        return convertView;
    }

    // Inner class to implement the {@link ViewHolder} pattern to improve load times
    public static class ViewHolder {
        ImageView pod_image;
        TextView pod_title;
        TextView pod_author;
    }
}
