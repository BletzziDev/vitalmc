package com.nasquicode.vitalcore.bukkit.licensing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nasquicode.vitalcore.bukkit.utils.Request;

public class LicenseServer {
    public static Boolean checkLicense(String license) {
        Request req = new Request("https://pastebin.com/UJDEdMHR");
        req.setContentType("application/json");
        req.setRequireSSL(false);
        if(req.execute()) {
            Request.RequestResponse rr = req.getResponse();
            if(rr.getResponseStatus() == Request.ResponseStatus.SUCCESS) {
                JsonObject json = new Gson().fromJson(rr.getData(), JsonObject.class);
                Boolean check = json.get("authorization").getAsBoolean();
                return check;
            }
            return false;
        }
        return false;
    }
}
