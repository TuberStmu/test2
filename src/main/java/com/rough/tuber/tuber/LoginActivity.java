package com.rough.tuber.tuber;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText etFirstname, etLastname, etUsername, etPassword, etEmail, etMajor, etClassification, etStudentID, etGender, etPhonenumber, etDOB;
    Button bRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bStudent = (Button) findViewById(R.id.bStudent);
        final Button bTutor = (Button) findViewById(R.id.bTutor);

        bStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentRegisterIntent = new Intent(LoginActivity.this, StudentRegister.class);
                LoginActivity.this.startActivity(StudentRegisterIntent);

            }
        });
        bTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TutorRegisterIntent = new Intent(LoginActivity.this, TutorRegister.class);
                LoginActivity.this.startActivity(TutorRegisterIntent);

            }
        });


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {


                signIn(etEmail.getText().toString(), etPassword.getText().toString());


            }
        });


    }


    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = mAuth.getCurrentUser();


                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("info/" + user.getUid());

                            ValueEventListener postListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {


                                    info DBuser = dataSnapshot.getValue(info.class);

                                    String type = DBuser.getEtType();


                                    //Redirect the user based on type
                                    Toast.makeText(LoginActivity.this, "You are a " + type,
                                            Toast.LENGTH_LONG).show();

                                    if (!(type == "Student")){
                                        Intent HomePageIntent = new Intent(LoginActivity.this, TutorHome.class);
                                        LoginActivity.this.startActivity(HomePageIntent);
                                    }
                                    else if (!(type == "Tutor")){
                                        Intent HomePage1Intent = new Intent(LoginActivity.this, StudentHome.class);
                                        LoginActivity.this.startActivity(HomePage1Intent);
                                    }
                                    else{

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(LoginActivity.this, databaseError.toException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            };


                            ref.addValueEventListener(postListener);


                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }


                        hideProgressDialog();
                    }
                });
    }


    private boolean validateForm() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }


}
