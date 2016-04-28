package com.quoteoftheday.raccoonapps.diploma;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.LoginFragment;
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnLoginListener;

public class LoginActivity extends AppCompatActivity implements OnLoginListener {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment = new LoginFragment();
        fragment.listener = this;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void login(User user) {
        if (user != null) {
            Intent intent;
            if (user.getLogin().equals("admin")) {
                intent = new Intent(this, AdminActivity.class);
            } else {
                intent = new Intent(this, ExpertActivity.class);
            }
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Ошибка, неправильный логин или пароль", Toast.LENGTH_SHORT).show();
        }
    }
}
