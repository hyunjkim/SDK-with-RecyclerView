# Using JWPlayerView in RecyclerView

To implement RecyclerView with JWPlayer SDK, first of all you should create `RecyclerView.Adapter<T>` that overrides 
```
onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads)
```
The main reason about that is to not show blinking animation of view when it rebinds. 

Then you should create your own `RecyclerView.ViewHolder`. The holder should have 4 methods:
```
// this method should be called when setting the view up or releasing player
public void bindViewHolder(final Feed feed);
// lifecycle methods for stopping and resuming 
// playback when app goes backrgound / foreground 
public void onResume(); 
public void onPause();
public void onDestroy();
```
The main thing when implementing Twitter like feed is not to forget about releasing player before player's view is still on the screen. So to make this happen you should call `stop()` before view is recycled.
