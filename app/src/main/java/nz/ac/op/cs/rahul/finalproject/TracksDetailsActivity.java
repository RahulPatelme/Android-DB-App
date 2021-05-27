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
import nz.ac.op.cs.rahul.finalproject.db.Tracks;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TracksRepository;


public class TracksDetailsActivity extends AppCompatActivity {
    Intent intent;
    String track_id = "";
    TracksRepository trackRepository;
    Context context;
    ImageView image_details;
    TextView name_details, genre_details, details_details;
    Button btn_edit,cancelInput, dltBtn;
    Tracks track;

    private void delete_track() {
        trackRepository.delete_track(track);
        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }


    private void get_track() {
        trackRepository.get_pC(Integer.valueOf(track_id +"")).observe(this, new Observer<Tracks>() {

            @Override
            public void onChanged(Tracks track_chosen) {
                if(track_chosen == null){
                    finish();
                    return;
                }
                track = track_chosen;
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
                Intent intent = new Intent(TracksDetailsActivity.this, UpdateTracksActivity.class);
                intent.putExtra("edit_track", track.getId()+"");
                startActivity(intent);
            }
        });

        dltBtn = findViewById(R.id.dltBtn);

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackRepository.delete_track(track);
                Intent intent = new Intent(TracksDetailsActivity.this, TracksMainActivity.class);
                startActivity(intent);
                intent.putExtra("edit_pC", track.getId()+"");
                finish();
            }
        });

        cancelInput = findViewById(R.id.cancelInput);
        cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TracksDetailsActivity.this, TracksMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        name_details.setText(track.getTrackName());
        genre_details.setText(track.getTrackNationality());
        details_details.setText(track.getTrackDetails());
        image_details.setImageBitmap(DataConverter.converByteArray2Image(track.getImage()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        intent = getIntent();
        track_id = intent.getStringExtra("track_id");

        trackRepository = new TracksRepository(this);
        get_track();
    }
}