package liber.app.android_concert_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liber.app.android_concert_app.Adapter.CategoryAdapter;
import liber.app.android_concert_app.Adapter.CountryAdapter;
import liber.app.android_concert_app.Adapter.PopularAdapter;
import liber.app.android_concert_app.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private boolean isNightMode;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireActivity().getSharedPreferences("darklight", MODE_PRIVATE);
        isNightMode = sharedPreferences.getBoolean("NightMode", false);
        if (isNightMode) {
            requireActivity().setTheme(R.style.AppTheme_Dark);
        } else {
            requireActivity().setTheme(R.style.AppTheme);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setupRecyclerViews();

        return view;
    }

    private void setupRecyclerViews() {
        // Category RecyclerView
        List<String> categoryTitles = List.of(getString(R.string.all), getString(R.string.electro), getString(R.string.heavy_metal));
        List<Integer> categoryImages = List.of(R.drawable.category_1, R.drawable.category_2, R.drawable.category_3);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryTitles, categoryImages);
        setupRecyclerView(binding.recyclerCategory, categoryAdapter, LinearLayoutManager.HORIZONTAL);

        // Country RecyclerView
        List<Integer> countryImages = List.of(R.drawable.country_1, R.drawable.country_2);
        CountryAdapter countryAdapter = new CountryAdapter(countryImages);
        setupRecyclerView(binding.recyclerCountry, countryAdapter, LinearLayoutManager.HORIZONTAL);

        // Popular RecyclerView
        List<String> popularTitles = List.of("Arctic Monkeys", "Bruce Springsteen", "Bryan Adams");
        List<Integer> popularImages = List.of(R.drawable.popular_1, R.drawable.popular_2, R.drawable.popular_3);
        List<String> popularDescriptions = List.of(
                "Parc del Forum, Barcelona",
                "Palau Sant Jordi, Barcelo...",
                "Palau Sant Jordi, Barcelo..."
        );
        PopularAdapter popularAdapter = new PopularAdapter(popularTitles, popularImages, popularDescriptions);
        setupRecyclerView(binding.recyclerPopular, popularAdapter, LinearLayoutManager.HORIZONTAL);
    }

    private void setupRecyclerView(androidx.recyclerview.widget.RecyclerView recyclerView,
                                   androidx.recyclerview.widget.RecyclerView.Adapter adapter,
                                   int orientation) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), orientation, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
