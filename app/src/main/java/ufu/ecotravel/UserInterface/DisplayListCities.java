package ufu.ecotravel.UserInterface;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import ufu.ecotravel.Classes.City;
import ufu.ecotravel.Database.DbHelper;
import ufu.ecotravel.R;

public class DisplayListCities extends AppCompatActivity implements SearchView.OnQueryTextListener {

    DisplayListCitiesAdapter adapter;
    Context DisplayListCitiesContext;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<City> arrayList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_d);
        DisplayListCitiesContext = this;

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = dbHelper.getCity(sqLiteDatabase);

        cursor.moveToFirst();
        do{

            //[{"id":1,"city":"Uberlandia","state":"MG","country":"Brasil","description":"Cidade de uberlandia","image":"http://www.gingaimoveis.com.br/blog/wp-content/uploads/2017/10/uberlandia.jpg","rating":5,"places":[2]}]

            City city = new City(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6));

            arrayList.add(city);

        }while (cursor.moveToNext());

        dbHelper.close();

        adapter = new DisplayListCitiesAdapter(arrayList,DisplayListCitiesContext);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        adapter.getFilter().filter(query);
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("Sair")
                .setMessage("Deseja sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Não", null).show();
    }
}