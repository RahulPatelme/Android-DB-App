package nz.ac.op.cs.rahul.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    CardView cardView1, cardView2, cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DriversMainActivity.class);
                Toast.makeText(MainActivity.this, "All Available Drivers", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TeamsMainActivity.class);
                Toast.makeText(MainActivity.this, "All Available Teams", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TracksMainActivity.class);
                Toast.makeText(MainActivity.this, "All Available Tracks", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}