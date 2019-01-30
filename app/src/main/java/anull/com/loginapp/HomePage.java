package anull.com.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    Button signout , upload_btn  ;

    FirebaseAuth mauth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signout =(Button)findViewById(R.id.signout_btn);
        upload_btn = (Button)findViewById(R.id.upload_btn) ;


        mauth = FirebaseAuth.getInstance() ;


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , upload_data.class ) ;
                startActivity(i);


            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mauth.signOut();

                Intent o  = new Intent(getApplicationContext() , Login_Page.class) ;
                startActivity(o);
                finish();




            }
        });




    }
}
