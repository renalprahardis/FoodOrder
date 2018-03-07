package id.project.renal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.project.renal.Common.Common;
import id.project.renal.Model.User;

public class MainActivity extends AppCompatActivity {

    Button btnSignin, btnSignup;
//    EditText edit_nama, edit_email, edit_password, edit_nomor;
    EditText editPhone, editPassword;
//    ProgressDialog progressDialog;

//    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignin = (Button)findViewById(R.id.btn_log);
        btnSignup = (Button)findViewById(R.id.btn_sign);
        editPhone = (EditText) findViewById(R.id.log_phone);
        editPassword = (EditText) findViewById(R.id.log_pass);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){
                        mDialog.dismiss();
                        User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                        user.setPhone(editPhone.getText().toString());
                        if(user.getPassword().equals(editPassword.getText().toString())){
                           Intent homeIntent = new Intent(MainActivity.this,Home.class);
                            Common.currentUser = user;
                            startActivity(homeIntent);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login failed !!", Toast.LENGTH_SHORT).show();
                        }
                        }else {
                            Toast.makeText(MainActivity.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);

            }
        });
    }
}





//        firebaseAuth = FirebaseAuth.getInstance();
//
//        progressDialog = new ProgressDialog(this);
//
//        edit_nama = (EditText)MenuList(R.id.sign_nama);
//        edit_email = (EditText)MenuList(R.id.sign_email);
//        edit_password = (EditText)MenuList(R.id.sign_pass);
//        edit_nomor = (EditText)MenuList(R.id.sign_num);
//        buttons = (Button) MenuList(R.id.btn_signfix);
//        buttons.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                registerUser();
//
//            }
//        });


//    private void registerUser(){
//        String email = edit_email.getText().toString().trim();
//        String nama = edit_nama.getText().toString().trim();
//        String password = edit_password.getText().toString().trim();
//        String nomer = edit_nomor.getText().toString().trim();
//
//
//        if(TextUtils.isEmpty(nama)){
//            Toast.makeText(this,"Please enter name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if(TextUtils.isEmpty(email)){
//            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if(TextUtils.isEmpty(password)){
//            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(TextUtils.isEmpty(nomer)){
//            Toast.makeText(this,"Please enter phone", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressDialog.setMessage("Registering User...");
//        progressDialog.show();
//
//        firebaseAuth.createUserWithEmailAndPassword(email,password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(MainActivity.this, Login.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(MainActivity.this, "Could not register.. please try again", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//
//
//    }



