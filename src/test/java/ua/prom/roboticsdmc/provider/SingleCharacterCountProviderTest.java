package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SingleCharacterCountProviderTest {

    SingleCharacterCountProvider singleCharacterCountProvider = new SingleCharacterCountProvider();

    @Test
    void countCharacters_shouldReturnOneSymbolAndNumberOfSymbols_whenInputIsManyTheSameSymbols() {

        String text = "aaaaaa";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('a', 6);

        assertEquals(expected, singleCharacterCountProvider.countCharacters(text));
    }

    @Test
    void countCharacters_shouldReturnOneSymbolAndNumberOne_whenInputIsOnlyOneSymbol() {

        String text = "b";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('b', 1);

        assertEquals(expected, singleCharacterCountProvider.countCharacters(text));
    }

    @Test
    void countCharacters_shouldReturnCountingAllCharactersSeparately_whenInputIsCopleOfWordsWithSymbols() {

        String text = "hello world!";
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        assertEquals(expected, singleCharacterCountProvider.countCharacters(text));
    }
}
