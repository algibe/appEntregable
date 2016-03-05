package com.meetmaps.app.getpersonas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteActivity extends AppCompatActivity{

    private static final String REGISTER_URLdel = "http://orlarium.com/meetmaps/meetmaps_api/api.php";
    public static final String DELETE_ACTION = "action";
    public static final String ID_ACTION = "id";

    private EditText actionDelete;
    private EditText idDel;
    private TextView errorDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        actionDelete = (EditText)findViewById(R.id.actionDelete);
        idDel = (EditText)findViewById(R.id.idPersona);
        errorDel = (TextView)findViewById(R.id.textoError);

    }

    public void deleteUser(){

        final String actionDel = actionDelete.getText().toString().trim();
        final String idPersona = idDel.getText().toString().trim();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URLdel,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        errorDel.setText(response);
                        Toast.makeText(DeleteActivity.this,response,Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DeleteActivity.this, "error no va", Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(DELETE_ACTION,actionDel);
                params.put(ID_ACTION,idPersona);
                return params;
            }

        };

        MySocialMediaSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        //requestQueue.add(stringRequest);
    }

    public void deleteId(View view) {

        deleteUser();
    }
}
