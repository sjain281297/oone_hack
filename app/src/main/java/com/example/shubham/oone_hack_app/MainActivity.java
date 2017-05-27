package com.example.shubham.oone_hack_app;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleExoPlayerView exoPlayerView= (SimpleExoPlayerView) findViewById(R.id.exoView);
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        LoadControl loadControl = new DefaultLoadControl();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

// 3. Create the player
        final SimpleExoPlayer player =
                ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);


        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this
                , "oone_hack_app"));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource firstSource = new ExtractorMediaSource(Uri.parse("file:///android_asset/cola.mp4"),
                dataSourceFactory, extractorsFactory, null, null);
        MediaSource secondSource = new ExtractorMediaSource(Uri.parse("file:///android_asset/fbb.mp4"),
                dataSourceFactory, extractorsFactory, null, null);

// Plays the first video, then the second video.
        ConcatenatingMediaSource concatenatedSource =
                new ConcatenatingMediaSource(firstSource, secondSource);
        LoopingMediaSource loopingMediaSource=new LoopingMediaSource(concatenatedSource);
        player.prepare(loopingMediaSource);
        exoPlayerView.setPlayer(player);



    }
}
