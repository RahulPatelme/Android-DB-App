package nz.ac.op.cs.rahul.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nz.ac.op.cs.rahul.finalproject.adapter.DriversAdapter;
import nz.ac.op.cs.rahul.finalproject.db.Drivers;
import nz.ac.op.cs.rahul.finalproject.viewmodel.DriversViewModel;

public class DriversMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addBtn, gobackFromDriver;

    DriversAdapter adapter;
    public  static final String DB_Name = "item_db";

    public DriversViewModel driversViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_main);

        List<Drivers> driverList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DriversAdapter(driverList,this);

        driversViewModel = new ViewModelProvider(this).get(DriversViewModel.class);
        driversViewModel.getCurrentdrivers().observe(this, new Observer<List<Drivers>>() {
            @Override
            public void onChanged(List<Drivers> drivers) {
                adapter.setDriver(drivers);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setonItemClickListener(new DriversAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Drivers obj, int pos) {
                Intent intent = new Intent(DriversMainActivity.this, DriversDetailsActivity.class);
                intent.putExtra("driver_id",obj.getId()+"");
                Log.d("driver", "onItemClick chosen "+obj.getId());
                Log.d("driver", "onItemClick chosen "+obj.getDriverName());
                DriversMainActivity.this.startActivity(intent);
            }
        });

        gobackFromDriver = findViewById(R.id.gobackFromDriver);
        gobackFromDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCata = new Intent(DriversMainActivity.this, MainActivity.class);
                startActivity(intentCata);
                finish();
            }
        });
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(DriversMainActivity.this, AddDrivers.class);
                startActivity(intentAdd);
                finish();
            }
        });
    }
}