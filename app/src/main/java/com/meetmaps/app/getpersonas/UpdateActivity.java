package com.meetmaps.app.getpersonas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private static final String REGISTER_URL2 = "http://orlarium.com/meetmaps/meetmaps_api/api.php";
    private String action = "personas_upd";

    private String upId;
    private EditText upName;
    private EditText upCargo;
    private EditText upEmpresa;

    private String nameMap;
    private String cargoMap;
    private String empresaMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        upName = (EditText)findViewById(R.id.update_nombre);
        upCargo = (EditText)findViewById(R.id.update_cargo);
        upEmpresa = (EditText)findViewById(R.id.update_empresa);

        Intent receta = getIntent();
        if (receta.hasExtra("updatePerson")) {

            Person updatePerson = (Person) receta.getSerializableExtra("updatePerson");
            upName.setText(updatePerson.getUsername());
            upCargo.setText(updatePerson.getPosition());
            upEmpresa.setText(updatePerson.getCompany());
            upId = updatePerson.getIdPerson();

        }

    }

    private void updateUser(){

        nameMap = upName.getText().toString().trim();
        cargoMap = upCargo.getText().toString().trim();
        empresaMap = upEmpresa.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateActivity.this, "error no va", Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("action",action);
                params.put("id",upId);
                params.put("nombre",nameMap);
                params.put("cargo",cargoMap);
                params.put("empresa",empresaMap);
                return params;
            }

        };

        MySocialMediaSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.upd_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.saveUpdate:

                updateUser();

                Person updatedPerson = new Person(upId,nameMap,cargoMap,empresaMap);
                Intent i = new Intent(this,GetActivity.class);
                i.putExtra("nuevaPersona",updatedPerson);
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
