package com.example.mama.logyus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

//Import xyz


public class Book_Activity extends AppCompatActivity {

    private TextView tvTitle,tvCategory,tvDescription;
    private ImageView imageView;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        final RatingBar ratingBar = findViewById(R.id.rating_bar);
        final Button submit_button = findViewById(R.id.submit_button);
        final Button searchButton = findViewById(R.id.submit_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit_button.isPressed()) {
                    Toast.makeText(getBaseContext(), "Your rating for this book is " + ratingBar.getRating() + " stars!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvTitle = findViewById(R.id.txtTitle);
        tvCategory = findViewById(R.id.txtCategory);
        tvDescription = findViewById(R.id.txtDescription);
        imageView = findViewById(R.id.book_thumbnail);

        //Receive data here
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Category = intent.getExtras().getString("Category");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");


        //Setting the values
        tvTitle.setText(Title);
        tvCategory.setText(Category);
        tvDescription.setText(Description);
        imageView.setImageResource(image);

    }
}
