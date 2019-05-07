package com.example.mama.logyus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<MainActivity> mData;

    public RecyclerViewAdapter(Context mContex, List<MainActivity> mData) {
        this.mContext = mContex;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_items_books,viewGroup,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_book_title.setText(mData.get(i).getBook_Title());
        myViewHolder.img_book_thumbnail.setImageResource(mData.get(i).getThumbnail());

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passing data to the book activity
                Intent intent = new Intent(mContext,Book_Activity.class);
                intent.putExtra("Title",mData.get(i).getBook_Title());
                intent.putExtra("Category",mData.get(i).getCategory());
                intent.putExtra("Description",mData.get(i).getDescription());
                intent.putExtra("Thumbnail",mData.get(i).getThumbnail());
                mContext.startActivity(intent);



            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_book_title = itemView.findViewById(R.id.book_title);
            img_book_thumbnail = itemView.findViewById(R.id.book_image);
            cardView = itemView.findViewById(R.id.cardView_id);


        }
    }
}
