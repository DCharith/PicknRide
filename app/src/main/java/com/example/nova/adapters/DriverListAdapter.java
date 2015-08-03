package com.example.nova.adapters;

/**
 * Created by Nova on 2015-05-23.
 */
import com.example.nova.picknride.R;
import com.example.nova.models.Driver;
import com.example.nova.picknride.AppController;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.nova.picknride.ViewProfileActivity;

public class DriverListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Driver> driverItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public DriverListAdapter(Activity activity, List<Driver> driverItems) {
        this.activity = activity;
        this.driverItems = driverItems;
    }

    @Override
    public int getCount() {
        return driverItems.size();
    }

    @Override
    public Object getItem(int location) {
        return driverItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_driver, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        Button choose = (Button) convertView.findViewById(R.id.chooseDriver);
        RelativeLayout driverListItem = (RelativeLayout) convertView.findViewById(R.id.driver_list_item);

        // getting driver data for the row
        final Driver m = driverItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());
        Log.d("rating", Double.toString(m.getRating()));

        // rating
        rating.setText("Rating: " + m.getRating());
        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);



        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, ViewProfileActivity.class);
                i.putExtra("thumbnailUrl", m.getThumbnailUrl());
                i.putExtra("name", m.getTitle());
                i.putExtra("rating", m.getRating());
                activity.startActivity(i);
            }
        });

        driverListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(activity, ViewProfileActivity.class);
//                i.putExtra("thumbnailUrl", m.getThumbnailUrl());
//                i.putExtra("name", m.getTitle());
//                i.putExtra("rating", m.getRating());
//                activity.startActivity(i);
                final Dialog dialog = new Dialog(activity, R.style.cust_dialog);
                dialog.setContentView(R.layout.dialog_ride_details);
                dialog.setTitle("Ride Details");

                NetworkImageView thumbnail = (NetworkImageView)dialog.findViewById(R.id.thumbnail);
                thumbnail.setImageUrl(m.getThumbnailUrl(), imageLoader);
                TextView name = (TextView)dialog.findViewById(R.id.name);
                name.setText(m.getTitle());
                TextView rating = (TextView)dialog.findViewById(R.id.rating);
                rating.setText("Rating: "+m.getRating());
                dialog.show();

                Button viewProfile = (Button)dialog.findViewById(R.id.view_profile);
                viewProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(activity, ViewProfileActivity.class);
                        i.putExtra("thumbnailUrl", m.getThumbnailUrl());
                        i.putExtra("name", m.getTitle());
                        i.putExtra("rating", m.getRating());
                        activity.startActivity(i);

                    }
                });
            }
        });
        // release year
        //year.setText(String.valueOf(m.getYear()));
        return convertView;
    }



}
