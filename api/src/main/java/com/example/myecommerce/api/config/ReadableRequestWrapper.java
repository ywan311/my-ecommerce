package com.example.myecommerce.api.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class ReadableRequestWrapper extends HttpServletRequestWrapper {
    private byte[] rawData;
    private Map<String,String[]> paramMap = new HashMap<>();

    public ReadableRequestWrapper(HttpServletRequest request) {
        super(request);


    }
}
