package com.example.kirayeka;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kirayeka.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.rengwuxian.materialedittext.MaterialEditText;

public class forgotpass extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] country = {"Choose security question", "What is your father's middle name?", "What is the name of your first school?", "What is your favorite color?"};
    MaterialEditText edtans, edtPhone, edtName;
    Button resetpas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
        spin.setPrompt("Choose Security question");
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        edtPhone = findViewById(R.id.edtphone12);
        edtName = findViewById(R.id.edtnamee);
        edtans = findViewById(R.id.edtforgotans1);
        resetpas = findViewById(R.id.btnforgotpas1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        resetpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(forgotpass.this);
                mDialog.setMessage("Please Wating...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getEmaill().equals(edtans.getText().toString())) {
                                Intent restpss = new Intent(forgotpass.this, resetpass.class);
                                restpss.putExtra("key",edtPhone.getText().toString());
                                startActivity(restpss);

                            }
                            else {
                                Toast.makeText(forgotpass.this, "Wrong information", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(forgotpass.this, "Phone not registered", Toast.LENGTH_SHORT).show();
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}