package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    public static final String ACTION_PLAY = "play";
    public static final String ACTION_STOP = "stop";
    public static final String ACTION_LIFECYCLE_PAUSE = "lc_pause";
    public static final String ACTION_LIFECYCLE_RESUME = "lc_resume";
    public static final String ACTION_LIFECYCLE_DESTROY = "lc_destroy";

    private List<Feed> mFeedList;

    private int mActivePosition;

    FeedAdapter(List<Feed> feed, int activePosition) {
        mFeedList = feed;
        mActivePosition = activePosition;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View feedView = layoutInflater.inflate(R.layout.feed_view, parent, false);
        return new FeedViewHolder(feedView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        final boolean isActive = mActivePosition == holder.getAdapterPosition();
        holder.bindViewHolder(mFeedList.get(position), isActive);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            final String payload = (String) payloads.get(0);

            if (ACTION_LIFECYCLE_RESUME.equals(payload)) {
                holder.onResume();
            } else if (ACTION_LIFECYCLE_PAUSE.equals(payload)) {
                holder.onPause();
            } else if (ACTION_LIFECYCLE_DESTROY.equals(payload)) {
                holder.onDestroy();
            } else {
                super.onBindViewHolder(holder, position, payloads);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public void setPositions(int selectedPosition) {
        mActivePosition = selectedPosition;
    }

}
