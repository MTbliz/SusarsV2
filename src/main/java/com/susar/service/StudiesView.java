package com.susar.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.JsonException;
import java.util.List;

public class StudiesView {
    public String stuidesToJson(List<String> studies) {
        JSONArray studiesJson = new JSONArray();
        try {
            for (String study : studies) {
                JSONObject list1 = new JSONObject();
                list1.put("study", study);
                studiesJson.add(list1);
            }
        } catch (
                JsonException e1) {
            e1.printStackTrace();
        }
        return studiesJson.toJSONString();
    }
}

