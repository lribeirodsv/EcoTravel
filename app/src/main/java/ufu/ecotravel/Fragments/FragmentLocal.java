package ufu.ecotravel.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import ufu.ecotravel.Classes.City;
import ufu.ecotravel.Classes.Place;
import ufu.ecotravel.Database.DbHelper;
import ufu.ecotravel.R;
import ufu.ecotravel.UserInterface.DisplayDetaisCity;
import ufu.ecotravel.UserInterface.DisplayListPlacesAdapter;

/**
 * Created by Lucas on 12/11/2017.
 */

public class FragmentLocal extends Fragment {

    private Integer cidadeSelecionada;
    private RecyclerView recyclerView;
    private TextView nomeCidade;
    private TextView descCidade;
    private RatingBar ratingCidade;
    private ImageView imageCidade;
    private LinearLayout linearLayout;
    private City cidade;
    private RecyclerView.LayoutManager layoutManager;
    private Context LocaisContext = getActivity();
    private Integer place;
    private ArrayList<Place> destinos = new ArrayList<>();
    private String codigoUrl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DisplayListPlacesAdapter adapter;
        DisplayDetaisCity activity = (DisplayDetaisCity) getActivity();
        cidadeSelecionada = activity.getCidade();

        View view = inflater.inflate(R.layout.activity_display_detais_locais, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.placesrecyclerView);
        nomeCidade = (TextView) view.findViewById(R.id.nomeCityfrag);
        descCidade = (TextView) view.findViewById(R.id.descCityfrag);
        ratingCidade = (RatingBar) view.findViewById(R.id.ratingBarCityfrag);
        imageCidade = (ImageView) view.findViewById(R.id.imageCityfrag);
        linearLayout = (LinearLayout) view.findViewById(R.id.framelinearLayout);

        layoutManager = new LinearLayoutManager(LocaisContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursorAssCityPlace = dbHelper.getCityPlaces(sqLiteDatabase,cidadeSelecionada);

        try {

            cursorAssCityPlace.moveToFirst();
            do {

                place = cursorAssCityPlace.getInt(1);

                Cursor cursorloc = dbHelper.getPlace(sqLiteDatabase, place);

                cursorloc.moveToFirst();

                do {

                    Cursor cursorimages = dbHelper.getImagesPlace(sqLiteDatabase,cursorloc.getInt(0));
                    cursorimages.moveToFirst();
                    codigoUrl = cursorimages.getString(1);

                    Cursor cursorimagem = dbHelper.getImagePlace(sqLiteDatabase,codigoUrl);
                    cursorimagem.moveToFirst();

                    Place place = new Place(
                            cursorloc.getInt(0),
                            cursorloc.getString(1),
                            cursorloc.getString(2),
                            cursorloc.getDouble(3),
                            cursorloc.getDouble(4),
                            cursorloc.getString(5),
                            cursorimagem.getString(1));

                    destinos.add(place);

                } while (cursorloc.moveToNext());

            } while (cursorAssCityPlace.moveToNext());

            adapter = new DisplayListPlacesAdapter(destinos, LocaisContext);
            recyclerView.setAdapter(adapter);

        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        Cursor cursorCidade = dbHelper.getCity(sqLiteDatabase,cidadeSelecionada);
        cursorCidade.moveToFirst();

        cidade = new City(
            cursorCidade.getInt(0),
            cursorCidade.getString(1),
            cursorCidade.getString(2),
            cursorCidade.getString(3),
            cursorCidade.getString(4),
            cursorCidade.getString(5),
            cursorCidade.getInt(6));

        nomeCidade.setText(cidade.getNome());
        descCidade.setText(cidade.getDescricao());

        Picasso.with(getActivity())
                .load(cidade.getUrl())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(imageCidade);

            Cursor imagensCidade = dbHelper.getImagesCity(sqLiteDatabase,cidadeSelecionada);
            imagensCidade.moveToFirst();

            do {

                Log.d("CURSOR1", "col0: "+imagensCidade.getInt(0)+" - col1: "+imagensCidade.getString(1));

                Cursor imagemCidade = dbHelper.getImageCity(sqLiteDatabase, imagensCidade.getString(1));

                imagemCidade.moveToFirst();

                    Log.d("CURSOR2", "col0: "+imagemCidade.getInt(0)+" - col1: "+imagemCidade.getString(1));

                    ImageView imageView = new ImageView(getActivity());
                    imageView.setId(imagemCidade.getInt(0));
                    imageView.setPadding(2, 0, 2, 0);
                    imageView.setLayoutParams(new GridView.LayoutParams(210, 210));

                    Picasso.with(getActivity())
                            .load(imagemCidade.getString(1))
                            .placeholder(R.drawable.noimage)
                            .error(R.drawable.noimage)
                            .into(imageView);

                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    linearLayout.addView(imageView);

            } while (imagensCidade.moveToNext());

        ratingCidade.setNumStars(cidade.getRating());
        Log.d("Rating: ", String.valueOf(cidade.getRating()));

        dbHelper.close();
        return view;
    }
}
