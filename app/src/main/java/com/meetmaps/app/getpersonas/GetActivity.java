package com.meetmaps.app.getpersonas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.MultiChoiceModeListener{

    private static final String REGISTER_URL = "http://orlarium.com/meetmaps/meetmaps_api/api.php";
    public static final String KEY_PERSONAS = "action";
    public static final String key_get = "personas_get";
    private ArrayList<Person> arrayPersona = new ArrayList<>();
    private ListView listView;
    private personasAdapter adapter;
    private static Context context;
    private ProgressDialog PD;

    Person personSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(true);

        context = this.getApplicationContext();

        listView = (ListView)findViewById(R.id.listView);

        adapter = new personasAdapter(this, arrayPersona, R.layout.personas_layout);
        listView.setOnItemClickListener(this);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        getPerson();

    }



    //----------------------------------------------------------------------------------------------
    // Get context to arrayAdapter
    public static Context getContext(){
        return context;
    }

    //----------------------------------------------------------------------------------------------
    // Metodo que gestiona las llamadas API
    private void getPerson(){

        arrayPersona.clear();
        PD.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            parseJSON(response);
                            PD.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
                params.put(KEY_PERSONAS,key_get);
                return params;
            }

        };

        MySocialMediaSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    //----------------------------------------------------------------------------------------------
    // Parsea el json de la respuesta
    private void parseJSON(String response) throws JSONException {

        JSONObject mainObject = new JSONObject(response);
        //String error = mainObject.getString("error");
        //String errorName = mainObject.getString("error_name");

        JSONArray jArray = mainObject.getJSONArray("personas");

        for(int i=0; i<jArray.length(); i++){

            JSONObject jsonObjectArray = jArray.getJSONObject(i);
            String arrayId = jsonObjectArray.getString("id_persona");
            String arrayName = jsonObjectArray.getString("nombre");
            String arrayCargo = jsonObjectArray.getString("cargo");
            String arrayEmpresa = jsonObjectArray.getString("empresa");

            Person personas = new Person(arrayId,arrayName,arrayCargo,arrayEmpresa);
            arrayPersona.add(personas);
        }

        listView.setAdapter(adapter);
    }

    //----------------------------------------------------------------------------------------------
    // Acciones sobre la list view
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent info = new Intent(this,InfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("detailPerson",arrayPersona.get(position));
        bundle.putInt("imageInfo",getResources().getIdentifier("ic_delete","mipmap",this.getPackageName()));
        info.putExtras(bundle);
        startActivity(info);

    }


    //----------------------------------------------------------------------------------------------
    // Contextual action mode
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        personSelected = (Person) listView.getItemAtPosition(position);
        mode.setTitle(personSelected.getUsername());
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        switch (item.getItemId()){
            case R.id.context_delete:
                Toast.makeText(this,mode.getTitle()+ " borrado",Toast.LENGTH_LONG).show();
                DeleteClass.deleteUser(personSelected.getIdPerson(), getContext());
                arrayPersona.remove(personSelected);
                adapter.notifyDataSetChanged();
                mode.finish();
                return true;

            case R.id.context_update:
                Intent i = new Intent(GetActivity.getContext(),UpdateActivity.class);
                i.putExtra("updatePerson",personSelected);
                startActivityForResult(i,1);
                mode.finish();
                return true;

            default:
                return false;
        }

    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }


    //----------------------------------------------------------------------------------------------
    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addPerson:
                Intent i = new Intent(this,InsertActivity.class);
                startActivityForResult(i,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

            Person person = (Person) data.getSerializableExtra("nuevaPersona");

            switch (requestCode){
                case 0://Insert
                    getPerson();
                    break;

                case 1://Update
                    arrayPersona.set(arrayPersona.indexOf(personSelected),person);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }



}