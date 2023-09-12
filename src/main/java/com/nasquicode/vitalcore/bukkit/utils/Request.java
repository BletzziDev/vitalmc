package com.nasquicode.vitalcore.bukkit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Request {

    static HttpURLConnection requestConnection;
    static RequestResponse response;
    static String contentType;
    static Exception exception;
    static long requestTime;
    static boolean requireSSL = true;

    private final URL url;
    private int timeout;

    public Request(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + e);
        }
    }

    public Request(URL url) {
        this.url = url;
    }

    public Request(URL url, int timeoutInMillis) {
        this.url = url;
        this.timeout = timeoutInMillis;
    }

    public Exception getException() {
        return exception;
    }

    public void setContentType(String type) {
        contentType = type;
    }

    public void setRequireSSL(boolean b) {
        requireSSL = b;
    }

    public boolean execute() {
        try {
            final long now = System.currentTimeMillis();
            int timeout = this.timeout == 0 ? 5000 : this.timeout;

            requestConnection = (HttpURLConnection) this.url.openConnection();
            requestConnection.setRequestMethod("GET");
            requestConnection.setRequestProperty("requireSSL", String.valueOf(requireSSL));
            requestConnection.setConnectTimeout(timeout);

            if (contentType != null && !contentType.isEmpty()) {
                requestConnection.setRequestProperty("Content-Type", contentType);
            }

            requestConnection.connect();
            requestTime = (System.currentTimeMillis() - now);

            buildResponse();

            return true;
        } catch (RuntimeException | IOException e) {
            exception = e;
            return false;
        }
    }

    private void buildResponse() throws IOException {
        int responseCode = requestConnection.getResponseCode();
        long responseTime = requestTime;
        String headers = requestConnection.getHeaderFields().toString();

        BufferedReader in = new BufferedReader(new InputStreamReader(requestConnection.getInputStream()));
        String inputLine;
        StringBuilder raw = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            raw.append(inputLine);
        }

        in.close();

        String data = raw.toString();

        response = new RequestResponse(data, responseCode, responseTime, headers, ResponseStatus.get(responseCode));
    }

    public RequestResponse getResponse() {
        return response;
    }

    public static enum ResponseStatus {
        SUCCESS,
        ACCEPTED,
        MOVED_PERMANENTLY,
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        NOT_IMPLEMENTED,
        SERVICE_UNAVAILABLE,
        GATEWAY_TIMEOUT,
        UNKNOWN;

        public static ResponseStatus get(int code) {
            switch (code) {
                case 200:
                    return SUCCESS;
                case 202:
                    return ACCEPTED;
                case 301:
                    return MOVED_PERMANENTLY;
                case 400:
                    return BAD_REQUEST;
                case 401:
                    return UNAUTHORIZED;
                case 403:
                    return FORBIDDEN;
                case 404:
                    return NOT_FOUND;
                case 500:
                    return INTERNAL_SERVER_ERROR;
                case 501:
                    return NOT_IMPLEMENTED;
                case 503:
                    return SERVICE_UNAVAILABLE;
                case 504:
                    return GATEWAY_TIMEOUT;
                default:
                    return UNKNOWN;
            }
        }
    }

    public static class RequestResponse {

        private final String data;
        private final Integer statusCode;
        private final Long time;
        private final String headers;
        private final ResponseStatus responseStatus;

        public RequestResponse(String data, Integer statusCode, Long time, String headers, ResponseStatus responseStatus) {
            this.data = data;
            this.statusCode = statusCode;
            this.time = time;
            this.headers = headers;
            this.responseStatus = responseStatus;
        }

        public String getData() {
            return data;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public Long getResponseTime() {
            return time;
        }

        public String getHeaders() {
            return headers;
        }

        public ResponseStatus getResponseStatus() {
            return responseStatus;
        }
    }

}
