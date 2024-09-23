package liber.app.android_concert_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import liber.app.android_concert_app.Adapter.SavedAdapter;


public class SavedFragment extends Fragment implements SavedAdapter.OnSavedItemClickListener {

    private RecyclerView recyclerSavedConcerts;
    private LinearLayout linearSaved;
    private SharedPreferences sharedPreferences;
    private TextView textSavedConcerts;
    private boolean isNightMode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        recyclerSavedConcerts = view.findViewById(R.id.recycler_saved_concerts);
        linearSaved = view.findViewById(R.id.linear_saved);

        sharedPreferences = getActivity().getSharedPreferences("darklight", MODE_PRIVATE);
        isNightMode = sharedPreferences.getBoolean("NightMode", false);

        textSavedConcerts = view.findViewById(R.id.text_saved_concerts);

        Resources resources = getResources();

        if(isNightMode){
            linearSaved.setBackground(resources.getDrawable(R.color.black));
            textSavedConcerts.setTextColor(resources.getColor(R.color.white));
        } else{
            linearSaved.setBackground(resources.getDrawable(R.color.white));
            textSavedConcerts.setTextColor(resources.getColor(R.color.black));
        }

        // SavedConcerts Recycler View
        LinearLayoutManager layoutManagerSaved = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerSavedConcerts.setLayoutManager(layoutManagerSaved);
        List<String> titleListSaved = new ArrayList<>();
        titleListSaved.add("Arctic Monkeys");
        titleListSaved.add("Bruce Springsteen");
        titleListSaved.add("Bryan Adams");
        List<Integer> imageListSaved = new ArrayList<>();
        imageListSaved.add(R.drawable.popular_1);
        imageListSaved.add(R.drawable.popular_2);
        imageListSaved.add(R.drawable.popular_3);
        List<String> descriptionListSaved = new ArrayList<>();
        descriptionListSaved.add("Parc del Forum, Barcelona");
        descriptionListSaved.add("Palau Sant Jordi, Barcelo...");
        descriptionListSaved.add("Palau Sant Jordi, Barcelo...");
        SavedAdapter adapterSaved = new SavedAdapter(titleListSaved, imageListSaved, descriptionListSaved, this);
        recyclerSavedConcerts.setAdapter(adapterSaved);


        return view;
    }

    // Реализуем метод интерфейса для обработки кликов
    @Override
    public void onItemClicked(int position, String title, String description, int imageRes) {
        // Создаем новый фрагмент с детализацией и передаем ему данные
        SavedDetailFragment detailFragment = SavedDetailFragment.newInstance(title, description, imageRes);

        // Меняем фрагмент
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_main, detailFragment)
                .addToBackStack(null)
                .commit();
    }


}