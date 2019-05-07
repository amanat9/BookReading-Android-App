package com.example.mama.logyus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class UpcomingEventActivity extends AppCompatActivity {

    private RecyclerView EventPostView;
    private List<UEvent> UeventList;
    private FirebaseFirestore firebaseFirestore;
    private UpcomingEventAdapter upcomingEventAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_event);

        EventPostView=findViewById(R.id.EventPostView);
        UeventList=new ArrayList<>();
        upcomingEventAdapter=new UpcomingEventAdapter(UeventList);
        EventPostView.setLayoutManager(new LinearLayoutManager(this));
        EventPostView.setAdapter(upcomingEventAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();
        Query firstQ= firebaseFirestore.collection("Posts").orderBy("email",Query.Direction.DESCENDING);

        firstQ.addSnapshotListener(this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges())
                {
                    if(doc.getType() == DocumentChange.Type.ADDED)
                    {
                        String uEventId=doc.getDocument().getId();
                        UEvent uEvent=doc.getDocument().toObject(UEvent.class).withId(uEventId);
                        UeventList.add(uEvent);


                        upcomingEventAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}
