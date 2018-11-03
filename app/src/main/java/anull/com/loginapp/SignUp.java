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

public class SignUp extends AppCompatActivity {

    EditText signUP_email , SignUp_pass ;
    Button SignUp_BTN ;

    FirebaseAuth mAuth ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth= FirebaseAuth.getInstance() ;
        signUP_email = (EditText)findViewById(R.id.Email_singUp);
        SignUp_pass = (EditText)findViewById(R.id.Password_singUp);
        SignUp_BTN = (Button)findViewById(R.id.sigup_btn);


        SignUp_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email , pass ;
                email =signUP_email.getText().toString();
                pass = SignUp_pass.getText().toString();

                if( ! TextUtils.isEmpty(email) && ! TextUtils.isEmpty(pass) ){

                    // reg process ta start korvo

                    mAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Intent i = new Intent(getApplicationContext(), HomePage.class) ;
                                startActivity(i);
                                finish();




                            }

                            else {

                                String e = task.getException().getMessage() ;


                                Toast.makeText(getApplicationContext(), "ERROR:"+ e , Toast.LENGTH_SHORT  ).show();


                            }



                        }
                    }) ;






                }
                    else
                        {

                            Toast.makeText(getApplicationContext() , "PLzz Enter SOme Data " , Toast.LENGTH_SHORT).show();




                }






            }
        });










    }
}
