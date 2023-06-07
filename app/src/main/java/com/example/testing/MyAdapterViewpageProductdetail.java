package com.example.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
//
//public class MyAdapterViewpageProductdetail extends PagerAdapter {
//   Context context;
//   ArrayList<Integer>arrayList;
//   LayoutInflater layoutInflater;
//
//    public MyAdapterViewpageProductdetail(Context context, ArrayList<Integer> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//        layoutInflater=LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view.equals(object);
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view;
//        view = layoutInflater.inflate(R.layout.item_detail,container,position);
//        ImageView imageView=view.findViewById(R.id.productimage1);
//        imageView.setImageResource(arrayList.get(position));
//        view.setOnClickListener(v -> {
//
//        });
//        return view;
//    }
//
//}
