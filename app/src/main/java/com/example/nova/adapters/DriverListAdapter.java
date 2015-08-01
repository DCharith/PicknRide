package com.example.nova.adapters;

/**
 * Created by Nova on 2015-05-23.
 */
import com.example.nova.picknride.R;
import com.example.nova.models.Driver;
import com.example.nova.picknride.AppController;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

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
            convertView = inflater.inflate(R.layout.driver_list_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        if(rating.getText()=="")
            Log.d("Rating","RATING FIELD OK");
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        Button choose = (Button) convertView.findViewById(R.id.chooseDriver);

        // getting movie data for the row
        Driver m = driverItems.get(position);

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

        // release year
        //year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}
