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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

class StationItem {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    StationItem(String name, int id) {
        this.name = name;
        this.id = id;


    }
}

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.StationViewHolder> {
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
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stationitem, parent, false);
        StationViewHolder viewHolder = new StationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        StationItem data = itemlist.get(position);

        //데이터 결합
        holder.nameText.setText(data.getName());
    }

    @Override
    public long getItemId(int i) {
        return itemlist.get(i).id;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StationViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView id;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.stationname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //아이템 터치 시 동작
                }
            });

        }
    }

}
