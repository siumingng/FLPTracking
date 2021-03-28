package com.example.flptracking;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.sql.*;
import android.os.AsyncTask;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> trackinginfo = new ArrayList<String>();
    EditText ordid ;
    EditText trackingid ;
    EditText trackingids;
    EditText status;
    Button  cancelbutton;
    Button  savebutton;
    private Connection connection = null;
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";

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
    public void onSaveClick(View v) {
        DBHelper savetodb = new DBHelper();
        savetodb.execute();
    }

    public void ClearContent(){
        trackinginfo.clear();
        ordid.setText("");
        trackingid.setText("");
        trackingids.setText("");
        ordid.requestFocus();
    }

    private class DBHelper extends AsyncTask<String,Void,String>{
        String message;
        @Override
        protected String doInBackground(String... urls) {
            try {
                String sql;
                int n;
                Class.forName(Classes);
                connection= DriverManager.getConnection("jdbc:jtds:sqlserver://172.23.4.250:1433/flpndp","sa","p@ssw0rd");
                Statement sqlcmd= connection.createStatement();
                for (int i=0; i<trackinginfo.size();i++)
                {
                    sql ="Insert into tbl_tracking (ord_id,tracking_id) values (" + ordid.getText() + ", '" + trackinginfo.get(i) +"' )";
                    n = sqlcmd.executeUpdate(sql);
                }

                message= "Records Inserted";
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                message= "Error";
            }
            catch (SQLException e)
            {
                message= "Error";
            } catch (Exception e)
            {
                message= "Error";

            }
            return message;
        }
        @Override
        protected void onPostExecute(String result) {
            if (result=="Error")
            {
                status.setText(Html.fromHtml("<font color='#ff0000'>Error</font>"));
            }else
            {
                status.setText(Html.fromHtml("<font color='#00ff00'>Records Saved!</font>"));
            }

            ClearContent();
        }

    }


}

