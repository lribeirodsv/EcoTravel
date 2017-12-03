package ufu.ecotravel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lucas on 22/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    //region VARIABLES

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ecotravel";

    public static final String CREATE_TABLE_CITIES =
            "CREATE TABLE " + ContractCity.CityEntry.TABLE_NAME +
                    "( " +
                    ContractCity.CityEntry.CODIGO + " integer PRIMARY KEY," +
                    ContractCity.CityEntry.NOME + " text," +
                    ContractCity.CityEntry.DESCRICAO + " text," +
                    ContractCity.CityEntry.ESTADO + " text," +
                    ContractCity.CityEntry.PAIS + " text," +
                    ContractCity.CityEntry.URL + " text," +
                    ContractCity.CityEntry.RATING + " integer);";

    public static final String CREATE_TABLE_COMMENTS =
            "CREATE TABLE " + ContractComment.CommentEntry.TABLE_NAME +
                    "( " +
                    ContractComment.CommentEntry.CODIGO + " integer PRIMARY KEY," +
                    ContractComment.CommentEntry.NOME + " text," +
                    ContractComment.CommentEntry.COMENTARIO + " text," +
                    ContractComment.CommentEntry.RATING + " integer);";

    public static final String CREATE_TABLE_PLACES =
            "CREATE TABLE " + ContractPlace.PlaceEntry.TABLE_NAME +
                    "( " +
                    ContractPlace.PlaceEntry.CODIGO + " integer  PRIMARY KEY," +
                    ContractPlace.PlaceEntry.NOME + " text," +
                    ContractPlace.PlaceEntry.DESCRICAO + " text," +
                    ContractPlace.PlaceEntry.CIDADE + " text," +
                    ContractPlace.PlaceEntry.LATITUDE + " double," +
                    ContractPlace.PlaceEntry.LONGITUDE + " double," +
                    ContractPlace.PlaceEntry.DATA + " text);";

    public static final String DROP_TABLE_CITIES =
            "DROP TABLE IF EXISTS " + ContractCity.CityEntry.TABLE_NAME;

    public static final String DROP_TABLE_COMMENTS =
            "DROP TABLE IF EXISTS " + ContractComment.CommentEntry.TABLE_NAME;

    public static final String DROP_TABLE_PLACES =
            "DROP TABLE IF EXISTS " + ContractPlace.PlaceEntry.TABLE_NAME;

    //endregion

    public DbHelper (Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
        Log.d("Database operations","Database created successfully");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DROP_TABLE_CITIES);
        Log.d("Database operations","Table cities dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_COMMENTS);
        Log.d("Database operations","Table comments dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_PLACES);
        Log.d("Database operations","Table places dropped successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_CITIES);
        Log.d("Database operations","Table cities created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMENTS);
        Log.d("Database operations","Table comments created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_PLACES);
        Log.d("Database operations","Table places created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_TABLE_CITIES);
        Log.d("Database operations","Table cities dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_COMMENTS);
        Log.d("Database operations","Table comments dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_PLACES);
        Log.d("Database operations","Table places dropped successfully");
    }


    //region METHODS - INSERTS

    public void insertCity (Integer codigo, String nome, String descricao, String estado, String pais, String url, Integer rating, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContractCity.CityEntry.CODIGO, codigo);
        contentValues.put(ContractCity.CityEntry.NOME, nome);
        contentValues.put(ContractCity.CityEntry.DESCRICAO, descricao);
        contentValues.put(ContractCity.CityEntry.ESTADO, estado);
        contentValues.put(ContractCity.CityEntry.PAIS, pais);
        contentValues.put(ContractCity.CityEntry.URL, url);
        contentValues.put(ContractCity.CityEntry.RATING, rating);

        long l = sqLiteDatabase.replace(ContractCity.CityEntry.TABLE_NAME, null,contentValues);
        Log.d("Database operations","One row inserted in cities table");
    }

    public void insertComment (Integer codigo, String nome, String comentario, Integer rating, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContractComment.CommentEntry.CODIGO, codigo);
        contentValues.put(ContractComment.CommentEntry.NOME, nome);
        contentValues.put(ContractComment.CommentEntry.COMENTARIO, comentario);
        contentValues.put(ContractComment.CommentEntry.RATING, rating);

        long l = sqLiteDatabase.replace(ContractComment.CommentEntry.TABLE_NAME, null,contentValues);
        Log.d("Database operations","One row inserted in comments table");
    }

    public void insertPlace(Integer codigo, String nome, String descricao, String cidade, double latitude, double longitude, String data, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContractPlace.PlaceEntry.CODIGO, codigo);
        contentValues.put(ContractPlace.PlaceEntry.NOME, nome);
        contentValues.put(ContractPlace.PlaceEntry.DESCRICAO, descricao);
        contentValues.put(ContractPlace.PlaceEntry.CIDADE, cidade);
        contentValues.put(ContractPlace.PlaceEntry.LATITUDE, latitude);
        contentValues.put(ContractPlace.PlaceEntry.LONGITUDE, longitude);
        contentValues.put(ContractPlace.PlaceEntry.DATA, data);

        long l = sqLiteDatabase.replace(ContractPlace.PlaceEntry.TABLE_NAME, null,contentValues);
        Log.d("Database operations","One row inserted in places table");
    }

    //endregion

    //region METHODS - GETS

    public Cursor getCity(SQLiteDatabase sqLiteDatabase){

        String[] projection = {
                ContractCity.CityEntry.CODIGO,
                ContractCity.CityEntry.NOME,
                ContractCity.CityEntry.DESCRICAO,
                ContractCity.CityEntry.ESTADO,
                ContractCity.CityEntry.PAIS,
                ContractCity.CityEntry.URL,
                ContractCity.CityEntry.RATING
        };

        Cursor cursor = sqLiteDatabase.query(ContractCity.CityEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    public Cursor getComment(SQLiteDatabase sqLiteDatabase){

        String[] projection = {
                ContractComment.CommentEntry.CODIGO,
                ContractComment.CommentEntry.NOME,
                ContractComment.CommentEntry.COMENTARIO,
                ContractComment.CommentEntry.RATING
        };

        Cursor cursor = sqLiteDatabase.query(ContractComment.CommentEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    public Cursor getPlaces(SQLiteDatabase sqLiteDatabase){

        String[] projection = {
                ContractPlace.PlaceEntry.CODIGO,
                ContractPlace.PlaceEntry.NOME,
                ContractPlace.PlaceEntry.DESCRICAO,
                ContractPlace.PlaceEntry.CIDADE,
                ContractPlace.PlaceEntry.LATITUDE,
                ContractPlace.PlaceEntry.LONGITUDE,
                ContractPlace.PlaceEntry.DATA
        };

        Cursor cursor = sqLiteDatabase.query(ContractPlace.PlaceEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    //endregion

}
