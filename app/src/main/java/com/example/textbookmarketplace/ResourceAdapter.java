package com.example.textbookmarketplace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private List<Textbook> resourceList;
    private OnItemClickListener listener;

    // Interface for the WhatsApp Click
    public interface OnItemClickListener {
        void onItemClick(Textbook textbook);
    }

    // The Constructor (This was likely missing or broken in your version!)
    public ResourceAdapter(List<Textbook> resourceList, OnItemClickListener listener) {
        this.resourceList = resourceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_textbook, parent, false);
        return new ResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        Textbook currentItem = resourceList.get(position);

        holder.tvTitle.setText(currentItem.getTitle());
        holder.tvSeller.setText("Listed by " + currentItem.getSeller());
        holder.tvPrice.setText("R " + currentItem.getPrice());

        // Handle the click event to trigger WhatsApp
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resourceList.size();
    }

    public static class ResourceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSeller, tvPrice, tvIcon;

        public ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSeller = itemView.findViewById(R.id.tvSeller);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }
}