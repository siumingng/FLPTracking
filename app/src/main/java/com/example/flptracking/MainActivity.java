package com.example.flptracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> trackinginfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ordid = findViewById(R.id.ordid);
        EditText trackingid = findViewById(R.id.trackingid);
        EditText trackingids= findViewById(R.id.trackingids);
        Button  cancelbutton = findViewById(R.id.Cancel);

        trackingid.setOnKeyListener((new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    if(trackingid.length()>0)
                    {
                        if(trackinginfo.contains(trackingid.getText().toString()))
                        {
                            trackingid.setText("");

                        }else
                        {
                            trackinginfo.add(trackingid.getText().toString());
                            trackingids.setText(String.valueOf(trackinginfo.size()));
                            trackingid.setText("");
                            trackingid.requestFocus();


                        }


                    }

                }

                trackingid.requestFocus();
                return false;

            }

        }));

    }
}