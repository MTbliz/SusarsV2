package com.susar.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.JsonException;
import java.util.List;

public class CountriesView {
    public String countriesToJson(List<String> countries) {
        JSONArray countriesJson = new JSONArray();
        try {
            for (String country : countries) {
                JSONObject list1 = new JSONObject();
                list1.put("country", country);
                countriesJson.add(list1);
            }
        } catch (
                JsonException e1) {
            e1.printStackTrace();
        }
        return countriesJson.toJSONString();
    }
}

