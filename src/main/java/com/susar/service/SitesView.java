package com.susar.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.JsonException;
import java.util.List;

public class SitesView {
    public String sitesToJson(List<String> sites) {
        JSONArray sitesJson = new JSONArray();
        try {
            for (String site : sites) {
                JSONObject list1 = new JSONObject();
                list1.put("site", site);
                sitesJson.add(list1);
            }
        } catch (
                JsonException e1) {
            e1.printStackTrace();
        }
        return sitesJson.toJSONString();
    }
}
