package com.meetmaps.app.getpersonas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.meetmaps.app.getpersonas.Modelo.Person;
import com.meetmaps.app.getpersonas.Singleton.MySocialMediaSingleton;

import java.util.HashMap;
import java.util.Map;

    public class InsertActivity extends AppCompatActivity{

        private String action = "personas_add";

        private EditText name;
        private EditText cargo;
        private EditText empresa;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_insert);

            name = (EditText)findViewById(R.id.iName);
            cargo = (EditText)findViewById(R.id.iCargo);
            empresa = (EditText)findViewById(R.id.iEmpresa);

        }

        private void registerUser(){

            final String nameMap = name.getText().toString().trim();
            final String cargoMap = cargo.getText().toString().trim();
            final String empresaMap = empresa.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Person.REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }){

                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();
                    params.put(Person.KEY_ACTION,action);
                    params.put(Person.KEY_NOMBRE,nameMap);
                    params.put(Person.KEY_CARGO,cargoMap);
                    params.put(Person.KEY_EMPRESA,empresaMap);
                    return params;
                }

            };


            MySocialMediaSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.add_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.saveAdd:
                    registerUser();
                    Intent i = new Intent(this,GetActivity.class);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
