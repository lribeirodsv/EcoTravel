package ufu.ecotravel.UserInterface;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import ufu.ecotravel.Classes.City;
import ufu.ecotravel.Database.DbHelper;
import ufu.ecotravel.R;

public class DisplayListCities extends AppCompatActivity {

    Context DisplayListCitiesContext;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<City> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_d);
        DisplayListCitiesContext = this;

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
}
