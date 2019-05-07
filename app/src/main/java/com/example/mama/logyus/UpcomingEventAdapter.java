package com.example.mama.logyus;

/**
 * Created by 16101219 on 12/4/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.ViewHolder>

{
    List<UEvent> UeventList;
    public Context context;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    String currentUserId;



    public UpcomingEventAdapter(List<UEvent> UeventList)
    {
        this.UeventList=UeventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcomingevent_list_item, viewGroup,false);
        context=viewGroup.getContext();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final String eventId = UeventList.get(i).uEventId;
        currentUserId= firebaseAuth.getCurrentUser().getUid();

        String descData=UeventList.get(i).getDescription();
        viewHolder.setDescView(descData);

        String image_url=UeventList.get(i).getImage();
        viewHolder.setEventImage(image_url);


        String time=UeventList.get(i).getEmail();
        final String title=UeventList.get(i).getTitle();

        viewHolder.setTimeandTitle(time,title);










        //User Data will be retrieved here...



    }

    @Override
    public int getItemCount() {
        return UeventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private View mview;
        private TextView descView;
        private ImageView eventImageView;
        private TextView etime;
        private TextView etitle;


        private TextView usernamePost;
        private ImageView imageViewPostUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview =itemView;


        }

        public void setDescView(String text)
        {
            descView=mview.findViewById(R.id.editTextEventPost);
            descView.setText(text);
        }

        public void setEventImage(String downloadUri)
        {
            eventImageView=mview.findViewById(R.id.eventPostPhoto1);
            Glide.with(context).load(downloadUri).into(eventImageView);

        }

        public void setTimeandTitle(String time,String title)
        {
            etime=mview.findViewById(R.id.time);
            etime.setText(time);
            etitle=mview.findViewById(R.id.titleEvent);
            etitle.setText(title);

        }

        public void setUserData(String userName, String userImage)
        {



        }
    }

}
