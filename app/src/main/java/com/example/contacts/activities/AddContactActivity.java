package com.example.contacts.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.contacts.R;
import com.example.contacts.activities.MainActivity;
import com.example.contacts.handlers.InternalContactsHandler;
import com.example.contacts.models.Contact;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                TextInputLayout layContactName = (TextInputLayout) findViewById(R.id.nameInputLayout);
                TextInputLayout layPassword = (TextInputLayout) findViewById(R.id.numberInputLayout);

                EditText etName = (TextInputEditText) findViewById(R.id.nameText);
                EditText etPassword = (TextInputEditText) findViewById(R.id.numberText);

                String strName = etName.getText().toString();
                String strNumber = etPassword.getText().toString();

                layContactName.setError(null);
                layPassword.setError(null);

                if (TextUtils.isEmpty(strName)) {
                    layContactName.setError("Name can't be empty");
                    return;
                }
                if (TextUtils.isEmpty(strNumber)){
                    layPassword.setError("Number can't be empty");
                    return;
                }

                InternalContactsHandler.createContactFile(getBaseContext() , new Contact(strName,strNumber));
                MainActivity.AddContact(new Contact(strName,strNumber));
                finish();
            }
        });
    }
}
