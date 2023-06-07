package com.example.testing;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<DisplayHome> {
    Context context;
    ArrayList<ProductListResponse.Futureproduct> futureproducts;



    public RecycleViewAdapter(Context context, ArrayList<ProductListResponse.Futureproduct> futureproducts) {
        this.context = context;
        this.futureproducts = futureproducts;
    }

    @NonNull
    @Override
    public DisplayHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DisplayHome(LayoutInflater.from(context).inflate(R.layout.card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayHome holder, int position) {

        Picasso.get().load(futureproducts.get(position).images.get(0).images).into(holder.imageView);
        holder.textView.setText(futureproducts.get(position).productname);

        holder.itemView.setOnClickListener(v -> {

            Log.d("poil", "onBindViewHolder: "+position);
            Intent i=new Intent(context,ProductDetailActivity.class);
            i.putExtra("id", position);
            context.startActivity(i);


        });

    }

    @Override
    public int getItemCount() {
        return futureproducts.size();
    }
}
