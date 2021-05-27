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

import nz.ac.op.cs.rahul.finalproject.adapter.TeamsAdapter;
import nz.ac.op.cs.rahul.finalproject.db.Teams;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TeamsViewModel;

public class TeamsMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addBtn, gobackFromTeam;

    TeamsAdapter adapter;
    public  static final String DB_Name = "item_db";

    public TeamsViewModel teamsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_main);

        List<Teams> teamList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TeamsAdapter(teamList,this);

        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsViewModel.getCurrentteams().observe(this, new Observer<List<Teams>>() {
            @Override
            public void onChanged(List<Teams> teams) {
                adapter.setTeam(teams);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setonItemClickListener(new TeamsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Teams obj, int pos) {
                Intent intent = new Intent(TeamsMainActivity.this, TeamsDetailsActivity.class);
                intent.putExtra("team_id",obj.getId()+"");
                Log.d("team", "onItemClick chosen "+obj.getId());
                Log.d("team", "onItemClick chosen "+obj.getTeamName());
                TeamsMainActivity.this.startActivity(intent);
            }
        });

        gobackFromTeam = findViewById(R.id.gobackFromTeam);
        gobackFromTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCata = new Intent(TeamsMainActivity.this, MainActivity.class);
                startActivity(intentCata);
                finish();
            }
        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(TeamsMainActivity.this, AddTeams.class);
                startActivity(intentAdd);
                finish();
            }
        });
    }
}