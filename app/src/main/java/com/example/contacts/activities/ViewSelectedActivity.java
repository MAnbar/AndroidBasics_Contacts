package com.example.contacts.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.contacts.R;
import com.example.contacts.adapters.ContactAdapter;
import com.example.contacts.models.Contact;

import java.util.ArrayList;

public class ViewSelectedActivity extends AppCompatActivity {

    private ArrayList<Contact> contactList;
    static private RecyclerView recyclerView;
    static private ContactAdapter contactAdapter;

    private void initializeAdapter() {
        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(this, contactList, recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    public void initializeData() {
        contactList.clear();
        contactList.addAll(MainActivity.selectedContactList);
        contactAdapter.setContactsList(contactList);
        contactAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected);

        initializeAdapter();
        initializeData();
    }
}
