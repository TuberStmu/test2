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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;

public class TutorRegister extends AppCompatActivity {
    EditText etFirstname, etLastname, etPassword, etEmail, etMajor, etTutorID,  etPhonenumber, etDOB;
    //    Spinner etGender;
    Button bContinue;
    String selectedGender="Gender";
    String selectedClassification="Classification";

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    info Info;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tutor_register);
        Gender();
        Classification();
        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("info");
        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
//        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMajor = (EditText) findViewById(R.id.etMajor);
    //    etClassification = (EditText) findViewById(R.id.etClassification);


        etTutorID = (EditText) findViewById(R.id.etTutorID);


//        Spinner etGender = (Spinner) findViewById(R.id.etGender);

        etPhonenumber = (EditText) findViewById(R.id.etPhonenumber);
        etDOB = (EditText) findViewById(R.id.etDOB);
        bContinue = (Button) findViewById(R.id.bContinue);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("info");
        Info = new info();


        bContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createAccount(etEmail.getText().toString(), etPassword.getText().toString());


            }
        });

    }

    private void getValues() {
        Info.setEtFirstname(etFirstname.getText().toString());
        Info.setEtLastname(etLastname.getText().toString());
        Info.setEtEmail(etEmail.getText().toString());
        Info.setEtMajor(etMajor.getText().toString());
        Info.setEtClassification(selectedClassification);
       Info.setEtTutorID(etTutorID.getText().toString());
        Info.setEtGender(selectedGender);
        Info.setEtPhonenumber(etPhonenumber.getText().toString());
        Info.setEtDOB(etDOB.getText().toString());
        Info.setEtType("Tutor");
    }


    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            //Store User id to database with other details
                            String userID = user.getUid().toString();

                            getValues();


                            databaseReference.child(userID).setValue(Info);

                            mAuth.signOut();

                            Intent myIntent = new Intent(TutorRegister.this, TutorRegister2.class);
                            myIntent.putExtra("key", "hi"); //Optional parameters
                            TutorRegister.this.startActivity(myIntent);


                            Toast.makeText(TutorRegister.this, "Registered Successfully",
                                    Toast.LENGTH_LONG).show();


                        } else {


                            // If sign in fails, display a Toast message to the user.
                            Toast.makeText(TutorRegister.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }

                        hideProgressDialog();
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


    private boolean validateForm() {
        boolean valid = true;

        final Spinner etGender = (Spinner) findViewById(R.id.etGender);

        String selectedGender = etGender.getSelectedItem().toString();

        if (TextUtils.isEmpty(selectedGender) || TextUtils.equals(selectedGender,"Gender")) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_LONG).show();
            valid = false;
        }

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




    private void Gender() {

        final Spinner etGender = (Spinner) findViewById(R.id.etGender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etGender.setAdapter(adapter);
        final String[] gen = getResources().getStringArray(R.array.gender);
        etGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                Toast.makeText(TutorRegister.this,"Selected",Toast.LENGTH_SHORT).show();
                selectedGender=etGender.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void Classification() {
        final Spinner etClassification = (Spinner) findViewById(R.id.etClassification);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classification, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etClassification.setAdapter(adapter);
        final String[] cla = getResources().getStringArray(R.array.classification);
        etClassification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                Toast.makeText(StudentRegister.this,"Selected",Toast.LENGTH_SHORT).show();
                selectedClassification=etClassification.getSelectedItem().toString();


            }


            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }


}





