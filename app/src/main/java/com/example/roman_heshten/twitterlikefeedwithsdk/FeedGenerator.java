package com.example.roman_heshten.twitterlikefeedwithsdk;

import java.util.ArrayList;
import java.util.List;

public class FeedGenerator {

    private final static String[] videoUrls = new String[]{
            "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8",
            "http://playertest.longtailvideo.com/jwpromo/jwpromo.m3u8",
            "http://playertest.longtailvideo.com/adaptive/more4plus1/playlist.m3u8",
            "http://content.bitsontherun.com/videos/3XnJSIm4-52qL9xLP.mp4",
            "http://cdn-videos.akamaized.net/btv/desktop/fastly/us/live/primary.m3u8",
            "http://content.jwplatform.com/videos/nhYDGoyh-el5vTWpr.mp4",
            "http://content.bitsontherun.com/videos/3XnJSIm4-injeKYZS.mp4"
    };

    public static List<Feed> generateFeedList(int size) {
        List<Feed> feedList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int urlPosition = i % videoUrls.length;
            feedList.add(new Feed(videoUrls[urlPosition]));
        }

        return feedList;
    }

}
