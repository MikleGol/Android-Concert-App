package liber.app.android_concert_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SavedDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_IMAGE_RES = "image_res";

    private String title;
    private String description;
    private int imageRes;
    private LinearLayout linearSavedDetail;

    public static SavedDetailFragment newInstance(String title, String description, int imageRes) {
        SavedDetailFragment fragment = new SavedDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putInt(ARG_IMAGE_RES, imageRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESCRIPTION);
            imageRes = getArguments().getInt(ARG_IMAGE_RES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_detail, container, false);

        TextView titleTextView = view.findViewById(R.id.text_save_detail_title);
        TextView descriptionTextView = view.findViewById(R.id.text_save_detail_description);
        ImageView imageView = view.findViewById(R.id.image_saved_detail);
        LinearLayout linearSavedDetail = view.findViewById(R.id.linear_saved_detail);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        imageView.setImageResource(imageRes);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("darklight", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);

        Resources resources = getResources();

        if(isNightMode){
            linearSavedDetail.setBackground(resources.getDrawable(R.color.black));
            titleTextView.setTextColor(resources.getColor(R.color.white));
            descriptionTextView.setTextColor(resources.getColor(R.color.white));
        } else{
            linearSavedDetail.setBackground(resources.getDrawable(R.color.white));
            titleTextView.setTextColor(resources.getColor(R.color.black));
            descriptionTextView.setTextColor(resources.getColor(R.color.black));
        }

        return view;
    }
}
