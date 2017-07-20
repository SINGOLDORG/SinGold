package com.example.singold.MyActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.singold.R;
import com.example.singold.data.YoutubeConnector;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerYouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView playerView;
    private YouTubePlayer myPlayer;


    @Override
        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);

            setContentView(R.layout.activity_player);

            playerView = (YouTubePlayerView)findViewById(R.id.player_view);
           // playerView.setVisibility(View.GONE);
            playerView.initialize(YoutubeConnector.KEY, this);
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider,
                YouTubeInitializationResult result) {
            Toast.makeText(this, "play youtube failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                            boolean restored) {
            if(!restored){
                myPlayer=player;
                myPlayer.loadVideo(getIntent().getStringExtra("VIDEO_ID"));
               // myPlayer.play();
            }
        }

    @Override
    protected void onPause() {
        super.onPause();
        myPlayer.pause();
    }
}
