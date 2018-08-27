package com.example.contacts.handlers;

import com.example.contacts.models.Contact;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ApiHandler {

    public static ArrayList<Contact> getRates() {
        URL url;
        try {
            url = new URL("http://www.apilayer.net/api/live?access_key=13557f9e94479f74ae1b455adf1b62f4");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL");
        }

        Contact cRate;
        ArrayList<Contact> list = new ArrayList<>();
        System.out.println(url);
        String jsonString;
        try {
            jsonString = new Scanner(url.openStream()).useDelimiter("\\A").next();
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Api Not Found");
            cRate = new Contact("Api Not Found", "");
            list.add(cRate);
            ex.printStackTrace();

            return list;
        } catch (java.io.IOException ex) {
            //System.out.println("IO Error");
            cRate = new Contact("IO Error", "");
            list.add(cRate);
            ex.printStackTrace();

            return list;
        }

        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(jsonString);
        JsonObject quotesNode = (JsonObject) rootNode.getAsJsonObject().get("quotes");

        if (quotesNode == null) {
            System.out.println("Quotes Node is NULL Error");
            cRate = new Contact("Quotes Node is NULL", "");
            list.add(cRate);
            return list;
        }
        for (Map.Entry<String, JsonElement> obj : quotesNode.entrySet()) {
            cRate = new Contact(obj.getKey(), obj.getValue().toString());
            System.out.println(cRate);
            list.add(cRate);
        }
        return list;
    }

}

