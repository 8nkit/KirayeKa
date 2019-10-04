package com.example.kirayeka;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kirayeka.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import static com.example.kirayeka.R.*;
public class SignUp extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {
    String[] country = {"Choose security question", "What is your father's middle name?", "What is the name of your first school?", "What is your favorite color?"};
    MaterialEditText edtsqa, edtPhone, edtName, edtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_sign_up);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        spin.setPrompt("Choose Security question");
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        edtPhone = findViewById(id.edtph);
        edtName = findViewById(id.edtnamee);
        edtPassword = findViewById(id.psswd);
        edtsqa = findViewById(id.edtforgotans);
        btnSignUp = findViewById(id.btnsignup);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wating...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //get user information
                         //   Toast.makeText(SignUp.this, "User exists already !", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                        else {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString(), edtsqa.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successfully !", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}


