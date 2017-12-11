package ufu.ecotravel.BackgroundTasks;

import android.app.AlertDialog;
import android.content.Context;
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
import dmax.dialog.SpotsDialog;
import ufu.ecotravel.Database.DbHelper;

/**
 * Created by Lucas on 22/10/2017.
 */

public class BackgroundTaskPlace extends AsyncTask <Void, Void, Void>{

    AlertDialog alertDialog;

    Context BackgroundTaskPlaceContext;

    String json_url_place = "http://52.73.120.71/webservice/place/?format=json";

    public BackgroundTaskPlace(Context ctx){
        this.BackgroundTaskPlaceContext = ctx;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new SpotsDialog(BackgroundTaskPlaceContext);
        alertDialog.setTitle("Please wait...");
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {

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
            DbHelper dbHelper = new DbHelper(BackgroundTaskPlaceContext);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            int count = 0;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                // Example [{"id":2,"title":"Cachoeira 1","description":"Essa é a descrição da cachoeira 1","city":1,"latitude":2.0,"longitude":1.0,"pub_date":"2017-11-15T23:49:40.321166Z","comments":[1],"place_images":[1]}]

                dbHelper.insertPlace(
                        JO.getInt("id"),
                        JO.getString("title"),
                        JO.getString("description"),
                        JO.getDouble("latitude"),
                        JO.getDouble("longitude"),
                        JO.getString("pub_date"),
                        sqLiteDatabase
                );

                dbHelper.insertImagePlace(
                        JO.getInt("id"),
                        JO.getJSONArray("place_images"),
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
        alertDialog.dismiss();
    }
}
