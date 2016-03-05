package com.meetmaps.app.getpersonas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView tName,tCargo,tEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tName = (TextView)findViewById(R.id.infoNombre);
        tCargo = (TextView)findViewById(R.id.infoCargo);
        tEmpresa = (TextView)findViewById(R.id.infoEmpresa);

        Bundle extra = getIntent().getExtras();
        tName.setText(extra.getString("p1"));
        tCargo.setText(extra.getString("p2"));
        tEmpresa.setText(extra.getString("p3"));

    }
}
