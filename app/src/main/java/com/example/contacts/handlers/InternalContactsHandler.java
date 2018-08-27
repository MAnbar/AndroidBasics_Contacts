package com.example.contacts.handlers;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;

import com.example.contacts.models.Contact;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class InternalContactsHandler {

    @RequiresApi(api = Build.VERSION_CODES.N)
    static public File createContactFile(Context context, Contact contact) {

        String contactFileName = "contact_" + contact.getContactName() + "_" + contact.getContactNumber();
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File contactFile = null;
        try {
            contactFile = File.createTempFile(contactFileName, "_.txt", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactFile;
    }

    static public ArrayList<Contact> getInternalContacts(Context context) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        ArrayList<Contact> internalContacts = new ArrayList<>();
        File[] files;


        files = storageDir.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            String[] currentContact = fileName.split("_");
            if (currentContact[0].equals("contact")) {
                internalContacts.add(new Contact(currentContact[1],currentContact[2]));
            }
        }
        return internalContacts;
    }
}
