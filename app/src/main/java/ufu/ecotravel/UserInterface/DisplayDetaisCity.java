package ufu.ecotravel.UserInterface;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import ufu.ecotravel.Fragments.FragmentClima;
import ufu.ecotravel.Fragments.FragmentLocal;
import ufu.ecotravel.Fragments.FragmentRoteiro;
import ufu.ecotravel.R;


/**
 * Created by Lucas on 12/11/2017.
 */

public class DisplayDetaisCity extends Activity {

    TextView Nome, Estado;

    private String nome;
    private String estado;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_destinos:
                    transaction.replace(R.id.content,new FragmentLocal()).commit();
                    return true;
                case R.id.navigation_restaurantes:
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

        Nome = (TextView) findViewById(R.id.DetalheNome);

        String nome = getIntent().getStringExtra("Nome");
        String descricao = getIntent().getStringExtra("Descricao");
        Integer cidade = getIntent().getIntExtra("Cod_cidade",0);

        Nome.setText(nome);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new FragmentLocal()).commit();

    }
}
