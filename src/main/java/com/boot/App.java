package com.boot;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String s="Hello baby Hello baby";

        String[] words=s.split(" ");

        HashSet<String> hs=new HashSet<>();

        for(String word:words){
            hs.add(word);
        }

        System.out.println(hs);

        String result = String.join(" ", hs);

        System.out.println(result);
    }
}
