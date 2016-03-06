package com.meetmaps.app.getpersonas;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.meetmaps.app.getpersonas.Modelo.Person;
import com.meetmaps.app.getpersonas.Singleton.MySocialMediaSingleton;

import java.util.HashMap;
import java.util.Map;

public class DeleteClass{

    private static String action = "personas_del";

    public static void deleteUser(final String id,Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Person.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(Person.KEY_ACTION,action);
                params.put(Person.KEY_ID,id);
                return params;
            }

        };

        MySocialMediaSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
