package com.example.mama.logyus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tfb.fbtoast.FBToast;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> title;
    ArrayList<String> image;
    ArrayList<String> price;


    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView textView;
        Button buttonReview;
        Button buttonAddtoCart;
        TextView textViewPrice;
        final DatabaseReference RootRef;
        FirebaseAuth firebaseAuth;

        public SearchViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.book_img);
            textView = itemView.findViewById(R.id.search_book_title);
            buttonReview=itemView.findViewById(R.id.buttonwriteReview);
            buttonAddtoCart=itemView.findViewById(R.id.search_addtoCart);
            textViewPrice=itemView.findViewById(R.id.search_book_price);
            RootRef=FirebaseDatabase.getInstance().getReference();
            firebaseAuth=FirebaseAuth.getInstance();
        }
    }

    public SearchAdapter(Context context, ArrayList<String> title, ArrayList<String> image , ArrayList<String> price) {
        this.context = context;
        this.title = title;
        this.image = image;
        this.price = price;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_book, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {

        final String email= holder.firebaseAuth.getCurrentUser().getEmail();
        final DatabaseReference ref=holder.RootRef;
        final String Uid= holder.firebaseAuth.getCurrentUser().getUid();

        Log.e("mamalol", "adapter er vitor"+title.get(position));
        Log.e("mamalol", "adapter er vitor"+price.get(position));
        holder.textView.setText(title.get(position));
        holder.textViewPrice.setText("Price: "+price.get(position));


        //Glide.with(context).load(image.get(position)).fitCenter().placeHolder(R.mipmap.ic_launcher_round).into(holder.imgView);
        Glide.with(context).load(image.get(position))
                .into(holder.imgView);

        holder.buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,PostEventActivity.class);
                i.putExtra("title",title.get(position));
                i.putExtra("image",image.get(position));
                context.startActivity(i);

            }
        });


        holder.buttonAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("mama","hello"+email);

                HashMap<String , Object> dataMap= new HashMap<>();
                dataMap.put("b_name", title.get(position));
                dataMap.put("b_price", price.get(position));

                dataMap.put("b_email", email);
                ref.child("orders").child(Uid).child((title.get(position).replace(" ",""))).updateChildren(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("mama","hello Success");
                        FBToast.successToast(context,"Added to Cart!", FBToast.LENGTH_LONG);

                    }
                });






            }
        });

    }


    @Override
    public int getItemCount() {
        return title.size();
    }
}
