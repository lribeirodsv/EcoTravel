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

public class BackgroundTaskCity extends AsyncTask <Void, Void, Void>{

    AlertDialog alertDialog;

    Context BackgroundTaskCityContext;

    String json_url_city = "http://52.73.120.71/webservice/city/?format=json";

    public BackgroundTaskCity(Context ctx){
        this.BackgroundTaskCityContext = ctx;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new SpotsDialog(BackgroundTaskCityContext);
        alertDialog.setTitle("Please wait...");
        alertDialog.setCancelable(false);
        alertDialog.show();

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
            DbHelper dbHelper = new DbHelper(BackgroundTaskCityContext);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            int count = 0;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                //Example [{"id":1,"city":"Uberlandia","state":"MG","country":"Brasil","description":"Cidade de uberlandia","image":"image2.jpg","rating":5,"places":[2]}]

                dbHelper.insertCity(
                        JO.getInt("id"),
                        JO.getString("city"),
                        JO.getString("description"),
                        JO.getString("state"),
                        JO.getString("country"),
                        JO.getString("image"),
                        JO.getInt("rating"),
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
