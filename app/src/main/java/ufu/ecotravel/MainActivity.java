package ufu.ecotravel;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import dmax.dialog.SpotsDialog;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskCity;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskComment;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskImage;
import ufu.ecotravel.BackgroundTasks.BackgroundTaskPlace;
import ufu.ecotravel.UserInterface.DisplayListCities;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alertDialog = new SpotsDialog(MainActivity.this);
        alertDialog.setTitle("Please wait...");
        alertDialog.setCancelable(false);
        alertDialog.show();

        BackgroundTaskPlace backgroundTaskPlace = new BackgroundTaskPlace(MainActivity.this);
        BackgroundTaskComment backgroundTaskComment = new BackgroundTaskComment(MainActivity.this);
        BackgroundTaskCity backgroundTaskCity = new BackgroundTaskCity(MainActivity.this);
        BackgroundTaskImage backgroundTaskImage = new BackgroundTaskImage(MainActivity.this);

        backgroundTaskPlace.execute();
        backgroundTaskComment.execute();
        backgroundTaskCity.execute();
        backgroundTaskImage.execute();

        alertDialog.dismiss();

        startActivity(new Intent(MainActivity.this,DisplayListCities.class));








    }

}
