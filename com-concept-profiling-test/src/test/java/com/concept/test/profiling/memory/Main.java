package com.concept.test.profiling.memory;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Main {
    public static void main (String[] args) {
        System.setProperty("http.agent", "Chrome");
        try {
            HttpURLConnection c = null;
            URL u = new URL("https://coderbyte.com/api/challenges/json/age-counting");
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String json = sb.toString();
            String subJson = json.substring(json.indexOf("\"data\"")).replace("\"data\":\"","").replace("\"}","");
            long numberOfAgeGtFifty = Arrays.stream(subJson.split(","))
                    .map(string -> string.trim())
                    .filter(string -> string.startsWith("age="))
                    .map(string -> string.replace("age=", ""))
                    .mapToInt(string -> Integer.parseInt(string))
                    .filter(age -> age >= 50)
                    .count();
            System.out.println(numberOfAgeGtFifty);

        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }
}