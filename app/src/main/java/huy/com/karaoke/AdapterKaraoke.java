package huy.com.karaoke;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import huy.com.karaoke.Model.Item;

public class AdapterKaraoke extends RecyclerView.Adapter<AdapterKaraoke.ViewHolder> {
    private Context context;
    private ArrayList<Item> itemArrayList;

    public AdapterKaraoke(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(itemArrayList.get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .into(holder.imageView);
        holder.title.setText(itemArrayList.get(position).getSnippet().getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YoutubePlayerView.class);
                intent.putExtra("ID_Video", String.valueOf(itemArrayList.get(position).getId().getVideoId()));
                Log.e("AAA", String.valueOf(itemArrayList.get(position).getId().getVideoId()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_thumbnail);
            title = itemView.findViewById(R.id.textView_title);

        }
    }
}
