package com.entrolabs.testapplications.fininfocomtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.entrolabs.testapplications.R;

import java.util.List;

public class DummyDataAdapter extends RecyclerView.Adapter<DummyDataAdapter.ViewHolder> {

    private List<DummyData> dataList;

    public DummyDataAdapter(List<DummyData> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dummy_data, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DummyData data = dataList.get(position);
        holder.titleTextView.setText(data.getTitle());
        holder.descriptionTextView.setText(data.getDescription());
        holder.ageTextView.setText(String.valueOf(data.getAge()));
        holder.cityTextView.setText(data.getCity());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView ageTextView;
        TextView cityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
        }
    }
}
