package com.example.kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mainNama, mainNo;
    Button mainBuat;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String collectionName = "kontak";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNama = findViewById(R.id.EditTextNama);
        mainNo = findViewById(R.id.EditTextNo);
        mainBuat = findViewById(R.id.btnBuat);

        mainBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                startActivity(new Intent(getApplicationContext(), ListActivity.class));
            }
        });


    }
        void addData () {
            String nama = mainNama.getText().toString();
            String notelp = mainNo.getText().toString();
            KontakModel kontakModel = new KontakModel(null, nama, notelp);

            db.collection(collectionName).add(kontakModel)
                    .addOnSuccessListener(documentReference -> Toast.makeText(this, "Berhasil Menyimpan Data", Toast.LENGTH_LONG).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Gagal Menyimpan Data", Toast.LENGTH_LONG).show());
        }
    }
