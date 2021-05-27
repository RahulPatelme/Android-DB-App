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

import nz.ac.op.cs.rahul.finalproject.adapter.DriversAdapter;
import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.Drivers;
import nz.ac.op.cs.rahul.finalproject.db.DriversDao;
import nz.ac.op.cs.rahul.finalproject.db.ItemDB;

public class AddDrivers extends AppCompatActivity {
    EditText editName,editGenre, editDetails;
    ImageView driversrcImage;
    Bitmap bmpImage;
    Button btnAdd,cancelInput;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    DriversDao driversDao;
    DriversAdapter adapter;

    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drivers);

        driversDao = ItemDB.getDBInstance(this).getDriverDao();

        editName = findViewById(R.id.editName);
        editGenre = findViewById(R.id.editNationality);
        editDetails = findViewById(R.id.editDetails);
        driversrcImage = findViewById(R.id.driversrcImage);

        driversrcImage.setOnClickListener(v -> openGallery());
        bmpImage = null;

        cancelInput = findViewById(R.id.cancelInput);
        cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDrivers.this, DriversMainActivity.class);
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

    private Bitmap getImageBitmap(Uri imageUri) throws FileNotFoundException {
        InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        driversrcImage.setImageBitmap(selectedImage);
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
            Drivers driver = new Drivers();
            driver.setDriverName(editName.getText().toString());
            driver.setDriverNationality(editGenre.getText().toString());
            driver.setDriverDetails(editDetails.getText().toString());
            driver.setImage(DataConverter.convertImage2ByteArray(bmpImage));
            driversDao.insert(driver);
            Toast.makeText(
                    this,
                    "Input successful",
                    Toast.LENGTH_SHORT
            ).show();
            Intent intent = new Intent(AddDrivers.this, DriversMainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
