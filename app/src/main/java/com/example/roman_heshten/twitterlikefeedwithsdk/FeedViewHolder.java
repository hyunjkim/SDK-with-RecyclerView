package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.core.PlayerState;


public class FeedViewHolder extends RecyclerView.ViewHolder{

    private JWPlayerView mPlayerView;
    private View mRoot;
    private Button button;
    private final String TAG = "HYUNJOO";

    FeedViewHolder(View itemView) {
        super(itemView);
        mRoot = itemView;
        mPlayerView = itemView.findViewById(R.id.jw_player);
        button = itemView.findViewById(R.id.button);
    }

//    public void bindViewHolder(final Feed feed, boolean isActive) {
//        updateBackground(isActive);
//        if (isActive) {
//            setupPlayer(feed.getVideoConfig());
//        } else {
//            stopPlayer();
//        }
//    }

    public void bindViewHolder(final Feed feed, boolean isActive, final int curr) {
        updateBackground(isActive);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.updateList(curr);
                setupPlayer(feed.getVideoConfig());
            }
        });
        if (!isActive) {
            stopPlayer();
        }
    }

    public void onResume() {
        mPlayerView.onResume();
        mPlayerView.play();
    }

    public void onPause() {
        mPlayerView.pause();
        mPlayerView.onPause();
    }

    public void onDestroy() {
        mPlayerView.stop();
        mPlayerView.onDestroy();
    }

    private void setupPlayer(final PlayerConfig playerConfig) {
        mPlayerView.setup(playerConfig);
        mPlayerView.play();
    }

    // Do not stop and release player in case we do no need it.
    private void stopPlayer() {
        if (mPlayerView.getState() == PlayerState.PLAYING
                || mPlayerView.getState() == PlayerState.BUFFERING) {
            mPlayerView.stop();
        }
    }

    private void updateBackground(boolean isActive) {
        final Context context = itemView.getContext();
        final int color = isActive ? R.color.colorActive : android.R.color.transparent;
        itemView.setBackgroundColor(ContextCompat.getColor(context, color));
    }

}
