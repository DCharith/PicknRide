package com.example.nova.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.nova.models.Comment;
import com.example.nova.picknride.AppController;
import com.example.nova.picknride.R;

import java.util.List;

/**
 * Created by Nova on 2015-08-01.
 */
public class CommentListAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Comment> commentItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CommentListAdapter(Activity activity, List<Comment> commentItems){
        this.activity = activity;
        this.commentItems = commentItems;
    }

    @Override
    public int getCount() {
        return commentItems.size();
    }

    @Override
    public Object getItem(int i) {
        return commentItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_comment, null);
        if (imageLoader==null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbnail = (NetworkImageView)convertView.findViewById(R.id.thumbnail);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView rating = (TextView)convertView.findViewById(R.id.rating);
        TextView commentTv = (TextView)convertView.findViewById(R.id.comment);

        Comment comment = commentItems.get(position);
        thumbnail.setImageUrl(comment.getThumbnailUrl(), imageLoader);
        Log.d("ThumbNailUrl", comment.getThumbnailUrl());
        name.setText(comment.getName());
        rating.setText("Rating: "+String.valueOf(comment.getRating()));
        commentTv.setText(comment.getComment());


        return convertView;
    }
}
