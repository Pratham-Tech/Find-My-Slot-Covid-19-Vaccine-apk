package app.cowin.findmyslot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import app.cowin.findmyslot.R;
import app.cowin.findmyslot.dataClass.Slots;
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
        holder.vaccineName.setText(slot.getVaccineName());
        holder.centerAddress.setText(slot.getAddress());
        holder.dose1.setText(slot.getDose1());
        holder.dose2.setText(slot.getDose2());
        holder.fee_type.setText(slot.getFeeType());
        holder.ageLimit.setText(slot.getAgeLimit());
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    static class SlotViewHolder extends RecyclerView.ViewHolder{

        TextView centerName;
        TextView vaccineName;
        TextView centerAddress;
        TextView dose1;
        TextView dose2;
        TextView fee_type;
        TextView ageLimit;

        SlotViewHolder(View v){
            super(v);

            centerName = v.findViewById(R.id.centerName);
            vaccineName = v.findViewById(R.id.vaccineName);
            centerAddress = v.findViewById(R.id.centerAddress);
            dose1 = v.findViewById(R.id.dose1Txt);
            dose2 = v.findViewById(R.id.dose2Txt);
            fee_type = v.findViewById(R.id.feeType);
            ageLimit = v.findViewById(R.id.ageLimit);
        }
    }
}