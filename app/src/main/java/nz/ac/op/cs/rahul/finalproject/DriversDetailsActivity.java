package nz.ac.op.cs.rahul.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import nz.ac.op.cs.rahul.finalproject.db.DataConverter;
import nz.ac.op.cs.rahul.finalproject.db.Drivers;
import nz.ac.op.cs.rahul.finalproject.viewmodel.DriversRepository;

public class DriversDetailsActivity extends AppCompatActivity {
    Intent intent;
    String driver_id = "";
    DriversRepository driversRepository;
    Context context;
    ImageView image_details;
    TextView name_details, genre_details, details_details;
    Button btn_edit,cancelInput, dltBtn;
    Drivers driver;

    private void delete_driver() {
        Log.d("check del", driver.toString());
        driversRepository.delete_driver(driver);
        Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    private void get_driver() {

        driversRepository.get_driver(Integer.valueOf(driver_id +"")).observe(this, new Observer<Drivers>() {

            @Override
            public void onChanged(Drivers driver_chosen) {
                if(driver_chosen == null){
                    finish();
                    return;
                }
                driver = driver_chosen;
                Log.d("check get", driver.toString());
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
                Intent intent = new Intent(DriversDetailsActivity.this, UpdateDriversActivity.class);
                intent.putExtra("edit_driver", driver.getId()+"");
                startActivity(intent);
            }
        });

        dltBtn = findViewById(R.id.dltBtn);

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driversRepository.delete_driver(driver);
                Intent intent = new Intent(DriversDetailsActivity.this, DriversMainActivity.class);
                startActivity(intent);
                intent.putExtra("edit_driver", driver.getId()+"");
                finish();
            }
        });

        cancelInput = findViewById(R.id.cancelInput);
        cancelInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriversDetailsActivity.this, DriversMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        name_details.setText(driver.getDriverName());
        genre_details.setText(driver.getDriverNationality());
        details_details.setText(driver.getDriverDetails());
        image_details.setImageBitmap(DataConverter.converByteArray2Image(driver.getImage()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_details);
        intent = getIntent();
        driver_id = intent.getStringExtra("driver_id");

        driversRepository = new DriversRepository(this);
        get_driver();
    }
}