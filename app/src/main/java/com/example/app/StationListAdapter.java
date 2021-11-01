package com.example.app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

class StationItem {
    int id;
    String name;
    StationItem(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

public class StationListAdapter extends RecyclerView.Adapter {
    Context mcontext;
    LayoutInflater layoutInflater;
    ArrayList<StationItem> itemlist;
    int layout;

    public StationListAdapter(Context context, int alayout, ArrayList<StationItem> arr){
        mcontext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
        itemlist = arr;
        layout = alayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int i) {
        return itemlist.get(i).id;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
