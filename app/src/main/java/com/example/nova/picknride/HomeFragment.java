package com.example.nova.picknride;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

public class HomeFragment extends Fragment{

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Button passengerMode = (Button)rootView.findViewById(R.id.passengerModeButton2) ;
        passengerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapsActivity.class);
                i.putExtra("driverMode", false);
                startActivity(i);
            }
        });

        Button driverMode = (Button)rootView.findViewById(R.id.driverModeButton2);
        driverMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), MapsActivity.class);
                i.putExtra("driverMode", true);
                startActivity(i);
            }
        });

        final Button goOnline = (Button)rootView.findViewById(R.id.goOnline);
        goOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goOnline.getText().equals("Go Online")) {
                    goOnline.setBackgroundColor(Color.parseColor("#FF4ABA10"));
                    goOnline.setText("Go Offline");
                }
                else {
                    goOnline.setBackgroundColor(Color.parseColor("#ffb60e12"));
                    goOnline.setText("Go Online");
                }
            }
        });

        return rootView;



    }
}