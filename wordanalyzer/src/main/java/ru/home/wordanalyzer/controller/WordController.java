package ru.home.wordanalyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.wordanalyzer.entity.WordEntity;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wordanalyzer")
public class WordController {

    @GetMapping(value = "/analyze", produces = "application/json")
    public WordEntity analyzeWord(@PathParam("word") String word) {
        Map<Character, Integer> map = new HashMap<>();
        int maxCount = 0;

        for (int i = 0; i < word.length(); i++)
            if (!map.containsKey(word.charAt(i)))
                map.put(word.charAt(i), 1);
            else {
                int oldValue = map.get(word.charAt(i));
                int newValue = ++oldValue;
                if (newValue > maxCount)
                    maxCount = newValue;
                map.replace(word.charAt(i), newValue);
            }

        int maxIndex = 0;
        for (Character key : map.keySet())
            if (map.get(key) == maxCount)
                if (word.lastIndexOf(key) > maxIndex)
                    maxIndex = word.lastIndexOf(key);

        WordEntity wordEntity=new WordEntity(word.charAt(maxIndex), maxCount);
        return wordEntity;
    }

}
