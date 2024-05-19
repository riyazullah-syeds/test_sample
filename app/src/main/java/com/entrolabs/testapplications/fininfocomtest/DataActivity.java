package com.entrolabs.testapplications.fininfocomtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.entrolabs.testapplications.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DummyDataAdapter adapter;
    private List<DummyData> dataList;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        dataList = databaseHelper.getAllData();

        adapter = new DummyDataAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sort_name) {
            sortByName();
            return true;
        } else if (item.getItemId() == R.id.action_sort_age) {
            sortByAge();
            return true;
        } else if (item.getItemId() == R.id.action_sort_city) {
            sortByCity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sortByName() {
        Collections.sort(dataList, new Comparator<DummyData>() {
            @Override
            public int compare(DummyData o1, DummyData o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortByAge() {
        Collections.sort(dataList, new Comparator<DummyData>() {
            @Override
            public int compare(DummyData o1, DummyData o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortByCity() {
        Collections.sort(dataList, new Comparator<DummyData>() {
            @Override
            public int compare(DummyData o1, DummyData o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        adapter.notifyDataSetChanged();
    }

}
