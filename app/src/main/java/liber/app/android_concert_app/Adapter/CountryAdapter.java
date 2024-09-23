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

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<Integer> images; // Список для хранения идентификаторов изображений

    // Конструктор принимает два списка: один для названий, другой для изображений
    public CountryAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position)); // Устанавливаем изображение
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_category); // Обновите с вашим id
        }
    }
}
