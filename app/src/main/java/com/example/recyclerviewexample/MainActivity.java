package com.example.recyclerviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1
        final RecyclerView recyclerView = findViewById(R.id.recycler);

        //2
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,2);
       // RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //3 dummy Database
        final ArrayList<Country> countries = new ArrayList<>();

        countries.add (new Country("china",8000000,R.drawable.flag_china,true));
        countries.add (new Country("france",6000000,R.drawable.flag_france,true));
        countries.add (new Country("greece",3000000,R.drawable.flag_greece,true));
        countries.add (new Country("italy",8000000, R.drawable.flag_italy,true));
        countries.add (new Country("romania",8000000, R.drawable.flag_romania,true));
        countries.add (new Country("russia",8000000, R.drawable.flag_russia,true));
        countries.add (new Country("spain",8000000, R.drawable.flag_spain,true));
        countries.add (new Country("thailand",8000000, R.drawable.flag_thailand,true));
        countries.add (new Country("usa",8000000, R.drawable.flag_usa,true));
        countries.add (new Country("china",8000000,R.drawable.flag_china,true));
        countries.add (new Country("france",6000000,R.drawable.flag_france,true));
        countries.add (new Country("greece",3000000,R.drawable.flag_greece,true));
        countries.add (new Country("italy",8000000, R.drawable.flag_italy,true));
        countries.add (new Country("romania",8000000, R.drawable.flag_romania,true));
        countries.add (new Country("russia",8000000, R.drawable.flag_russia,true));
        countries.add (new Country("spain",8000000, R.drawable.flag_spain,true));
        countries.add (new Country("thailand",8000000, R.drawable.flag_thailand,true));
        countries.add (new Country("usa",8000000, R.drawable.flag_usa,true));

        //4
        CountryAdapter countryAdapter = new CountryAdapter(countries);
        recyclerView.setAdapter(countryAdapter);

        countryAdapter.setListner(new CountryAdapter.MyCountryListner() {
            @Override
            public void onCountryClicked(int position, View view) {
                //Toast.makeText(MainActivity.this, "Position: "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,country_details.class);
                intent.putExtra("data1",countries.get(position).getName());
                intent.putExtra("data2",countries.get(position).getPopulation());
                intent.putExtra("data3",countries.get(position).getFlagResId());
                startActivity(intent);

            }

            @Override
            public void onCountryLongClicked(int position, View view) {
                countries.remove(position);
                countryAdapter.notifyItemRemoved(position);

            }

            @Override
            public void onCountryDeleteClicked(int position, View view) {
                countries.remove(position);
                countryAdapter.notifyItemRemoved(position);

            }
        });

       ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               if (direction == ItemTouchHelper.LEFT)
                   Toast.makeText(MainActivity.this, "LEFT", Toast.LENGTH_SHORT).show();

               if (direction == ItemTouchHelper.RIGHT)
                   Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();

               countries.remove(viewHolder.getAdapterPosition());
               countryAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
           }
       };
       ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
       itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}