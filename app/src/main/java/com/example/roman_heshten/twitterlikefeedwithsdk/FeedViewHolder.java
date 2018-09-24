package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.DisplayClickEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import static com.example.roman_heshten.twitterlikefeedwithsdk.MainActivity.updateList;


public class FeedViewHolder extends RecyclerView.ViewHolder{

    private JWPlayerView mPlayerView;

    private View mItemView;
    FeedViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mPlayerView = itemView.findViewById(R.id.jw_player);
    }

//    public void bindViewHolder(final Feed feed, boolean isActive) {
//        updateBackground(isActive);
//        if (isActive) {
//            setupPlayer(feed.getVideoConfig());
//        } else {
//            stopPlayer();
//        }
//    }

    @SuppressLint("ClickableViewAccessibility")
    public void bindViewHolder(final Feed feed, boolean isActive, final int curr) {
        Log.i("HYUNJOO", "bindViewHolder " + isActive);
        if(isActive) {
            Log.i("HYUNJOO", "bindViewHolder selected pos: " + curr);
            setupPlayer(feed.getVideoConfig());
        } else {
            stopPlayer();
        }
        updateBackground(isActive);
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
