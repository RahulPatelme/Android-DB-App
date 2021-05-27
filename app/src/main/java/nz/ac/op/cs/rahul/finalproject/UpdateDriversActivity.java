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
import nz.ac.op.cs.rahul.finalproject.db.Drivers;
import nz.ac.op.cs.rahul.finalproject.db.DriversDao;
import nz.ac.op.cs.rahul.finalproject.viewmodel.DriversRepository;

public class UpdateDriversActivity extends AppCompatActivity {
    Intent intent;
    String driver_id = "";
    DriversRepository driversRepository;
    Bitmap bmpImage;
    Context context;
    ImageView updatedriverImage;
    EditText updateName, updateGenre, updateDetails;
    Button btnUpdate,btncancelUpdate,btnDelete, gobackFromDriver;
    private static final int PICK_IMAGE = 100;
    Drivers driver;
    DriversDao driverDao;
    Uri imageUri;

    private void get_driver() {
        driversRepository.get_driver(Integer.valueOf(driver_id +"")).observe(this, new Observer<Drivers>() {

            @Override
            public void onChanged(Drivers driver_chosen) {
                if(driver_chosen == null){
                    Toast.makeText(UpdateDriversActivity.this,"Driver not found in the Database",Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                driver = driver_chosen;
                Log.d("driver name", driver.getDriverName());
                populate_data();
            }

        });
    }
    private void populate_data() {
        updateName = findViewById(R.id.updateName);
        updateGenre = findViewById(R.id.updateNationality);
        updateDetails = findViewById((R.id.updateDetails));
        updatedriverImage = findViewById(R.id.updatedriverImage);
        btnUpdate = findViewById(R.id.btnUpdate);
        updatedriverImage.setOnClickListener(v -> openGallery());
        updateName.setText(driver.getDriverName());
        updateGenre.setText(driver.getDriverNationality());
        updatedriverImage.setImageBitmap(DataConverter.converByteArray2Image(driver.getImage()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drivers);
        intent = getIntent();
        driver_id = intent.getStringExtra("edit_driver");
        Log.d("driver_id", driver_id +"chosen");
        driversRepository = new DriversRepository(this);
        get_driver();

        btnUpdate= findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateName.getText().toString().isEmpty() ||
                        updateGenre.getText().toString().isEmpty() ||
                        updateDetails.getText().toString().isEmpty()
                ) {
                    Toast.makeText(UpdateDriversActivity.this, "Error: User Data is missing", Toast.LENGTH_SHORT).show();
                } else {
                    get_driver();
                    driver.setDriverName(updateName.getText().toString());
                    driver.setDriverNationality(updateGenre.getText().toString());
                    driver.setDriverDetails(updateDetails.getText().toString());
                    driver.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                    driversRepository.update_driver(driver);
                    Toast.makeText(UpdateDriversActivity.this,"Input successful",Toast.LENGTH_SHORT).show();
                    Intent intentC = new Intent(UpdateDriversActivity.this, DriversMainActivity.class);
                    intentC.putExtra("driver_id", driver_id);
                    Log.d("driver_id", "onItemClick chosen "+ driver_id);
                    startActivity(intentC);
                    finish();
                }
            }
        });

        btncancelUpdate= findViewById(R.id.btncancelUpdate);
        btncancelUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentC = new Intent(UpdateDriversActivity.this, DriversMainActivity.class);
                intentC.putExtra("driver_id", driver_id);
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
        updatedriverImage.setImageBitmap(selectedImage);
        bmpImage = selectedImage;
        return bmpImage;
    }
}