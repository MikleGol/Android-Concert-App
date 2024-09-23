package liber.app.android_concert_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    private SwitchCompat switchDayNight;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView textLight;
    private TextView textDark;
    private TextView textMyAccount;
    private TextView textAppearance;
    private LinearLayout linearDayNight;
    private LinearLayout linearProfile;
    private LinearLayout linearEditTextProfile;
    private LinearLayout linearAppearance;
private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        switchDayNight = view.findViewById(R.id.switch_day_night);
        textLight = view.findViewById(R.id.text_light);
        textDark = view.findViewById(R.id.text_dark);
        linearProfile = view.findViewById(R.id.linear_profile);
        linearDayNight = view.findViewById(R.id.linear_profile_light_dark);
        textMyAccount = view.findViewById(R.id.text_test);
        linearEditTextProfile = view.findViewById(R.id.linear_edit_text_profile);
        linearAppearance = view.findViewById(R.id.linear_appearance);
        textAppearance = view.findViewById(R.id.text_appearance);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);



        // Получаем SharedPreferences для сохранения состояния переключателя
        sharedPreferences = getActivity().getSharedPreferences("darklight", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Устанавливаем начальное состояние переключателя
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);
        switchDayNight.setChecked(isNightMode);

        // Устанавливаем тему в зависимости от сохраненного значения
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //updateTextViews(isNightMode);
            updateUIForTheme(isNightMode);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //updateTextViews(isNightMode);
            updateUIForTheme(isNightMode);
        }

        // Обрабатываем нажатие на SwitchCompat
        switchDayNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("NightMode", true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("NightMode", false);
            }
            editor.apply();
        });

        textLight.setOnClickListener(v -> {
            switchDayNight.setChecked(false);
        });

        textDark.setOnClickListener(v -> {
            switchDayNight.setChecked(true);
        });

        return view;
    }

    private void updateUIForTheme(boolean isNightMode) {
        Resources res = getResources();

        // Меняем фон и цвета элементов программно
        if (isNightMode) {
            linearProfile.setBackgroundColor(res.getColor(R.color.black));
            linearDayNight.setBackground(res.getDrawable(R.drawable.linear_transparent_bg_dark));
            textMyAccount.setTextColor(res.getColor(R.color.white));
            linearAppearance.setBackground(res.getDrawable(R.drawable.edit_text_transparent_bg_dark));
            textAppearance.setTextColor(res.getColor(R.color.white));
            textDark.getCompoundDrawables()[0].setTint(res.getColor(R.color.white));
            textLight.getCompoundDrawables()[0].setTint(res.getColor(R.color.white));
            textDark.setTextColor(res.getColor(R.color.white));
            textLight.setTextColor(res.getColor(R.color.white));

            textLight.setBackgroundResource(R.drawable.switch_unselected);
            textDark.setBackgroundResource(R.drawable.switch_selected_dark);


        } else {
            linearProfile.setBackgroundColor(res.getColor(R.color.grey_transparent_light));
            linearDayNight.setBackground(res.getDrawable(R.drawable.linear_transparent_bg_light));
            textMyAccount.setTextColor(res.getColor(R.color.black));
            linearEditTextProfile.setBackground(res.getDrawable(R.drawable.edit_text_transparent_bg_light));
            linearAppearance.setBackground(res.getDrawable(R.drawable.edit_text_transparent_bg_light));
            textAppearance.setTextColor(res.getColor(R.color.black));
            textDark.getCompoundDrawables()[0].setTint(res.getColor(R.color.black));
            textLight.getCompoundDrawables()[0].setTint(res.getColor(R.color.black));
            textDark.setTextColor(res.getColor(R.color.black));
            textLight.setTextColor(res.getColor(R.color.black));

            textLight.setBackgroundResource(R.drawable.switch_selected_light);
            textDark.setBackgroundResource(R.drawable.switch_unselected);

            bottomNavigationView.setBackgroundResource(R.drawable.nav_bg_light);
        }
    }

}
