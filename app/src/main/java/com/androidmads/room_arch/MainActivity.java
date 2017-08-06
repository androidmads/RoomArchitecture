package com.androidmads.room_arch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidmads.room_arch.R;
import com.androidmads.room_arch.adapter.RecyclerViewAdapter;
import com.androidmads.room_arch.db.AppDataBase;
import com.androidmads.room_arch.db.ProductModel;
import com.androidmads.room_arch.view_model.ProductListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatLifeCycleActivity implements View.OnLongClickListener, View.OnClickListener {

    private ProductListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<ProductModel>(), this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(@Nullable List<ProductModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDataBase.destroyInstance();
    }

    @Override
    public boolean onLongClick(View v) {
        ProductModel productModel = (ProductModel) v.getTag();
        viewModel.deleteItem(productModel);
        return true;
    }

    @Override
    public void onClick(View v) {
        ProductModel productModel = (ProductModel) v.getTag();
        Intent i = new Intent(MainActivity.this, UpdateActivity.class);
        i.putExtra("itemId",productModel.itemId);
        startActivity(i);
    }
}
