package anull.com.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class upload_data extends AppCompatActivity {

    String title , desc  ;
    Button upld_btn ;
    EditText title_tv , desc_tv ;
    DatabaseReference mDatabase ;
    FirebaseAuth mauth ;
    String userId ;
    String time ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);

        //getting the userID from firebase
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getInstance().getCurrentUser() ;

               userId = user.getUid() ;


               //definfing the DATABASE PATH
            mDatabase = FirebaseDatabase.getInstance().getReference("data");



        //addding the views and buttons respectivly ;

        title_tv = (EditText)findViewById(R.id.editText_title) ;
        desc_tv = (EditText)findViewById(R.id.editText_details);

        upld_btn = (Button)findViewById(R.id.firebase_upload_btn);

            upld_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                                title = title_tv.getText().toString() ;
                                desc = desc_tv.getText().toString() ;

                        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc)){

                            //send to firebase
                                sentDataToFirebase();
                            Intent i = new Intent(getApplicationContext() , HomePage.class) ;
                            startActivity(i);

                        }
                        else
                            {

                                Toast.makeText(getApplicationContext() , "Enter Some Value " , Toast.LENGTH_SHORT).show();


                        }

                }
            });


    }

    public  void sentDataToFirebase(){

        Calendar calendar = Calendar.getInstance() ;
        Date date = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        time = dateFormat.format(date) ;


                            uploadHelper sentData = new uploadHelper(title , desc);
                            mDatabase.child(userId).child("upload").child(time).setValue(sentData);



    }



}
