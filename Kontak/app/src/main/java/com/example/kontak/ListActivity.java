package com.example.kontak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements CustomAdapter.CustomAdapterListener{

    RecyclerView listRecyclerView;
    ArrayList<KontakModel> items = new ArrayList<>();
    CustomAdapter customAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listRecyclerView = findViewById(R.id.listRecyclerView);
        customAdapter = new CustomAdapter(this, items);
        listRecyclerView.setAdapter(customAdapter);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.listFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });

        readData();
    }

    void readData(){
        db.collection("kontak").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {

                if (task.getResult() != null) {
                    items.clear();
                    for(QueryDocumentSnapshot document : (task.getResult())) {
                        KontakModel kontakModel = new KontakModel(document.getId(),
                                document.getString("nama"), document.getString("notelp"));
                        items.add(kontakModel);
                    }
                }
                customAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(ListActivity.this, "Gagal Membaca Data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void removeDataFromCustomAdapter(int position) {
        KontakModel kontakModel = items.get(position);

        db.collection("kontak").document(kontakModel.getId()).delete()
                .addOnSuccessListener(unused -> readData())

                .addOnFailureListener(e -> Toast.makeText(ListActivity.this, "Gagal Menghapus Data",
                        Toast.LENGTH_LONG).show());
    }

}