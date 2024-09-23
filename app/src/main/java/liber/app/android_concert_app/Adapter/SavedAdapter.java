package liber.app.android_concert_app.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import liber.app.android_concert_app.MainActivity;
import liber.app.android_concert_app.R;
import liber.app.android_concert_app.SavedDetailFragment;
import liber.app.android_concert_app.SavedFragment;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    public interface OnSavedItemClickListener {
        void onItemClicked(int position, String title, String description, int imageRes);
    }

    private List<String> titles;
    private List<Integer> images;
    private List<String> descriptions;
    private OnSavedItemClickListener listener;

    public SavedAdapter(List<String> titles, List<Integer> images, List<String> descriptions, OnSavedItemClickListener listener) {
        this.titles = titles;
        this.images = images;
        this.descriptions = descriptions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_saved, parent, false);
        return new SavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, int position) {
        holder.titleTextView.setText(titles.get(position));
        holder.imageView.setImageResource(images.get(position)); // Устанавливаем изображение
        holder.titleTextView.setText(descriptions.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClicked(position, titles.get(position), descriptions.get(position), images.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class SavedViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_saved_title); // Обновите с вашим id
            imageView = itemView.findViewById(R.id.image_saved); // Обновите с вашим id
            descriptionTextView = itemView.findViewById(R.id.text_saved_description);
        }
    }
}