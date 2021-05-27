package nz.ac.op.cs.rahul.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.Teams;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TeamsRepository;

public class TeamsDetailsActivity extends AppCompatActivity {
    Intent intent;
    String team_id = "";
    TeamsRepository teamsRepository;
    Context context;
    ImageView image_details;
    TextView name_details, genre_details, details_details;
    Button btn_edit,cancelInput, dltBtn;
    Teams team;

    private void delete_team() {
        teamsRepository.delete_team(team);
        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    private void get_team() {
        teamsRepository.get_team(Integer.valueOf(team_id +"")).observe(this, new Observer<Teams>() {

            @Override
            public void onChanged(Teams team_chosen) {
                if(team_chosen == null){
                    Toast.makeText(TeamsDetailsActivity.this,"The team was not found in the Database",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                team = team_chosen;
                populate_data();
            }
        });
    }

    private void populate_data() {
        name_details = findViewById(R.id.name_details);
        genre_details = findViewById(R.id.nationality_details);
        details_details = findViewById(R.id.details_details);
        image_details = findViewById(R.id.image_details);
        btn_edit = findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamsDetailsActivity.this, UpdateTeamsActivity.class);
                intent.putExtra("edit_p4", team.getId()+"");
                startActivity(intent);
            }
        });

        dltBtn = findViewById(R.id.dltBtn);

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamsRepository.delete_team(team);
                Intent intent = new Intent(TeamsDetailsActivity.this, TeamsMainActivity.class);
                startActivity(intent);
                intent.putExtra("edit_p4", team.getId()+"");
                finish();
            }
        });

        cancelInput = findViewById(R.id.cancelInput);
        cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamsDetailsActivity.this, TeamsMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        name_details.setText(team.getTeamName());
        genre_details.setText(team.getTeamNationality());
        details_details.setText(team.getTeamDetails());
        image_details.setImageBitmap(DataConverter.converByteArray2Image(team.getImage()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_details);
        intent = getIntent();
        team_id = intent.getStringExtra("team_id");

        teamsRepository = new TeamsRepository(this);
        get_team();
    }
}