# Using JWPlayerView in RecyclerView

First of all you should create `RecyclerView.Adapter<T>` that overrides 
```
onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull List<Object> payloads)
```
The main reason about that is to not show blinking animation of view when it rebinds. 

Then you should create your own `RecyclerView.ViewHolder`. The holder should have 4 methods:
```
public void bindViewHolder(final Feed feed);
public void onResume();
public void onPause();
public void onDestroy();
```
