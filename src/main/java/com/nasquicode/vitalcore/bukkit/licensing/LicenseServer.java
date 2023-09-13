package com.nasquicode.vitalcore.bukkit.licensing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nasquicode.vitalcore.bukkit.utils.Console;
import com.nasquicode.vitalcore.bukkit.utils.Request;

public class LicenseServer {
    public static Boolean checkLicense(String license) {
        Request req = new Request("https://apivitalmc.nasquicode.com/checklicense.php?token="+license); // Valid
        req.setContentType("application/json");
        req.setRequireSSL(false);
        try {
            if (req.execute()) {
                Request.RequestResponse rr = req.getResponse();
                if (rr.getResponseStatus() == Request.ResponseStatus.SUCCESS) {
                    Console.log(rr.getData());
                    JsonObject json = new Gson().fromJson(rr.getData(), JsonObject.class);
                    Boolean check = json.get("authorization").getAsString().equalsIgnoreCase("true");
                    return check;
                }
                return false;
            }
            return false;
        }catch(Exception x) {
            return false;
        }
    }
}