package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private final static String EXTRA_POSITION = "extra_pos_";

    private RecyclerView mRecyclerView;
//    private ScrollListener mScrollListener;
    private static int mSelectedPosition;
    private static FeedAdapter mFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mSelectedPosition = savedInstanceState.getInt(EXTRA_POSITION);
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
        destroyInstances();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_POSITION, mSelectedPosition);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupRecyclerView(int selectedPosition) {
//        mScrollListener = new ScrollListener();
        mFeedAdapter = createFeedAdapter(selectedPosition);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mFeedAdapter);
//        mRecyclerView.addOnScrollListener(mScrollListener);

    }

    private void destroyInstances() {
        updateListWithLifecycleEvent(FeedAdapter.ACTION_LIFECYCLE_DESTROY);
        mRecyclerView.setAdapter(null);
//        mRecyclerView.removeOnScrollListener(mScrollListener);
//        mScrollListener = null;
    }

    private FeedAdapter createFeedAdapter(int selectedPosition) {
        List<Feed> feed = FeedGenerator.generateFeedList();
        return new FeedAdapter(feed, selectedPosition);
    }

    private void calculateTopItemAndUpdateRecycler(RecyclerView recyclerView) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

        updateList(manager.findFirstCompletelyVisibleItemPosition());
    }

    public static void updateList(final int selectedPosition) {
        if (mSelectedPosition != selectedPosition) {
            mFeedAdapter.setActivePosition(selectedPosition);
            //add payloads to not call animation

            mFeedAdapter.notifyItemChanged(selectedPosition, FeedAdapter.ACTION_PLAY);
            mFeedAdapter.notifyItemChanged(mSelectedPosition, FeedAdapter.ACTION_STOP);
            mSelectedPosition = selectedPosition;
        }
    }

    private void updateListWithLifecycleEvent(String event) {
        mFeedAdapter.notifyItemChanged(this.mSelectedPosition, event);
    }

//    private class ScrollListener extends RecyclerView.OnScrollListener {
//
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//
//            // currently not scrolling
//            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                Log.i("HYUNJOO","onScrollStateChanged - clicked position: " + mFeedAdapter.getClickedPosition() );
//                calculateTopItemAndUpdateRecycler(recyclerView);
//            }
//
//            // currently scrolling
//            if (newState == RecyclerView.SCROLL_STATE_SETTLING
//                    || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                updateList(FeedAdapter.POSITION_NONE);
//            }
//        }
//    }

}
