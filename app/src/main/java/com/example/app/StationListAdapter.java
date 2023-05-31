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
    ArrayList<StationItem> item_list;
    int layout;

    public StationListAdapter(Context context, int alayout, ArrayList<StationItem> arr){
        mcontext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
        item_list = arr;
        layout = alayout;
    }

    @NonNull
    @Override
    public StationListAdapter.StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stationitem, parent, false);
        StationListAdapter.StationViewHolder viewHolder = new StationListAdapter.StationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StationListAdapter.StationViewHolder holder, int position) {
        StationItem data = item_list.get(position);

        //데이터 결합
        holder.nameText.setText(data.getName());
        holder.idText.setText(data.getId()+"");
    }

    @Override
    public long getItemId(int i) {
        return item_list.get(i).id;
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    class StationViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView idText;


        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.station_name);
            idText = (TextView) itemView.findViewById(R.id.station_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //아이템 터치 시 동작 상위 뷰에서 콘텐츠 변환
                }
            });

        }
    }

}
