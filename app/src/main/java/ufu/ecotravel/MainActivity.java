package ufu.ecotravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ufu.ecotravel.BackgroundTasks.BackgroundTaskCity;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskComment;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskPlace;
import ufu.ecotravel.UserInterface.DisplayListCities;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            BackgroundTaskPlace backgroundTaskPlace = new BackgroundTaskPlace(MainActivity.this);
            BackgroundTaskComment backgroundTaskComment = new BackgroundTaskComment(MainActivity.this);
            BackgroundTaskCity backgroundTaskCity = new BackgroundTaskCity(MainActivity.this);
            backgroundTaskPlace.execute();
            backgroundTaskComment.execute();
            backgroundTaskCity.execute();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,DisplayListCities.class));
            }
        });
    }
}
