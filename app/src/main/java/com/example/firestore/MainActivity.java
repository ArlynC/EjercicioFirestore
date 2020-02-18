package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText nombre,precio;
    Button boton;

    FirebaseFirestore mfirestore;

    RecyclerView recycler;
    MiAdaptador adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfirestore=FirebaseFirestore.getInstance();//acceso a todas las colecciones

        recycler=findViewById(R.id.recycler_view);
        nombre=findViewById(R.id.etName);
        precio=findViewById(R.id.etPrice);
        boton=findViewById(R.id.btnSave);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
                Toast.makeText(MainActivity.this,"Producto registrado",Toast.LENGTH_SHORT).show();
                nombre.setText("");
                precio.setText("");
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        Query query=mfirestore.collection("PRODUCTOS");

        FirestoreRecyclerOptions<Producto> f=new FirestoreRecyclerOptions.Builder<Producto>().setQuery(query,Producto.class).build();
        adapter=new MiAdaptador(f);
        adapter.notifyDataSetChanged();
        recycler.setAdapter(adapter);
    }
    private void crear(){
        String no=nombre.getText().toString();
        String pr=precio.getText().toString();
        Map<String,Object> map=new HashMap<>();
        map.put("nombre",no);
        map.put("precio",pr);
        //mfirestore.collection("PRODUCTOS").document().set(map);
        mfirestore.collection("PRODUCTOS").add(map);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
