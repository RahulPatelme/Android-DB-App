package nz.ac.op.cs.rahul.finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.io.FileNotFoundException;
import java.io.InputStream;

import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.Teams;
import nz.ac.op.cs.rahul.finalproject.db.TeamsDao;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TeamsRepository;

public class UpdateTeamsActivity extends AppCompatActivity {
    Intent intent;
    String team_id = "";
    TeamsRepository teamsRepository;
    Bitmap bmpImage;
    Context context;
    ImageView updateteamImage;
    EditText updateName, updateGenre, updateDetails;
    Button btnUpdate,btncancelUpdate,btnDelete, gobackFromTeam;
    private static final int PICK_IMAGE = 100;
    Teams team;
    TeamsDao teamDao;
    Uri imageUri;

    private void get_team() {
        teamsRepository.get_team(Integer.valueOf(team_id +"")).observe(this, new Observer<Teams>() {

            @Override
            public void onChanged(Teams team_chosen) {
                if(team_chosen == null){
                    Toast.makeText(UpdateTeamsActivity.this,"Team not found in the Database",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                team = team_chosen;
                Log.d("team name", team.getTeamName());
                populate_data();
            }
        });
    }

    private void populate_data() {
        updateName = findViewById(R.id.updateName);
        updateGenre = findViewById(R.id.updateNationality);
        updateDetails = findViewById((R.id.updateDetails));
        updateteamImage = findViewById(R.id.updateteamImage);
        btnUpdate = findViewById(R.id.btnUpdate);
        updateteamImage.setOnClickListener(v -> openGallery());
        updateName.setText(team.getTeamName());
        updateGenre.setText(team.getTeamNationality());
        updateteamImage.setImageBitmap(DataConverter.converByteArray2Image(team.getImage()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teams);
        intent = getIntent();
        team_id = intent.getStringExtra("edit_p4");
        Log.d("team_id", team_id +"chosen");
        teamsRepository = new TeamsRepository(this);
        get_team();

        btnUpdate= findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateName.getText().toString().isEmpty() ||
                        updateGenre.getText().toString().isEmpty() ||
                        updateDetails.getText().toString().isEmpty()
                ) {
                    Toast.makeText(UpdateTeamsActivity.this, "Error: User Data is missing", Toast.LENGTH_SHORT).show();
                } else {
                    get_team();
                    team.setTeamName(updateName.getText().toString());
                    team.setTeamNationality(updateGenre.getText().toString());
                    team.setTeamDetails(updateDetails.getText().toString());
                    team.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                    teamsRepository.update_team(team);
                    Toast.makeText(UpdateTeamsActivity.this,"Input successful",Toast.LENGTH_SHORT).show();
                    Intent intentC = new Intent(UpdateTeamsActivity.this, TeamsMainActivity.class);
                    intentC.putExtra("team_id", team_id);
                    Log.d("team_id", "onItemClick chosen "+ team_id);
                    startActivity(intentC);
                    finish();
                }
            }
        });

        btncancelUpdate= findViewById(R.id.btncancelUpdate);
        btncancelUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentC = new Intent(UpdateTeamsActivity.this, TeamsMainActivity.class);
                intentC.putExtra("team_id", team_id);
                Log.d("team_id", "onItemClick chosen "+ team_id);
                startActivity(intentC);
                finish();
            }
        });
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            try {
                Bitmap bitmap = getImageBitmap(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getImageBitmap(Uri imageUri) throws FileNotFoundException {
        InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        updateteamImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }
}
