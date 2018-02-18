
/*
 * Created by Elena on 2/18/18 6:34 AM
 * Copyright (c) 2018 . All rights reserved.
 */

package com.example.android.singlescreenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    String myVideoAPIkey = "AIzaSyCo1hEZDtD6mUmTp1PO2w_9Yiuf6zy2UUc";
    String videoURL = "0GG-GlGU1zA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVideo();

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * starts playing the video on top of the app
     */
    private void setVideo() {
        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.kittens_licking_paws);
        youtubeFragment.initialize(myVideoAPIkey,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        //play video
                        youTubePlayer.loadVideo(videoURL);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
    }

    /**
     * Adds a marker in Upes 2a, Riga.
     * Moves the map's camera to the location and zooms in.
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng address = new LatLng(56.975948, 24.137677);
        googleMap.addMarker(new MarkerOptions().position(address)
                .title(getString(R.string.company_name)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    /**
     * sets the layout with cats visible, and the layout with other animals invisible
     *
     * @param view
     */
    public void showCats(View view) {
        findViewById(R.id.cats_view).setVisibility(View.VISIBLE);
        findViewById(R.id.others_view).setVisibility(View.GONE);
    }

    /**
     * sets the layout with other animals visible, and the layout with cats invisible
     *
     * @param view
     */
    public void showOthers(View view) {
        findViewById(R.id.others_view).setVisibility(View.VISIBLE);
        findViewById(R.id.cats_view).setVisibility(View.GONE);
    }
}
