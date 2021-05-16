package com.example.newsinformation.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsinformation.R;
import com.example.newsinformation.activity.main.PictureActivity;
import com.example.newsinformation.po.Picture;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends  RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Picture> pictureList = new ArrayList<>() ;
    private Context context;
    public PictureAdapter(List<Picture> pictureList,Context context) {
        this.pictureList = pictureList;
        this.context = context;
    }
    static  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;

        public ViewHolder( View view) {
            super(view);
            picture = view.findViewById(R.id.img_picture);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item,parent,false);
        final  PictureAdapter.ViewHolder holder = new PictureAdapter.ViewHolder(view);//创建唯一的holder实例
        holder.picture.setOnClickListener(new View.OnClickListener() {//为imageView添加点击事件
            @Override
            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                Intent intent = new Intent(context, PictureActivity.class);
//                intent.putExtra("picture", (Parcelable) pictureList.get(position));
//                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String path = pictureList.get(position).getUrl();
        StringBuffer sbf = new StringBuffer(path);
        sbf.insert(4,'s');//将http变为https
        Log.i("URL",sbf.toString());
        URL url;
        try {
            url = new URL(path);
            Glide.with(context).load(url).into(holder.picture);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }
}
