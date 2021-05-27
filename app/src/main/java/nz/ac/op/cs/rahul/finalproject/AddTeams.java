package nz.ac.op.cs.rahul.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import nz.ac.op.cs.rahul.finalproject.adapter.TeamsAdapter;
import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.ItemDB;
import nz.ac.op.cs.rahul.finalproject.db.Teams;
import nz.ac.op.cs.rahul.finalproject.db.TeamsDao;

public class AddTeams extends AppCompatActivity {
    EditText editName,editGenre, editDetails;
    ImageView teamsrcImage;
    Bitmap bmpImage;
    Button btnAdd,cancelInput;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    TeamsDao teamDao;
    TeamsAdapter adapter;

    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teams);

        teamDao = ItemDB.getDBInstance(this).getTeamDao();

        editName = findViewById(R.id.editName);
        editGenre = findViewById(R.id.editNationality);
        editDetails = findViewById(R.id.editDetails);
        teamsrcImage = findViewById(R.id.teamsrcImage);

        teamsrcImage.setOnClickListener(v -> openGallery());
        bmpImage = null;
        cancelInput = findViewById(R.id.cancelInput);
        cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTeams.this, TeamsMainActivity.class);
                startActivity(intent);
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

    /**
     *
     * @param imageUri
     * @return
     * @throws FileNotFoundException
     */

    private Bitmap getImageBitmap(Uri imageUri) throws FileNotFoundException {
        InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        teamsrcImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }

    /**
     *
     * @param view
     */

    public void onAddButtonClick(View view){
        if(editName.getText().toString().isEmpty() ||
                editGenre.getText().toString().isEmpty() ||
                editDetails.getText().toString().isEmpty()
        ){
            Toast.makeText(
                    this,
                    "Error: Missing some Data",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Teams team = new Teams();
            team.setTeamName(editName.getText().toString());
            team.setTeamNationality(editGenre.getText().toString());
            team.setTeamDetails(editDetails.getText().toString());
            team.setImage(DataConverter.convertImage2ByteArray(bmpImage));
            teamDao.insert(team);
            Toast.makeText(
                    this,
                    "Input successful",
                    Toast.LENGTH_SHORT
            ).show();
            Intent intent = new Intent(AddTeams.this, TeamsMainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

