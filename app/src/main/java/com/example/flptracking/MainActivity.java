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
    EditText ordid ;
    EditText trackingid ;
    EditText trackingids;
    EditText status;
    Button  cancelbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordid = findViewById(R.id.ordid);
        trackingid = findViewById(R.id.trackingid);
        trackingids= findViewById(R.id.trackingids);
        status= findViewById(R.id.txtStatus);

        ordid.setOnKeyListener((new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    status.setText("");

                }
                return false;

            }

        }));
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
    public void onCancelClick(View v) {
        status.setText("Cancel!");
        ClearContent();
    }

    public void ClearContent(){
        trackinginfo.clear();
        ordid.setText("");
        trackingid.setText("");
        trackingids.setText("");
        ordid.requestFocus();
    }


}