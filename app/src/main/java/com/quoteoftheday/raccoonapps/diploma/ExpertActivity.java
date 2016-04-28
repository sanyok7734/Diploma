package com.quoteoftheday.raccoonapps.diploma;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.FragmentSelectionKEO;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.ListHierarchiesFragment;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.ListMethodAdapterFragment;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.ListMethodKEOFragment;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.ListModelKEOFragment;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.ListUserFragment;
import com.quoteoftheday.raccoonapps.diploma.view.fragments.UserFragment;
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener;

import org.jetbrains.annotations.NotNull;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class ExpertActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMainListener{

    @Bind(R.id.container)
    FrameLayout container;

    @Bind(R.id.touch_container)
    FrameLayout touchContainer;

    @Bind(R.id.anim)
    View animView;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static float touchX = 0;
    public static float touchY = 0;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_main);
        ButterKnife.bind(this);


        toolbar.setTitle("Expert");
        setSupportActionBar(toolbar);


        navigationView.getMenu().findItem(R.id.label1).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        user = (User) getIntent().getParcelableExtra("user");
        Toast.makeText(ExpertActivity.this, "" + user.getFirstName() + " - " + user.getLastName(), Toast.LENGTH_SHORT).show();

        openFragment(new FragmentSelectionKEO());
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getFragmentManager().popBackStack();
        switch (id) {
            case R.id.label1:
                openFragment(new FragmentSelectionKEO());
                break;
            case R.id.label2:
                openFragment(new ListHierarchiesFragment());
                break;
            case R.id.report:
                openFragment(new ListModelKEOFragment());
                break;
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.power) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    public void openAboutUser(@NotNull String id) {
       // Toast.makeText(AdminActivity.this, "" + id, Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        UserFragment userFragment = new UserFragment();
        userFragment.setArguments(bundle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, userFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void startAnimationCircleRipe(@NotNull final String id) {


/*        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;*/


        // get the final radius for the clipping circle
        int dx = animView.getWidth();
        int dy = animView.getHeight();
        float finalRadius = (float) Math.hypot(2000, 2000);

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(animView, (int)touchX, (int)touchY, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(800);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                animView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd() {
                animView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel() {  }
            @Override
            public void onAnimationRepeat() {            }
        });
        animator.start();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(touchContainer, "alpha", 0);
        objectAnimator.setDuration(600);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                openAboutUser(id);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();

    }
}
