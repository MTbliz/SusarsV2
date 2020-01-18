package com.susar.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.json.JsonException;
import java.util.List;

public class TypesView {
    public String typesToJson(List<String> types) {
        JSONArray typesJson = new JSONArray();
        try {
            for (String type : types) {
                JSONObject list1 = new JSONObject();
                list1.put("type", type);
                typesJson.add(list1);
            }
        } catch (
                JsonException e1) {
            e1.printStackTrace();
        }
        return typesJson.toJSONString();
    }
}
