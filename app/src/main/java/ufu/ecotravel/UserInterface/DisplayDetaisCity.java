package ufu.ecotravel.UserInterface;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import ufu.ecotravel.Fragments.FragmentClima;
import ufu.ecotravel.Fragments.FragmentLocal;
import ufu.ecotravel.Fragments.FragmentRoteiro;
import ufu.ecotravel.R;


/**
 * Created by Lucas on 12/11/2017.
 */

public class DisplayDetaisCity extends Activity {

    private Integer cidade;
    Context displayDetaisCityContext;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_destinos:
                    transaction.replace(R.id.content,new FragmentLocal()).commit();
                    return true;
                case R.id.navigation_clima:
                    transaction.replace(R.id.content,new FragmentClima()).commit();
                    return true;
                case R.id.navigation_roteiros:
                    transaction.replace(R.id.content,new FragmentRoteiro()).commit();
                    return true;
            }

            return false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detais);

        cidade = getIntent().getIntExtra("Cod_cidade",0);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new FragmentLocal()).commit();
    }

    public Integer getCidade() {
        return cidade;
    }

    public Context getContext() {
        return displayDetaisCityContext;
    }

}
