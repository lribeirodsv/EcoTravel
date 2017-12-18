package ufu.ecotravel.BackgroundTasks;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import ufu.ecotravel.Database.DbHelper;
import ufu.ecotravel.UserInterface.DisplayListCities;

/**
 * Created by Lucas on 22/10/2017.
 */

public class BackgroundTaskImage extends AsyncTask <Void, Void, Void>{

    Context BackgroundTaskImageContext;

    String json_url_city = "http://52.73.120.71/webservice/cityimage/?format=json";
    String json_url_place = "http://52.73.120.71/webservice/placeimage/?format=json";


    public BackgroundTaskImage(Context ctx){
        this.BackgroundTaskImageContext = ctx;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try{
            URL url = new URL(json_url_city);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line + "\n");
                Thread.sleep(500);
            }

            //JSON
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONArray jsonArray = new JSONArray(json_data);

            //SQLite
            DbHelper dbHelper = new DbHelper(BackgroundTaskImageContext);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            int count = 0;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                // Example [{"id":1,"city":2,"url":"http://marqueviagem.com/marque/uploads/2015/04/Jogue-se-nas-%C3%A1guas-quentes-de-Caldas-Novas-a-praia-do-cerrado.jpg","pub_date":"2017-12-11T00:49:36.658244Z"}]

                dbHelper.insertImagesCity(
                        JO.getInt("id"),
                        JO.getString("url"),
                        sqLiteDatabase
                );

            }

            dbHelper.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try{
            URL url = new URL(json_url_place);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line + "\n");
                Thread.sleep(500);
            }

            //JSON
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONArray jsonArray = new JSONArray(json_data);

            //SQLite
            DbHelper dbHelper = new DbHelper(BackgroundTaskImageContext);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            int count = 0;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                // Example [{"id":2,"place":10,"url":"https://i.imgur.com/aHVYgFr.jpg","pub_date":"2017-12-12T03:07:40.654526Z"}]

                dbHelper.insertImagesPlace(
                        JO.getInt("id"),
                        JO.getString("url"),
                        sqLiteDatabase
                );
            }

            dbHelper.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        BackgroundTaskImageContext.startActivity(new Intent(BackgroundTaskImageContext, DisplayListCities.class));
    }
}
