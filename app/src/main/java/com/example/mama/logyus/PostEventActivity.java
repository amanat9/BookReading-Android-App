package com.example.mama.logyus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tfb.fbtoast.FBToast;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PostEventActivity extends AppCompatActivity {

    private EditText eventEditText;
    private Button EventPostButton;
    private ImageView uploadEventPhoto;
    private Uri postImageUri=null;
    private FirebaseAuth firebaseAuth;
    private String currentUserId;
    private EditText editTextTitlePost;
    private ProgressBar progressBar;
    String title;
    String image;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        Intent intent = getIntent();
        String t = intent.getExtras().getString("title");
        String i = intent.getExtras().getString("image");
        title= t;
        image =i;



        storageReference=FirebaseStorage.getInstance().getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        EventPostButton=findViewById(R.id.eventPostButton);
        eventEditText = findViewById(R.id.editTextEvent);
        uploadEventPhoto=findViewById(R.id.uploadEventPhoto);
        editTextTitlePost=findViewById(R.id.editTextTitlePost);
        firebaseAuth=FirebaseAuth.getInstance();
        editTextTitlePost.setText(title);

        progressBar = findViewById(R.id.progressBar);

        Glide.with(getApplicationContext()).load(image)
                .into(uploadEventPhoto);
        EventPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });



        currentUserId= firebaseAuth.getCurrentUser().getUid();
        uploadEventPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }

        });


        EventPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String desc =eventEditText.getText().toString();
                // Toast.makeText(PostEventActivity.this,desc,Toast.LENGTH_LONG).show();
                if(!TextUtils.isEmpty(desc) )
                {




                            //String downloadUri= task.getResult().toString();
                            String downloadUri=image;
                            Map<String,Object> postMap=new HashMap<>();
                            String email= firebaseAuth.getCurrentUser().getEmail();
                            postMap.put("email",email);
                            postMap.put("image",downloadUri);
                            //String[] result = desc.split("\n", 2);
                            String title=editTextTitlePost.getText().toString();
                            postMap.put("title",title);

                            // if(result.length>1) {}
                            postMap.put("description", desc.toString());

                        /*else
                        {
                            postMap.put("description", "");
                        }*/



                            Date currentTime = Calendar.getInstance().getTime();
                            String time=currentTime.toString();





                            //post to firestore
                            firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful())
                                    {

                                        progressBar.setVisibility(View.INVISIBLE);
                                        FBToast.successToast(PostEventActivity.this,"Posted Successfully!",FBToast.LENGTH_SHORT);
                                        startActivity(new Intent(getApplicationContext(), Book_Search.class));
                                        finish();
                                    }
                                    else{
                                        String FireStorecry=task.getException().getMessage();
                                        Toast.makeText(PostEventActivity.this,FireStorecry,Toast.LENGTH_LONG).show();
                                    }

                                }

                            });


                } // if text not empty

            }//onclick
        });



    }




}
