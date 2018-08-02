package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int FEED_SIZE = 100;
    private RecyclerView mRecyclerView;
    private ScrollListener mScrollListener;
    private int mSelectedPosition;
    private FeedAdapter mFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mSelectedPosition = savedInstanceState.getInt("pos");
        }
        setupRecyclerView(mSelectedPosition);
    }

    @Override
    protected void onResume() {
        updateListWithLifecycleEvent(FeedAdapter.ACTION_LIFECYCLE_RESUME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        updateListWithLifecycleEvent(FeedAdapter.ACTION_LIFECYCLE_PAUSE);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        updateListWithLifecycleEvent(FeedAdapter.ACTION_LIFECYCLE_DESTROY);
        mRecyclerView.setAdapter(null);
        mRecyclerView.removeOnScrollListener(mScrollListener);
        mScrollListener = null;
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("pos", mSelectedPosition);
        super.onSaveInstanceState(outState);
    }

    private void setupRecyclerView(int selectedPosition) {
        mScrollListener = new ScrollListener();
        mFeedAdapter = createFeedAdapter(FEED_SIZE, selectedPosition);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mFeedAdapter);
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private FeedAdapter createFeedAdapter(int feedListSize, int selectedPosition) {
        List<Feed> feed = FeedGenerator.generateFeedList(feedListSize);
        return new FeedAdapter(feed, selectedPosition);
    }

    private void calculateTopItemAndUpdateRecycler(RecyclerView recyclerView) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        updateList(manager.findFirstCompletelyVisibleItemPosition());
    }

    private void updateList(final int selectedPosition) {
        if (mSelectedPosition != selectedPosition) {
            mFeedAdapter.setPositions(selectedPosition);
            //add payloads to not call animation
            mFeedAdapter.notifyItemChanged(selectedPosition, FeedAdapter.ACTION_PLAY);
            mFeedAdapter.notifyItemChanged(mSelectedPosition, FeedAdapter.ACTION_STOP);
            mSelectedPosition = selectedPosition;
        }
    }

    private void updateListWithLifecycleEvent(String event) {
        mFeedAdapter.notifyItemChanged(this.mSelectedPosition, event);
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                calculateTopItemAndUpdateRecycler(recyclerView);
            }

            if (newState == RecyclerView.SCROLL_STATE_SETTLING
                    || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                updateList(-1);
            }
        }
    }

}
