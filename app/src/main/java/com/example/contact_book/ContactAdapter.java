package com.example.contact_book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
{
    MainActivity mainActivity;
    ArrayList<ContactModel> contactList;

//    ArrayList<Integer> idList;
//    ArrayList<String> nameList;
//    ArrayList<String> emailList;
//    ArrayList<String> numberList;

//    public ContactAdapter(MainActivity mainActivity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList, ArrayList<String> numberList) {
//        this.mainActivity = mainActivity;
//        this.idList = idList;
//        this.nameList = nameList;
//        this.emailList = emailList;
//        this.numberList = numberList;
//    }

    public ContactAdapter(MainActivity mainActivity, ArrayList<ContactModel> contactList) {
     this.mainActivity=mainActivity;
     this.contactList=contactList;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mainActivity).inflate(R.layout.contact_item,parent,false);
       ContactViewHolder holder=new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.textView.setText(""+contactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nametxt);
        }
    }
}
