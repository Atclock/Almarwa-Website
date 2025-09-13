package com.example.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public final class Sanitizers {
    private Sanitizers () {}

    public static String plain(String s) {
        if (s==null) return null;
        return Jsoup.clean(s, Safelist.none());
    }
}