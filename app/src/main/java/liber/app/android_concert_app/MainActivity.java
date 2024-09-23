package liber.app.android_concert_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import liber.app.android_concert_app.Adapter.CategoryAdapter;
import liber.app.android_concert_app.Adapter.SavedAdapter;

public class MainActivity extends AppCompatActivity{
    HomeFragment homeFragment = new HomeFragment();
    SavedFragment savedFragment = new SavedFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем настройки темы из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("darklight", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);

        // Устанавливаем тему в зависимости от сохраненного значения
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Загружаем первый фрагмент по умолчанию
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), false); // false для первого экрана, чтобы не было анимации
        }

        // Обрабатываем нажатия на элементы меню
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            int itemId = item.getItemId();

            if(itemId == R.id.nav_home){
                loadFragment(new HomeFragment(), true);
            } else if(itemId == R.id.nav_saved){
                loadFragment(new SavedFragment(), true);
            } else if(itemId == R.id.nav_tickets){

            } else if(itemId == R.id.nav_profile){
                loadFragment(new ProfileFragment(), true);
            }
            return false;
        });


        //showFragment(homeFragment);
    }

    private void loadFragment(Fragment fragment, boolean animate) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animate) {
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out); // Добавляем плавную анимацию
        }
        transaction.replace(R.id.frame_layout_main, fragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


}