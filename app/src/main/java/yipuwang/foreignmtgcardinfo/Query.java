package yipuwang.foreignmtgcardinfo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yipu Wang on 2/26/2017.
 */


public class Query {
    private Context con;
    private JSONObject cards;
    private String cardByName = "https://api.magicthegathering.io/v1/cards?name=";
    private String foreignCard1 = "https://api.magicthegathering.io/v1/cards?language=russian&name=";
    private String foreignCard2 = "&name=";
    private String language;
    private String cardName;
    public Query(Context c, String cn, String l){
        cardName = cn;
        language = l;
        con = c;
    }
    public JSONObject start(){
        JsonTask t = new JsonTask();
        String s = cardByName + cardName; ;
        if(language != "English")
            s = foreignCard1 + language + foreignCard2 + cardName;
        t.execute(s);
        return cards;
    }
    private class JsonTask extends AsyncTask<String, String, JSONObject>{

        protected JSONObject doInBackground(String... url){
            HttpURLConnection conn;
            BufferedReader reader;
            StringBuilder sb = new StringBuilder();
            JSONObject jo = null;
            try{
                URL u = new URL(url[0]+cardName);
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jo = new JSONObject(sb.toString());
            }catch (MalformedURLException mu){}
            catch(IOException io){}
            catch(JSONException je){}
            assert (jo.length() != 0);
            cards = jo;
            return jo;
        }
        protected void onPostExecute(JSONObject obj){
            Intent intent = new Intent(con, ResultActivity.class);
            intent.putExtra("SearchResult",cards.toString());
            con.startActivity(intent);
            Toast.makeText(con, "Search finished", Toast.LENGTH_LONG).show();

        }
    }

}
