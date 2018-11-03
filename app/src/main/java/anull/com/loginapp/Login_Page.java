package anull.com.loginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity {
    EditText email , pass ;
    Button login , sigup ;
    FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
       // mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.email_signin);
        pass = (EditText)findViewById(R.id.pass_singin);
        login = (Button)findViewById(R.id.Login_btn);
        sigup = (Button)findViewById(R.id.SignUP_btn);


        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , SignUp.class) ;
                startActivity(i);
                finish();
            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sign_email , sign_pass ;
                sign_email = email.getText().toString();
                sign_pass = pass.getText().toString() ;

                if( !TextUtils.isEmpty(sign_email) && !TextUtils.isEmpty(sign_pass)   ){

                    //firabse a data pathabu

                    mAuth.signInWithEmailAndPassword(sign_email , sign_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){


                                Intent o = new Intent(getApplicationContext() ,HomePage.class ) ;
                                startActivity(o);
                                finish();




                            }
                            else {
                                String e = task.getException().getMessage() ;
                                Toast.makeText(getApplicationContext() , "ERROR "+e  , Toast.LENGTH_SHORT    ).show();



                            }





                        }
                    });













                }


                else{


                    Toast.makeText(getApplicationContext() , "Plzz ENter SOme Value "  , Toast.LENGTH_SHORT).show();



                }








            }
        });








    }

  // ALT + INS BUTTON
  // CTRL + O


    @Override
    protected void onStart() {
        super.onStart();

        //check if user is already signed in ;
        FirebaseUser currentUser = mAuth.getCurrentUser() ;

        if(currentUser != null){

            Intent homepage = new Intent( getApplicationContext() , HomePage.class) ;
            startActivity(homepage);
            finish();

        }




    }


}
