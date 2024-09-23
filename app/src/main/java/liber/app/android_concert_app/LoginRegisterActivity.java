package liber.app.android_concert_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginRegisterActivity extends AppCompatActivity {

    LoginFragment loginFragment = new LoginFragment();
    RegisterFragment registerFragment = new RegisterFragment();
    Boolean loginPage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        if(savedInstanceState != null){
            loginPage = savedInstanceState.getBoolean("login_page");
        }

        if(loginPage){
            showFragment(loginFragment);
        } else {
            showFragment(registerFragment);
        }

        TextView registerText = findViewById(R.id.register_text);
        TextView loginText = findViewById(R.id.login_text);

        SwitchCompat switchToggle = findViewById(R.id.switch_toggle);
        AppCompatButton btnSignIn = findViewById(R.id.btn_sign_in);

        TextView textViewForgotPassword =findViewById(R.id.text_forgot_password);
        TextView textViewUrlRemember =findViewById(R.id.text_url_remember);
        TextView textViewDontHaveAccount =findViewById(R.id.text_dont_have_account);
        TextView textViewUrlRegister =findViewById(R.id.text_url_register);

        switchToggle.setChecked(loginPage);

        switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                loginText.setTextColor(getResources().getColor(android.R.color.white));
                loginText.setBackgroundResource(R.drawable.switch_selected_dark);

                registerText.setTextColor(getResources().getColor(android.R.color.white));
                registerText.setBackgroundResource(R.drawable.switch_unselected);

                textViewForgotPassword.setText(R.string.forgot_password);
                textViewUrlRemember.setText(R.string.remember);
                textViewDontHaveAccount.setText(R.string.don_t_have_an_account);
                textViewUrlRegister.setText(R.string.register);

                showFragment(loginFragment);
            } else {

                registerText.setTextColor(getResources().getColor(android.R.color.white));
                registerText.setBackgroundResource(R.drawable.switch_selected_dark);

                loginText.setTextColor(getResources().getColor(android.R.color.white));
                loginText.setBackgroundResource(R.drawable.switch_unselected);

                textViewForgotPassword.setText(R.string.by_signing_up_you_accept_our);
                textViewUrlRemember.setText(R.string.conditions);
                textViewDontHaveAccount.setText(R.string.have_an_account);
                textViewUrlRegister.setText(R.string.login);

                showFragment(registerFragment);
            }
        });

        registerText.setOnClickListener(v -> {
            switchToggle.setChecked(false);
        });

        loginText.setOnClickListener(v -> {
            switchToggle.setChecked(true);
        });

        textViewUrlRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToggle.setChecked(!switchToggle.isChecked());
            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linear_fragments, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("login_page", loginPage);
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
