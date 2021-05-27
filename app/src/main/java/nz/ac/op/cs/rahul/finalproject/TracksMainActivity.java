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

import nz.ac.op.cs.rahul.finalproject.adapter.TracksAdapter;
import nz.ac.op.cs.rahul.finalproject.db.Tracks;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TracksViewModel;

public class TracksMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addBtn, gobackFromTrack;

    TracksAdapter adapter;
    public  static final String DB_Name = "item_db";

    public TracksViewModel trackViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_main);

        List<Tracks> trackList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TracksAdapter(trackList,this);

        trackViewModel = new ViewModelProvider(this).get(TracksViewModel.class);
        trackViewModel.getCurrenttracks().observe(this, new Observer<List<Tracks>>() {
            @Override
            public void onChanged(List<Tracks> pCs) {
                adapter.setTrack(pCs);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setonItemClickListener(new TracksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Tracks obj, int pos) {
                Intent intent = new Intent(TracksMainActivity.this, TracksDetailsActivity.class);
                intent.putExtra("track_id",obj.getId()+"");
                Log.d("track", "onItemClick chosen "+obj.getId());
                Log.d("track", "onItemClick chosen "+obj.getTrackName());
                TracksMainActivity.this.startActivity(intent);
            }

        });

        gobackFromTrack = findViewById(R.id.gobackFromTrack);
        gobackFromTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCata = new Intent(TracksMainActivity.this, MainActivity.class);
                startActivity(intentCata);
                finish();
            }
        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(TracksMainActivity.this, AddTracks.class);
                startActivity(intentAdd);
                finish();
            }
        });
    }
}