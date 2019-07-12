package com.cron.service.Utils;

import org.json.JSONObject;
import org.jsoup.Connection.Response;

import java.net.HttpURLConnection;

public class ResponseUtils {

    public static boolean requestFailed(Response response, String path) {
        boolean isFailed = false;
        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            System.err.println("Cannot to " + path + " return http code: " + response.statusCode());
            isFailed = true;
        }
        JSONObject jsonObject = new JSONObject(response.body());

        if (jsonObject.getInt("err") != 0) {
            System.err.println("Connect to " + path + " return error_code:" + jsonObject.getInt("err"));
            isFailed = true;
        }
        return isFailed;
    }
}
