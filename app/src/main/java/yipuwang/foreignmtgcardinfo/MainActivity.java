package yipuwang.foreignmtgcardinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText search;
    private Spinner languages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.search);
        search = (EditText) findViewById((R.id.cardName));
        languages = (Spinner) findViewById(R.id.languageSpinner);
        button.setOnClickListener(new ClickListener(this));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.Languages,android.R.layout.simple_spinner_item);
        languages.setAdapter(adapter);
    }

    private class ClickListener implements View.OnClickListener {
        Context con;
        public ClickListener( Context con){
            this.con = con;
        }
        public void onClick(View view){
            String t = "Searching "+ search.getText().toString();
            Toast.makeText(con,t, Toast.LENGTH_LONG).show();
            Query q = new Query(search.getText().toString(), languages.getSelectedItem().toString());
        }
    }
}
