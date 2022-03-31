package com.example.testapi;

import java.util.ArrayList;

public class ModelClass {

    /*public ArrayList<ModelClass.data> getData;*/
    String page;
    String per_page;
    String total;
    String total_pages;

    public ArrayList<data>data;

    class data {

        String id;
        String email;
        String first_name;
        String last_name;
        String avater;


    }
}
