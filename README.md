# Using JWPlayerView in RecyclerView

First of all you should create 'RecyclerView.Adapter<T>' that overrides 'onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull List<Object> payloads)'. The main reason about that is to not show blinking animation of view when it rebinds. 
