package com.example.testing;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayHome  extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public DisplayHome(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.productimg);
        textView = itemView.findViewById(R.id.productname);
    }
}

