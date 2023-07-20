package com.example.contact_book;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(""+contactList.get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity,Detalils_Activity.class);
                intent.putExtra("name",contactList.get(position).getName());
                intent.putExtra("number",contactList.get(position).getNumber());
                intent.putExtra("email",contactList.get(position).getEmail());
                mainActivity.startActivity(intent);
            }
        });
        holder.Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(mainActivity,holder.Next);
                mainActivity.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.update)
                        {
                            Toast.makeText(mainActivity,"Update",Toast.LENGTH_SHORT).show();
                            Dialog dialog=new Dialog(mainActivity);
                            dialog.setContentView(R.layout.more_activity);
                            EditText txtname,txtNumber,txtemail;
                            Button btnupdate;
                            txtname=dialog.findViewById(R.id.txtname);
                            txtNumber=dialog.findViewById(R.id.txtnumber);
                            txtemail=dialog.findViewById(R.id.txtemail);
                            btnupdate=dialog.findViewById(R.id.btnupdate);
                            txtname.setText(""+contactList.get(position).getName());
                            txtemail.setText(""+contactList.get(position).getEmail());
                            txtNumber.setText(""+contactList.get(position).getNumber());
                            btnupdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MydataBase mydataBase=new MydataBase(mainActivity.getApplicationContext());
                                    mydataBase.updateData(contactList.get(position).getId(),contactList.get(position).getName(),contactList.get(position).getNumber(),contactList.get(position).getEmail());
                                    String name=txtname.getText().toString();
                                    String email=txtemail.getText().toString();
                                    txtname.setText(""+name);
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                        if(item.getItemId()==R.id.delete)
                        {
                            Toast.makeText(mainActivity,"Delete",Toast.LENGTH_SHORT).show();
                            MydataBase mydataBase=new MydataBase(mainActivity);
                            mydataBase.deleteData(contactList.get(position).getId());
                            contactList.remove(position).getId();
                            contactList.remove(position).getName();
                            contactList.remove(position).getNumber();
                            contactList.remove(position).getEmail();
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton Next;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nametxt);
            Next=itemView.findViewById(R.id.next);

        }
    }
}
