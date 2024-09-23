package liber.app.android_concert_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import liber.app.android_concert_app.R;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    private List<String> titles;
    private List<Integer> images; // Список для хранения идентификаторов изображений
    private List<String> descriptions;

    // Конструктор принимает два списка: один для названий, другой для изображений
    public PopularAdapter(List<String> titles, List<Integer> images, List<String> descriptions) {
        this.titles = titles;
        this.images = images;
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.titleTextView.setText(titles.get(position));
        holder.imageView.setImageResource(images.get(position)); // Устанавливаем изображение
        holder.titleTextView.setText(descriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title); // Обновите с вашим id
            imageView = itemView.findViewById(R.id.image_popular); // Обновите с вашим id
            descriptionTextView = itemView.findViewById(R.id.text_description);
        }
    }
}