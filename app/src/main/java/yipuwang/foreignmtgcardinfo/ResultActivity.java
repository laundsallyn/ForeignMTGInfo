package yipuwang.foreignmtgcardinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;

/**
 * Created by Yipu Wang on 3/1/2017.
 */

public class ResultActivity extends AppCompatActivity{
    private JSONArray result;
    private TextView list;
//    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        setContentView(R.layout.result);
        list = (TextView) findViewById(R.id.list);

        if(savedInstanceState != null){
            String json = (String) savedInstanceState.get("SearchResult");
            list.setText(json);
        }
//        try{
//            list.setText( savedInstanceState.getBundle("SearchResult"));
//        }catch(JSONException je){
//            Toast.makeText(this,"Nothing found",Toast.LENGTH_LONG);
//            return;
//        }
        super.onCreate(savedInstanceState);

    }
}
