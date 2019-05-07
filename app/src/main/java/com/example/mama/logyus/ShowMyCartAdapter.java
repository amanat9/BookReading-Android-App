package com.example.mama.logyus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ShowMyCartAdapter extends RecyclerView.Adapter<ShowMyCartAdapter.myViewHolder>{

    Context context;
    ArrayList<String> title;
    //ArrayList<String> image;
    ArrayList<String> price;


    class myViewHolder extends RecyclerView.ViewHolder
    {

        //ImageView imgView;
        TextView textView;
        //Button buttonReview;
        Button buttonAddtoCart;
        TextView textViewPrice;
        final DatabaseReference RootRef;
        FirebaseAuth firebaseAuth;

        public myViewHolder(View itemView) {
            super(itemView);
            //imgView = itemView.findViewById(R.id.book_img);
            textView = itemView.findViewById(R.id.mycart_book_title);
            // buttonReview=itemView.findViewById(R.id.buttonwriteReview);
            buttonAddtoCart=itemView.findViewById(R.id.mycart_addtoCart);
            textViewPrice=itemView.findViewById(R.id.mycart_book_price);
            RootRef=FirebaseDatabase.getInstance().getReference();
            firebaseAuth=FirebaseAuth.getInstance();
        }
    }



    public ShowMyCartAdapter(Context context, ArrayList<String> title, ArrayList<String> price) {
        this.context = context;
        this.title = title;
        //this.image = image;
        this.price = price;

        Log.e("mamalol", "adapter"+title.toString());
    }
    @NonNull
    @Override
    public ShowMyCartAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycart_view, viewGroup, false);
        return new ShowMyCartAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i)
    {
        Log.e("mamalol", "adapter"+title.get(i));
        Log.e("mamalol", "adapter"+price.get(i));

        myViewHolder.textView.setText(title.get(i));
        myViewHolder.textViewPrice.setText("Price: "+price.get(i));




    }

    @Override
    public int getItemCount() {
        return title.size();
    }



}