package ru.home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input the line to analyze and press enter");
        String inputStr = null;
        try {
            inputStr = reader.readLine();

            Map<Character, Integer> map = new HashMap<>();
            int maxCount = 0;

            for (int i = 0; i < inputStr.length(); i++)
                if (!map.containsKey(inputStr.charAt(i)))
                    map.put(inputStr.charAt(i), 1);
                else {
                    int oldValue = map.get(inputStr.charAt(i));
                    int newValue = ++oldValue;
                    if (newValue > maxCount)
                        maxCount = newValue;
                    map.replace(inputStr.charAt(i), newValue);
                }

            int maxIndex = 0;
            for (Character key : map.keySet())
                if (map.get(key) == maxCount)
                    if (inputStr.lastIndexOf(key) > maxIndex)
                        maxIndex = inputStr.lastIndexOf(key);

            System.out.printf("%s %d",inputStr.charAt(maxIndex), maxCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
