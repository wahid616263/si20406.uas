package com.example.kontak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public CustomAdapter(Context context, ArrayList<KontakModel> items) {
        this.context = context;
        this.items = items;
    }

    Context context;
    ArrayList<KontakModel> items;

    CustomAdapterListener listener;

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.itemId.setText(items.get(position).getId());
        holder.itemNama.setText(items.get(position).getNama());
        holder.itemNo.setText(items.get(position).getNotelp());

        //Todo implement button delete
        holder.itemBtnHapus.setOnClickListener(v -> listener.removeDataFromCustomAdapter(position));

        try {
            listener = (CustomAdapterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement");
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemId, itemNama, itemNo;
        Button itemBtnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemId = itemView.findViewById(R.id.itemId);
            itemNama = itemView.findViewById(R.id.itemNama);
            itemNo = itemView.findViewById(R.id.itemNo);
            itemBtnHapus = itemView.findViewById(R.id.itemBtnHapus);
        }
    }

    public interface CustomAdapterListener {
        void removeDataFromCustomAdapter(int position);
    }
}
