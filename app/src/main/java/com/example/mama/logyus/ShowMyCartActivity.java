package com.example.mama.logyus;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShowMyCartActivity extends AppCompatActivity {

    RecyclerView  recyclerViewMycart;
    DatabaseReference databaseReference;
    DatabaseReference orderReference;
    FirebaseUser firebaseUser;
    ArrayList<String> title;
    String Uid;
    ListView lv;
    ArrayList<String> values;
    ArrayList<String> price;
    //ShowMyCartAdapter showMyCartAdapter;
    SearchAdapter1 showMyCartAdapter;





    public static final String TAG = "paymentExample";
    public static final String PAYPAL_KEY = "AoJkZDhEa2jOHD9zr2EbbWTK1-7tAnebsYvRpstDhGqo0mEebc0p1ft8";
    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static PayPalConfiguration config;
    PayPalPayment thingsToBuy;
    Button order;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_show_my_cart);

        order = findViewById(R.id.buttonOrderPaypal);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakePayment();
            }
        });
        configPaypal();

        Log.e("mamalol", "entered ShowCartAct"+"hello pls");
        title = new ArrayList<>();
        values=new ArrayList<String>();
        price = new ArrayList<>();
        lv=findViewById(R.id.lisviewforMycart);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Uid=firebaseUser.getUid();
        //recyclerViewMycart = findViewById(R.id.recyclerViewMycart);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        orderReference=FirebaseDatabase.getInstance().getReference("orders").child(Uid);


        Log.e("mamalol", Uid+"hello pls");
        mamaGetData();


    }

    private void mamaGetData() {
        databaseReference.child("orders").child(""+Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //title.clear();

                //price.clear();
                //recyclerViewMycart.removeAllViews();
                //Log.e("bhul","start adapter"+s);

                //int c = 0;
                Log.e("mamalol", "mama on data change er vitor");
                Log.e("mamalol",""+dataSnapshot.getChildrenCount());

                final String [] val = new String[(int)dataSnapshot.getChildrenCount()];
                int c=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {

                    String b_title = snapshot.child("b_name").getValue(String.class);
                    //String b_image = snapshot.child("image").getValue(String.class);
                    String b_price = snapshot.child("b_price").getValue(String.class);
                    Log.e("mamalol", b_title+"hello pls");
                    Log.e("mamalol", b_price+"hello pls");
                    //Log.e("bhul", b_image+"hello pls");

                    title.add(b_title);

                    price.add(b_price);
                    values.add(b_title+"\n"+b_price);
                    val[c]=b_title+"\n"+"price: "+b_price;
                    c++;
                    Log.e("mamalol", "adapter e dhukar age price "+price.size());
                    Log.e("mamalol", "adapter e dhukar age title"+title.size());





                }
                Log.e("mamalol", "adapter e dhukar age price"+price.toString());
                Log.e("mamalol", "adapter e dhukar age title "+title.toString());
                //showMyCartAdapter = new ShowMyCartAdapter (ShowMyCartActivity.this, title,price );



                final ArrayAdapter<String> adapter= new ArrayAdapter<String>(ShowMyCartActivity.this,android.R.layout.simple_expandable_list_item_1,values);
                //showMyCartAdapter = new SearchAdapter1 (ShowMyCartActivity.this, title,price );
                //recyclerViewMycart.setAdapter(showMyCartAdapter);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        AlertDialog.Builder adb=new AlertDialog.Builder(ShowMyCartActivity.this);
                        adb.setTitle("Delete?");
                        adb.setMessage("Are you sure you want to delete " + position);
                        final int positionToRemove = position;
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String book_id=values.get(positionToRemove).replace(" ","");
                                Log.e("mamalol", "deleteMama"+book_id);
                                orderReference.child(book_id).setValue(null);
                                values.remove(positionToRemove);
                                adapter.notifyDataSetChanged();
                            }});
                        adb.show();
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });
    }


    private void configPaypal() {
        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(PAYPAL_KEY)
                .merchantName("Paypal Login")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    }

    private void MakePayment() {
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        thingsToBuy = new PayPalPayment(new BigDecimal(String.valueOf("10.45")),"USD","Payment",PayPalPayment.PAYMENT_INTENT_SALE );
        Intent payment = new Intent(this, PaymentActivity.class);
        payment.putExtra(PaymentActivity.EXTRA_PAYMENT,thingsToBuy);
        payment.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startActivityForResult(payment,REQUEST_CODE_PAYMENT);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==REQUEST_CODE_PAYMENT)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirm!=null)
                {
                    try{
                        System.out.println(confirm.toJSONObject().toString(4));
                        System.out.println(confirm.getPayment().toJSONObject());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (resultCode== Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Payment is Canceled ", Toast.LENGTH_LONG).show();
            }
            else if (resultCode== PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Toast.makeText(this, "Error occoured ", Toast.LENGTH_LONG).show();
            }
        }

    }
}
