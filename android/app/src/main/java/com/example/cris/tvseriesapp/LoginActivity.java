package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private static final String what = "Firebase Auth ";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button singInButton = (Button) findViewById(R.id.singInButton);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Log.d(what, "onAuthStateChanged: signed_in " + user.getUid());
                }
            }
        };
        singInButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!(emailAddress.getText().toString().equals("") || password.getText().toString().equals(""))){
                    singIn(emailAddress.getText().toString(), password.getText().toString());
                }else {
                    Toast.makeText(LoginActivity.this, "This fields should not be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void singIn(String emailAddress, String password) {
        firebaseAuth.signInWithEmailAndPassword(emailAddress,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(what, "signInWithEmailAndPassword: onComplete: " + task.isSuccessful());
                        if(!task.isSuccessful()){
                            Log.d(what, "signInWithEmailAndPassword: failed: " + task.getException());
                            Toast.makeText(LoginActivity.this, "The authentification has failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
