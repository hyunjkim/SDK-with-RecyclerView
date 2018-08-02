package com.example.roman_heshten.twitterlikefeedwithsdk;

import com.longtailvideo.jwplayer.configuration.PlayerConfig;

public class Feed {

    private final PlayerConfig mPlayerConfig;

    Feed(String videoUrl) {
        mPlayerConfig = new PlayerConfig.Builder()
                .file(videoUrl)
                .controls(false)
                .build();
    }

    public PlayerConfig getVideoConfig() {
        return mPlayerConfig;
    }
}
