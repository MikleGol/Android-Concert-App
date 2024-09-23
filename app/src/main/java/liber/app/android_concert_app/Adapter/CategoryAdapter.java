package liber.app.android_concert_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import liber.app.android_concert_app.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<String> titles;
    private List<Integer> images; // Список для хранения идентификаторов изображений

    // Конструктор принимает два списка: один для названий, другой для изображений
    public CategoryAdapter(List<String> titles, List<Integer> images) {
        this.titles = titles;
        this.images = images;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.titleTextView.setText(titles.get(position));
        holder.imageView.setImageResource(images.get(position)); // Устанавливаем изображение
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_category); // Обновите с вашим id
            imageView = itemView.findViewById(R.id.image_category); // Обновите с вашим id
        }
    }
}
