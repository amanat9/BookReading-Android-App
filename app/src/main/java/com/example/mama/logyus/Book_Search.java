package com.example.mama.logyus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class Book_Search extends AppCompatActivity{


    EditText searchBar;
    RecyclerView recyclerView2;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> title;
    ArrayList<String> image;
    ArrayList<String> price;
    SearchAdapter searchAdapter;
    Button button;
    Button buttonShowMyCart;
    Button button3;
    Button buttonaddtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("bhul","start hello pls");
        setContentView(R.layout.fragment_favourite_);
        title = new ArrayList<>();
        image = new ArrayList<>();
        price = new ArrayList<>();

        searchBar = findViewById(R.id.searchText);
        recyclerView2 = findViewById(R.id.recyclerView2);
        databaseReference = FirebaseDatabase.getInstance().getReference();



        button = findViewById(R.id.buttonEbook);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book_Search.this, EbooksActivity.class));
            }
        });

       buttonShowMyCart = findViewById(R.id.buttonShowMyCart);
        buttonShowMyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"mama sesh",Toast.LENGTH_LONG).show();
                Log.e("mamalol", "Dhukar age  ShowCartAct"+"hello pls");
                startActivity(new Intent(Book_Search.this, ShowMyCartActivity.class));
            }
        });
        button3 = findViewById(R.id.buttonSeeReview);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book_Search.this, UpcomingEventActivity.class));
            }
        });





        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Log.e("bhul","text change next");


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    Log.e("bhul","start adapter");
                    setAdapter(s.toString());
                } else {
                    //title.clear();
                    //image.clear();
                    //recyclerView2.removeAllViews();
                }

            }
        });






    }

    private void setAdapter(final String s) {


        databaseReference.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                title.clear();
                image.clear();
                price.clear();
                recyclerView2.removeAllViews();
                //Log.e("bhul","start adapter"+s);

                int c = 0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {

                    String b_title = snapshot.child("title").getValue(String.class);
                    String b_image = snapshot.child("image").getValue(String.class);
                    String b_price = snapshot.child("price").getValue(String.class);
                    Log.e("bhul", b_price+"hello pls");
                    //Log.e("bhul", b_image+"hello pls");
                    if (b_title.toLowerCase().startsWith(s.toLowerCase()))
                    {
                        title.add(b_title);
                        image.add(b_image);
                        price.add(b_price);
                        c++;
                    }

                    if (c >15)
                    {
                        break;
                    }


                    searchAdapter = new SearchAdapter(Book_Search.this, title,image,price );
                    recyclerView2.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
