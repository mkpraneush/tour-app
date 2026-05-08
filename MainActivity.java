package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private PlaceAdapter adapter;

    private List<Place> popularPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // Display all 10 South India places
        popularPlaces = PlaceRepository.getPlaces();

        adapter = new PlaceAdapter(
                this,
                popularPlaces,
                place -> {

                    Intent intent =
                            new Intent(MainActivity.this,
                                    DetailActivity.class);

                    intent.putExtra("place", place);

                    startActivity(intent);
                });

        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav =
                findViewById(R.id.bottomNavigation);

        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_seasons) {

                startActivity(
                        new Intent(MainActivity.this,
                                SeasonActivity.class));

                return true;

            } else if (id == R.id.nav_favorites) {

                startActivity(
                        new Intent(MainActivity.this,
                                FavoritesActivity.class));

                return true;
            }

            return id == R.id.nav_home;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(
            @NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_about) {

            startActivity(
                    new Intent(this,
                            AboutActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}