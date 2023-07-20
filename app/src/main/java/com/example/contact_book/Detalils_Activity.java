package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contact_book.databinding.ActivityDetalilsBinding;

import java.util.ArrayList;

public class Detalils_Activity extends AppCompatActivity {
    ActivityDetalilsBinding binding;
    private int position;
    ArrayList<ContactModel> contactList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalils);
        binding = ActivityDetalilsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        position = getIntent().getIntExtra("position", 0);
        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");
        binding.name.setText(""+name);
        binding.number.setText(""+number);
        binding.email.setText(""+email);

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detalils_Activity.this,NewContactAdd.class);
                startActivity(intent);
            }
        });
        binding.beck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detalils_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}