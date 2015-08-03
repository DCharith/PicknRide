package com.example.nova.picknride;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.nova.adapters.CommentListAdapter;
import com.example.nova.adapters.DriverListAdapter;
import com.example.nova.models.Comment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ViewProfileActivity extends Activity {
    ImageLoader imageLoader;
    CommentListAdapter adapter;
    List<Comment> commentList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbnail = (NetworkImageView)findViewById(R.id.thumbnail);
        TextView name = (TextView)findViewById(R.id.name);
        TextView rating = (TextView)findViewById(R.id.rating);
        TextView genderAge = (TextView)findViewById((R.id.gender_age));
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null) {
            thumbnail.setImageUrl(b.get("thumbnailUrl").toString(), imageLoader);
            name.setText(b.get("name").toString());
            rating.setText("Rating: "+b.get("rating").toString());
            genderAge.setText("Male, 25");
        }

        final LinearLayout collapsibleLayout = (LinearLayout)findViewById(R.id.show_more_hidden_layout);
        final ImageButton showMore = (ImageButton)findViewById(R.id.show_more_button);
        showMore.setBackgroundResource(R.mipmap.ic_expand);
//        final RelativeLayout showMoreRelativeLayout = (RelativeLayout)findViewById(R.id.show_more_relative);
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //b=checked
                if (collapsibleLayout.getVisibility()==View.GONE) {
                    //showMore.setImageDrawable(getDrawable(R.id.search_button));
//                    showMoreRelativeLayout.setMinimumHeight(150);
                    collapsibleLayout.setVisibility(View.VISIBLE);
                    showMore.setBackgroundResource(R.mipmap.ic_collapse);
                }

                else {
                    collapsibleLayout.setVisibility(View.GONE);
                    showMore.setBackgroundResource(R.mipmap.ic_expand);
//                    showMoreRelativeLayout.setMinimumHeight(0);
                }
            }

        });



        adapter = new CommentListAdapter(this, commentList);
        listView = (ListView)findViewById(R.id.comment_list);
        listView.setAdapter(adapter);

        Comment c1 = new Comment("http://api.androidhive.info/json/movies/1.jpg", "John Doe", 9.5 , "This Guy is Freaking aweosme!");
        Comment c2 = new Comment("http://api.androidhive.info/json/movies/3.jpg", "Charith Epitawatta", 9.5 , "This Guy is Freaking aweosme!");
        Comment c3 = new Comment("http://api.androidhive.info/json/movies/5.jpg", "Thilina Madusanka", 9.5 , "This Guy is Freaking aweosme!");
        Comment c4 = new Comment("http://api.androidhive.info/json/movies/6.jpg", "Subhashini Wijerathne", 9.5 , "This Guy is Freaking aweosme!");
        Comment c5 = new Comment("http://api.androidhive.info/json/movies/7.jpg", "Lahiru Sandaruwan", 9.5 , "This Guy is Freaking aweosme!");
        Comment c6 = new Comment("http://api.androidhive.info/json/movies/8.jpg", "Fareed Isfan", 9.5 , "I speak better sinhalese than this guy! #idiot!");
        Comment c7 = new Comment("http://api.androidhive.info/json/movies/9.jpg", "Tharindu Kandegedara", 9.5 , "This Guy is Freaking aweosme! Umba beela gedara palayan pawu nodee!");

        commentList.add(c1);
        commentList.add(c2);
        commentList.add(c3);
        commentList.add(c4);
        commentList.add(c5);
        commentList.add(c6);
        commentList.add(c7);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
