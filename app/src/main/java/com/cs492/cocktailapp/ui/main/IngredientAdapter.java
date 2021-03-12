package com.cs492.cocktailapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs492.cocktailapp.R;
import com.cs492.cocktailapp.data.MeasureIngredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientItemViewHolder> {
    private ArrayList<MeasureIngredient> ingredientList;

    public IngredientAdapter() {
        this.ingredientList = new ArrayList<>();
    }

    @NonNull
    @Override
    public IngredientItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.measurement_list_item, parent, false);
        return new IngredientItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientItemViewHolder holder, int position) {
        holder.bind(this.ingredientList.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.ingredientList != null) {
            return this.ingredientList.size();
        }
        else {
            return 0;
        }
    }

    void updateIngredientListData(ArrayList<MeasureIngredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public class IngredientItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView measurementName;
        final private TextView measurementAmount;

        public IngredientItemViewHolder(@NonNull View itemView) {
            super(itemView);
            measurementName = itemView.findViewById(R.id.measurement_name);
            measurementAmount = itemView.findViewById(R.id.measurement_amount);
        }

        void bind(MeasureIngredient item) {
            if(item.getIngredient() != null && item.getIngredient() != "") {
                measurementName.setText(item.getIngredient());
            }
            if(item.getMeasurement() != null && item.getMeasurement() != "") {
                measurementAmount.setText(": " + item.getMeasurement());
            }
        }
    }


}
