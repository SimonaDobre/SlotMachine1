package com.simona.slotmachine1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderSlot> {

    ArrayList<Slot> slotsArray;

    public Adapter(ArrayList<Slot> slotsArray) {
        this.slotsArray = slotsArray;
    }

    class ViewHolderSlot extends RecyclerView.ViewHolder{

        TextView slotTV;
        public ViewHolderSlot(@NonNull View itemView) {
            super(itemView);
            slotTV = itemView.findViewById(R.id.slotTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolderSlot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderSlot(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.slot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSlot holder, int position) {
        Slot currentSlot = slotsArray.get(position);
        holder.slotTV.setText(String.valueOf(currentSlot.getValue()));
    }

    @Override
    public int getItemCount() {
        return slotsArray.size();
    }

    public void setSlotsArray(ArrayList<Slot> slotsArray) {
        this.slotsArray = slotsArray;
        notifyDataSetChanged();
    }
}
