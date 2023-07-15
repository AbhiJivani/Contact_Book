package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contact_book.databinding.ActivityNewContactAddBinding;

import java.util.ArrayList;

public class NewContactAdd extends AppCompatActivity {
    ActivityNewContactAddBinding binding;
    ArrayList<String> nameList=new ArrayList();
    ArrayList<String> numberList=new ArrayList();
    ArrayList<String> emailList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_add);
        binding=ActivityNewContactAddBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        MydataBase mydataBase=new MydataBase(NewContactAdd.this);
        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.addname.getText().toString();
                String email=binding.addemail.getText().toString();
                String number=binding.addnumber.getText().toString();
                mydataBase.add(name,email,number);
                binding.addname.setText("");
                binding.addemail.setText("");
                binding.addnumber.setText("");
                Intent intent=new Intent(NewContactAdd.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}