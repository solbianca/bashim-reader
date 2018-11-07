package com.example.solbianca.bashim;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.solbianca.bashim.Fragments.BestByRatingQuotesFragment;
import com.example.solbianca.bashim.Fragments.BestQuotesFragment;
import com.example.solbianca.bashim.Fragments.NewQuotesFragment;
import com.example.solbianca.bashim.Fragments.RandomQuotesFragment;
import com.example.solbianca.bashim.Services.BashImApi;

public class BashActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_bash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initNewQuotesFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_new_quotes) {
            initNewQuotesFragment();
        } else if (id == R.id.nav_random_quotes) {
            initRandomQuotesFragment();
        } else if (id == R.id.nav_best_quotes) {
            initBestQuotesFragment();
        } else if (id == R.id.nav_best_by_rating_quotes) {
            initBestByRatingQuotesFragment();
        }

        item.setChecked(true);
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNewQuotesFragment() {
        setTitle("Новые");
        NewQuotesFragment fragment = new NewQuotesFragment();
        fragment.setQuotesRoute(BashImApi.QUOTES_NEW);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initRandomQuotesFragment() {
        setTitle("Случайные");
        RandomQuotesFragment fragment = new RandomQuotesFragment();
        fragment.setQuotesRoute(BashImApi.QUOTES_RANDOM);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initBestQuotesFragment() {
        setTitle("Лучшие за день");
        BestQuotesFragment fragment = new BestQuotesFragment();
        fragment.setQuotesRoute(BashImApi.QUOTES_BEST_OF_THE_DAY);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initBestByRatingQuotesFragment() {
        setTitle("Лучшие по рейтингу");
        BestByRatingQuotesFragment fragment = new BestByRatingQuotesFragment();
        fragment.setQuotesRoute(BashImApi.QUOTES_BEST_BY_RATING);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}
