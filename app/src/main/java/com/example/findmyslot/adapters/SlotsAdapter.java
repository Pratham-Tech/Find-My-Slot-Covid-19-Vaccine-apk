package com.example.findmyslot.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.findmyslot.R;
import java.util.ArrayList;

public class SlotsAdapter extends RecyclerView.Adapter<SlotViewHolder>{

    ArrayList<String> items;

    public SlotsAdapter(ArrayList<String> items) {

        this.items = items;
    }
    @Override
    public SlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slots_screen,parent,false);

        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SlotViewHolder holder, int position) {

        holder.centerName.setText(items.get(position));
        holder.vaccinationDate.setText(items.get(position));
        holder.vaccineName.setText(items.get(position));
        holder.slotAvailable.setText(items.get(position));
        holder.centerAddress.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
class SlotViewHolder extends RecyclerView.ViewHolder{

    TextView vaccinationDate;
    TextView centerName;
    TextView vaccineName;
    TextView slotAvailable;
    TextView centerAddress;

    SlotViewHolder(View v){
        super(v);

        vaccinationDate = v.findViewById(R.id.vaccinationDate);
        centerName = v.findViewById(R.id.centerName);
        vaccineName = v.findViewById(R.id.vaccineName);
        slotAvailable = v.findViewById(R.id.slotsAvailable);
        centerAddress = v.findViewById(R.id.centerAddress);

    }
}

