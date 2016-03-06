package com.meetmaps.app.getpersonas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetmaps.app.getpersonas.Modelo.Person;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if(getResources().getConfiguration().smallestScreenWidthDp < 600) {

            TextView tName = (TextView) findViewById(R.id.infoNombre);
            TextView tCargo = (TextView) findViewById(R.id.infoCargo);
            TextView tEmpresa = (TextView) findViewById(R.id.infoEmpresa);
            ImageView imageInfo = (ImageView) findViewById(R.id.imageInfo);

            Bundle extras = getIntent().getExtras();

            Person person = (Person) extras.getSerializable(GetActivity.safr_detail);
            if (person != null) {
                tName.setText(person.getUsername());
                tCargo.setText(person.getPosition());
                tEmpresa.setText(person.getCompany());
            }

            imageInfo.setImageResource(extras.getInt(GetActivity.safr_image));
        }

    }
}
