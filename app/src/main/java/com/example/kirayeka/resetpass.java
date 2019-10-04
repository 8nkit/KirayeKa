package com.example.kirayeka;
import com.example.kirayeka.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import static com.example.kirayeka.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class resetpass extends AppCompatActivity {
private FirebaseDatabase mDatabase;
Button btndone;
private  DatabaseReference mref;
    MaterialEditText edtpass0,edtpass01;
    private String value,fi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getString("key");
        }
         fi="/"+value+"/password";
        mDatabase = FirebaseDatabase.getInstance();
        mref = mDatabase.getReference("User");
        edtpass0 = findViewById(id.edtpass00);
        edtpass01 = findViewById(id.edtpass01);
        btndone=findViewById(id.btndone);
            btndone.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                        if (edtpass0.getText().toString().equals(edtpass01.getText().toString())) {
                            Map<String, Object> updatedValues = new HashMap<>();
                            updatedValues.put(fi, edtpass01.getText().toString());
                            mref.updateChildren(updatedValues);
                            Intent restpse = new Intent(resetpass.this, SignIn.class);
                            startActivity(restpse);
                            System.exit(1);
                        }
                        else {
                            Toast.makeText(resetpass.this, "Password not matched", Toast.LENGTH_SHORT).show();
                        }
    }

    });
}
}
