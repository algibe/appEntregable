package com.meetmaps.app.getpersonas;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView tName,tCargo,tEmpresa;
    private ImageView imageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tName = (TextView)findViewById(R.id.infoNombre);
        tCargo = (TextView)findViewById(R.id.infoCargo);
        tEmpresa = (TextView)findViewById(R.id.infoEmpresa);
        imageInfo = (ImageView)findViewById(R.id.imageInfo);

        Bundle extras = getIntent().getExtras();

        Person person = (Person) extras.getSerializable("detailPerson");
        if (person != null) {
            tName.setText(person.getUsername());
            tCargo.setText(person.getPosition());
            tEmpresa.setText(person.getCompany());
        }

        imageInfo.setImageResource(extras.getInt("imageInfo"));

    }
}
