package com.example.findmyslot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.findmyslot.R;
import com.example.findmyslot.dataClass.Slots;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotViewHolder>{

    ArrayList<Slots> slots;
    Context c;

    public SlotsAdapter(Context c, ArrayList<Slots> slots) {

        this.slots = slots;
        this.c = c;
    }
    @Override
    public @NotNull SlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.slots_screen,parent,false);
        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SlotViewHolder holder, int position) {

        Slots slot = slots.get(position);

        holder.centerName.setText(slot.getCenterName());
        holder.vaccinationDate.setText(slot.getDate());
        holder.vaccineName.setText(slot.getVaccineName());
        holder.slotAvailable.setText(slot.getBlock_name());
        holder.centerAddress.setText(slot.getAddress());
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    static class SlotViewHolder extends RecyclerView.ViewHolder{

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

}