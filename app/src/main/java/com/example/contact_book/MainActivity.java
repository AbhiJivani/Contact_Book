package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.contact_book.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
//    ArrayList<Integer> idList=new ArrayList<>();
//    ArrayList<String> nameList=new ArrayList();
//    ArrayList<String> numberList=new ArrayList();
//    ArrayList<String> emailList=new ArrayList();
    ArrayList<ContactModel> contactList = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        MydataBase mydataBase = new MydataBase(MainActivity.this);
        showdata();

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactAdd.class);
                startActivity(intent);
            }
        });

    }
    private void showdata() {
        MydataBase mydataBase=new MydataBase(MainActivity.this);
        Cursor cursor=mydataBase.showdata();
        while(cursor.moveToNext())
        {
            int id=(cursor.getInt(0));
            String name=(cursor.getString(1));
            String number=cursor.getString(2);
            String email=(cursor.getString(3));
            ContactModel model=new ContactModel(id,name,number,email);
            contactList.add(model);
        }
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(manager);
        ContactAdapter adapter=new ContactAdapter(MainActivity.this,contactList);
        binding.recyclerview.setAdapter(adapter);
        Log.d("TTT", "showdata: name="+contactList);
    }
}