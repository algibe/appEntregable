package com.meetmaps.app.getpersonas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class personasAdapter extends BaseAdapter {

    String url;
    private Activity activity;
    private ArrayList<Person>  data;
    private LayoutInflater inflater;
    private int item_layout;

    private Context context;

    public personasAdapter (Activity activity, ArrayList<Person> data, int item_layout){
        this.activity=activity;
        this.data=data;
        this.item_layout=item_layout;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = this.inflater.inflate(item_layout,null);
        }

        TextView id = (TextView) convertView.findViewById(R.id.Id);
        TextView nombre = (TextView) convertView.findViewById(R.id.name);
        TextView cargo = (TextView) convertView.findViewById(R.id.position);
        TextView empresa = (TextView) convertView.findViewById(R.id.company);

        ImageView image = (ImageView) convertView.findViewById(R.id.deletePerson);

        id.setText(data.get(position).getIdPerson());
        nombre.setText(data.get(position).getUsername());
        cargo.setText(data.get(position).getPosition());
        empresa.setText(data.get(position).getCompany());
        //añadir imagen



        return  convertView;
    }
}
