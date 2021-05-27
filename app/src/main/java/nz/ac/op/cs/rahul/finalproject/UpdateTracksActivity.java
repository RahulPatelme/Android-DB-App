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
import nz.ac.op.cs.rahul.finalproject.db.Tracks;
import nz.ac.op.cs.rahul.finalproject.db.TracksDao;
import nz.ac.op.cs.rahul.finalproject.viewmodel.TracksRepository;

public class UpdateTracksActivity extends AppCompatActivity {
    Intent intent;
    String track_id = "";
    TracksRepository trackRepository;
    Bitmap bmpImage;
    Context context;
    ImageView updatepCImage;
    EditText updateName, updateGenre, updateDetails;
    Button btnUpdate,btncancelUpdate,btnDelete, gobackFromTrack;
    private static final int PICK_IMAGE = 100;
    Tracks track;
    TracksDao trackDao;
    Uri imageUri;

    private void get_track() {
        trackRepository.get_pC(Integer.valueOf(track_id +"")).observe(this, new Observer<Tracks>() {

            @Override
            public void onChanged(Tracks track_chosen) {
                if(track_chosen == null){
                    Toast.makeText(UpdateTracksActivity.this,"Track not found in the Database",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                track = track_chosen;
                Log.d("track name", track.getTrackName());
                populate_data();
            }
        });
    }

    private void populate_data() {
        updateName = findViewById(R.id.updateName);
        updateGenre = findViewById(R.id.updateNationality);
        updateDetails = findViewById((R.id.updateDetails));
        updatepCImage = findViewById(R.id.updatetrackImage);
        btnUpdate = findViewById(R.id.btnUpdate);
        updatepCImage.setOnClickListener(v -> openGallery());
        updateName.setText(track.getTrackName());
        updateGenre.setText(track.getTrackNationality());
        updatepCImage.setImageBitmap(DataConverter.converByteArray2Image(track.getImage()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tracks);
        intent = getIntent();
        track_id = intent.getStringExtra("edit_track");
        Log.d("track_id", track_id +"chosen");
        trackRepository = new TracksRepository(this);
        get_track();

        btnUpdate= findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateName.getText().toString().isEmpty() ||
                        updateGenre.getText().toString().isEmpty() ||
                        updateDetails.getText().toString().isEmpty()
                ) {
                    Toast.makeText(UpdateTracksActivity.this, "Error: User Data is missing", Toast.LENGTH_SHORT).show();
                } else {
                    get_track();
                    track.setTrackName(updateName.getText().toString());
                    track.setTrackNationality(updateGenre.getText().toString());
                    track.setTrackDetails(updateDetails.getText().toString());
                    track.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                    trackRepository.update_track(track);
                    Toast.makeText(UpdateTracksActivity.this,"Input successful",Toast.LENGTH_SHORT).show();
                    Intent intentC = new Intent(UpdateTracksActivity.this, TracksMainActivity.class);
                    intentC.putExtra("track_id", track_id);
                    Log.d("track_id", "onItemClick chosen "+ track_id);
                    startActivity(intentC);
                    finish();
                }
            }
        });

        btncancelUpdate= findViewById(R.id.btncancelUpdate);
        btncancelUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentC = new Intent(UpdateTracksActivity.this, TracksMainActivity.class);
                intentC.putExtra("track_id", track_id);
                Log.d("track_id", "onItemClick chosen "+ track_id);
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
        updatepCImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }
}