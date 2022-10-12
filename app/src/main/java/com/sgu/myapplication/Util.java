package com.sgu.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Util {

    public static ArrayList<String> getJSONkeys(JSONObject x){
        try{

            Iterator<String> keys = x.keys();
            ArrayList<String> list = new ArrayList<String>();

            do {
                String newKEY = keys.next().toString();
                list.add(newKEY);

            }while (keys.hasNext());
            ;
            return list;
        }
        catch(Error e){

        }

    return null;
    }
}
