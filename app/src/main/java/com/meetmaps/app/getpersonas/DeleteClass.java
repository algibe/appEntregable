package com.meetmaps.app.getpersonas;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteClass{

    private static final String REGISTER_URLdel = "http://orlarium.com/meetmaps/meetmaps_api/api.php";
    public static final String DELETE_ACTION = "action";
    public static final String ID_ACTION = "id";

    public static void deleteUser(final String id,Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URLdel,
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
                params.put(DELETE_ACTION,"personas_del");
                params.put(ID_ACTION,id);
                return params;
            }

        };

        MySocialMediaSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
