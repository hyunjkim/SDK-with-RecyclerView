# Using JWPlayerView in RecyclerView

To implement RecyclerView with JWPlayer SDK, first of all you should create `RecyclerView.Adapter<T>` that overrides 
```java
onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads)
```
The main reason about that is to not show blinking animation of view when it rebinds. 

Then you should create your own `RecyclerView.ViewHolder`. The holder should have 4 methods:
```java
// this method should be called when setting the player up or releasing it
public void bindViewHolder(final Feed feed);
// lifecycle methods for stopping and resuming 
// playback when app goes backrgound / foreground 
public void onResume(); 
public void onPause();
public void onDestroy();
```
The main thing when implementing `JWPlayerView` with `RecyclerView` is not to forget about releasing player before player's view is still on the screen. So to make this happen you should call `stop()` before view is recycled. For this case you should calculate items position and stop playback if needed. You can see in demo app  `RecyclerView.OnScrollListener` that stops playback in case of any scroll event and after this event is finished and `RecyclerView` is in `IDLE` state, setup and start playback for first visible item.

