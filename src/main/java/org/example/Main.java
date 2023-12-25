package org.example;

import org.example.requests.RequestCats;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String urlCats = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

        RequestCats.Test(urlCats);

    }

}