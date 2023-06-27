package ua.prom.roboticsdmc.provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SingleCharacterCountProvider implements CharacterCountProvider {

    @Override
    public Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> carachterToCount = new LinkedHashMap<>();
        IntStream streamOfChar = text.chars();
        streamOfChar.forEach(c -> carachterToCount.merge((char) c, 1, (a, b) -> b + carachterToCount.get((char) c)));
        return carachterToCount;
    }
}
