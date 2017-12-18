package ufu.ecotravel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

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
                    ContractPlace.PlaceEntry.LATITUDE + " double," +
                    ContractPlace.PlaceEntry.LONGITUDE + " double," +
                    ContractPlace.PlaceEntry.DATA + " text," +
                    ContractPlace.PlaceEntry.URL + " text);";

    public static final String CREATE_TABLE_ASSCITYPLCS =
            "CREATE TABLE " + ContractCityPlaces.CityPlaceEntry.TABLE_NAME +
                    "( " +
                    ContractCityPlaces.CityPlaceEntry.CODIGOCID + " integer NOT NULL," +
                    ContractCityPlaces.CityPlaceEntry.CODIGOPLC + " integer NOT NULL," +
                    "PRIMARY KEY ( "+ ContractCityPlaces.CityPlaceEntry.CODIGOCID + " , " + ContractCityPlaces.CityPlaceEntry.CODIGOPLC + " ));";

    public static final String CREATE_TABLE_IMAGES =
            "CREATE TABLE " + ContractImages.ImagesEntry.TABLE_NAME +
                    "( " +
                    ContractImages.ImagesEntry.CODIGOCID + " integer NOT NULL," +
                    ContractImages.ImagesEntry.CODIGOPLC + " integer NOT NULL," +
                    ContractImages.ImagesEntry.CODIGOURL + " text," +
                    "PRIMARY KEY ( "+ ContractImages.ImagesEntry.CODIGOCID + " , " + ContractImages.ImagesEntry.CODIGOPLC + " , " + ContractImages.ImagesEntry.CODIGOURL + " ));";

    public static final String CREATE_TABLE_IMAGESCITY =
            "CREATE TABLE " + ContractImages.CityImagesEntry.TABLE_NAME +
                    "( " +
                    ContractImages.CityImagesEntry.CODIGOURL + " integer PRIMARY KEY," +
                    ContractImages.CityImagesEntry.URL + " text);";

    public static final String CREATE_TABLE_IMAGESPLACE =
            "CREATE TABLE " + ContractImages.PlaceImagesEntry.TABLE_NAME +
                    "( " +
                    ContractImages.PlaceImagesEntry.CODIGOURL + " integer PRIMARY KEY," +
                    ContractImages.PlaceImagesEntry.URL + " text);";

    public static final String DROP_TABLE_CITIES =
            "DROP TABLE IF EXISTS " + ContractCity.CityEntry.TABLE_NAME;

    public static final String DROP_TABLE_COMMENTS =
            "DROP TABLE IF EXISTS " + ContractComment.CommentEntry.TABLE_NAME;

    public static final String DROP_TABLE_PLACES =
            "DROP TABLE IF EXISTS " + ContractPlace.PlaceEntry.TABLE_NAME;

    public static final String DROP_TABLE_ASSCITYPLCS =
            "DROP TABLE IF EXISTS " + ContractCityPlaces.CityPlaceEntry.TABLE_NAME;

    public static final String DROP_TABLE_IMAGES =
            "DROP TABLE IF EXISTS " + ContractImages.ImagesEntry.TABLE_NAME;

    public static final String DROP_TABLE_IMAGESCITY =
            "DROP TABLE IF EXISTS " + ContractImages.CityImagesEntry.TABLE_NAME;

    public static final String DROP_TABLE_IMAGESPLACE =
            "DROP TABLE IF EXISTS " + ContractImages.PlaceImagesEntry.TABLE_NAME;
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
        sqLiteDatabase.execSQL(DROP_TABLE_ASSCITYPLCS);
        Log.d("Database operations","Table cititesXplaces dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGES);
        Log.d("Database operations","Table images dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGESCITY);
        Log.d("Database operations","Table images (city) dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGESPLACE);
        Log.d("Database operations","Table images (place) dropped successfully");

        sqLiteDatabase.execSQL(CREATE_TABLE_CITIES);
        Log.d("Database operations","Table cities created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMENTS);
        Log.d("Database operations","Table comments created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_PLACES);
        Log.d("Database operations","Table places created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_ASSCITYPLCS);
        Log.d("Database operations","Table cititesXplaces created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGES);
        Log.d("Database operations","Table images created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGESCITY);
        Log.d("Database operations","Table images (city) created successfully");
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGESPLACE);
        Log.d("Database operations","Table images (place) created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_TABLE_CITIES);
        Log.d("Database operations","Table cities dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_COMMENTS);
        Log.d("Database operations","Table comments dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_PLACES);
        Log.d("Database operations","Table places dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_ASSCITYPLCS);
        Log.d("Database operations","Table cititesXplaces dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGES);
        Log.d("Database operations","Table images dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGESCITY);
        Log.d("Database operations","Table images (city) dropped successfully");
        sqLiteDatabase.execSQL(DROP_TABLE_IMAGESPLACE);
        Log.d("Database operations","Table images (place) dropped successfully");
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

    public void insertPlace(Integer codigo, String nome, String descricao, double latitude, double longitude, String data, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ContractPlace.PlaceEntry.CODIGO, codigo);
        contentValues.put(ContractPlace.PlaceEntry.NOME, nome);
        contentValues.put(ContractPlace.PlaceEntry.DESCRICAO, descricao);
        contentValues.put(ContractPlace.PlaceEntry.LATITUDE, latitude);
        contentValues.put(ContractPlace.PlaceEntry.LONGITUDE, longitude);
        contentValues.put(ContractPlace.PlaceEntry.DATA, data);

        long l = sqLiteDatabase.replace(ContractPlace.PlaceEntry.TABLE_NAME, null,contentValues);
        Log.d("Database operations","One row inserted in places table");
    }

    public void insertCityPlaces(Integer codigo, JSONArray places, SQLiteDatabase sqLiteDatabase) throws JSONException {

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < places.length(); i++) {

            contentValues.put(ContractCityPlaces.CityPlaceEntry.CODIGOCID, codigo);
            contentValues.put(ContractCityPlaces.CityPlaceEntry.CODIGOPLC, places.getInt(i));

            long l = sqLiteDatabase.replace(ContractCityPlaces.CityPlaceEntry.TABLE_NAME, null,contentValues);
            Log.d("Database operations","One row inserted in citiesXplaces table");
        }
    }

    public void insertImageCity(Integer codigo, JSONArray images, SQLiteDatabase sqLiteDatabase) throws JSONException {

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < images.length(); i++) {

            contentValues.put(ContractImages.ImagesEntry.CODIGOCID, codigo);
            contentValues.put(ContractImages.ImagesEntry.CODIGOPLC, 0);
            contentValues.put(ContractImages.ImagesEntry.CODIGOURL, images.getString(i));

            long l = sqLiteDatabase.replace(ContractImages.ImagesEntry.TABLE_NAME, null,contentValues);
            Log.d("Database operations","One row inserted in images table (city)");
        }
    }

    public void insertImagePlace(Integer codigo, JSONArray images, SQLiteDatabase sqLiteDatabase) throws JSONException {

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < images.length(); i++) {

            contentValues.put(ContractImages.ImagesEntry.CODIGOPLC, codigo);
            contentValues.put(ContractImages.ImagesEntry.CODIGOCID, 0);
            contentValues.put(ContractImages.ImagesEntry.CODIGOURL, images.getString(i));

            long l = sqLiteDatabase.replace(ContractImages.ImagesEntry.TABLE_NAME, null,contentValues);
            Log.d("Database operations","One row inserted in images table (place)");
        }
    }

    public void insertImagesCity(Integer codigo, String url, SQLiteDatabase sqLiteDatabase) throws JSONException {

        ContentValues contentValues = new ContentValues();

            contentValues.put(ContractImages.CityImagesEntry.CODIGOURL, codigo);
            contentValues.put(ContractImages.CityImagesEntry.URL, url);

            long l = sqLiteDatabase.replace(ContractImages.CityImagesEntry.TABLE_NAME, null,contentValues);
            Log.d("Database operations","One row inserted in images (city) table (city)");

    }

    public void insertImagesPlace(Integer codigo, String url, SQLiteDatabase sqLiteDatabase) throws JSONException {

        ContentValues contentValues = new ContentValues();

            contentValues.put(ContractImages.PlaceImagesEntry.CODIGOURL, codigo);
            contentValues.put(ContractImages.PlaceImagesEntry.URL, url);

            long l = sqLiteDatabase.replace(ContractImages.PlaceImagesEntry.TABLE_NAME, null,contentValues);
            Log.d("Database operations","One row inserted in images (place) table (place)");

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
                ContractCity.CityEntry.RATING,
        };

        Cursor cursor = sqLiteDatabase.query(ContractCity.CityEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    public Cursor getCity(SQLiteDatabase sqLiteDatabase, Integer cidade){

        String[] projection = {
                ContractCity.CityEntry.CODIGO,
                ContractCity.CityEntry.NOME,
                ContractCity.CityEntry.DESCRICAO,
                ContractCity.CityEntry.ESTADO,
                ContractCity.CityEntry.PAIS,
                ContractCity.CityEntry.URL,
                ContractCity.CityEntry.RATING,
        };

        String whereClause = ContractCity.CityEntry.CODIGO+"=?";
        String [] whereArgs = {cidade.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractCity.CityEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

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

    public Cursor getPlace(SQLiteDatabase sqLiteDatabase, Integer place){

        String[] projection = {
                ContractPlace.PlaceEntry.CODIGO,
                ContractPlace.PlaceEntry.NOME,
                ContractPlace.PlaceEntry.DESCRICAO,
                ContractPlace.PlaceEntry.LATITUDE,
                ContractPlace.PlaceEntry.LONGITUDE,
                ContractPlace.PlaceEntry.DATA,
                ContractPlace.PlaceEntry.URL,
        };

        String whereClause = ContractPlace.PlaceEntry.CODIGO+"=?";
        String [] whereArgs = {place.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractPlace.PlaceEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }

    public Cursor getPlaces(SQLiteDatabase sqLiteDatabase){

        String[] projection = {
                ContractPlace.PlaceEntry.CODIGO,
                ContractPlace.PlaceEntry.NOME,
                ContractPlace.PlaceEntry.DESCRICAO,
                ContractPlace.PlaceEntry.LATITUDE,
                ContractPlace.PlaceEntry.LONGITUDE,
                ContractPlace.PlaceEntry.DATA
        };

        Cursor cursor = sqLiteDatabase.query(ContractPlace.PlaceEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    public Cursor getCityPlaces(SQLiteDatabase sqLiteDatabase, Integer cidade){

        String[] projection = {
                ContractCityPlaces.CityPlaceEntry.CODIGOCID,
                ContractCityPlaces.CityPlaceEntry.CODIGOPLC
        };

        String whereClause = ContractCityPlaces.CityPlaceEntry.CODIGOCID+"=?";
        String [] whereArgs = {cidade.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractCityPlaces.CityPlaceEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }

    public Cursor getCityPlaces(SQLiteDatabase sqLiteDatabase){

        String[] projection = {
                ContractCityPlaces.CityPlaceEntry.CODIGOCID,
                ContractCityPlaces.CityPlaceEntry.CODIGOPLC
        };

        Cursor cursor = sqLiteDatabase.query(ContractCityPlaces.CityPlaceEntry.TABLE_NAME,projection,null,null,null,null,null);

        return  cursor;
    }

    public Cursor getImagesPlace(SQLiteDatabase sqLiteDatabase, Integer lugar){

        String[] projection = {
                ContractImages.ImagesEntry.CODIGOPLC,
                ContractImages.ImagesEntry.CODIGOURL
        };

        String whereClause = ContractImages.ImagesEntry.CODIGOPLC+"=?";
        String [] whereArgs = {lugar.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractImages.ImagesEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }

    public Cursor getImagesCity(SQLiteDatabase sqLiteDatabase, Integer cidade){

        String[] projection = {
                ContractImages.ImagesEntry.CODIGOCID,
                ContractImages.ImagesEntry.CODIGOURL
        };

        String whereClause = ContractImages.ImagesEntry.CODIGOCID+"=?";
        String [] whereArgs = {cidade.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractImages.ImagesEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }

    public Cursor getImageCity(SQLiteDatabase sqLiteDatabase, String codurl) {

        String[] projection = {
                ContractImages.CityImagesEntry.CODIGOURL,
                ContractImages.CityImagesEntry.URL
        };

        String whereClause = ContractImages.CityImagesEntry.CODIGOURL+"=?";
        String [] whereArgs = {codurl.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractImages.CityImagesEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }

    public Cursor getImagePlace(SQLiteDatabase sqLiteDatabase, String codurl) {

        String[] projection = {
                ContractImages.PlaceImagesEntry.CODIGOURL,
                ContractImages.PlaceImagesEntry.URL
        };

        String whereClause = ContractImages.PlaceImagesEntry.CODIGOURL+"=?";
        String [] whereArgs = {codurl.toString()};

        Cursor cursor = sqLiteDatabase.query(ContractImages.PlaceImagesEntry.TABLE_NAME,projection,whereClause,whereArgs,null,null,null);

        return  cursor;
    }


    //endregion

}
