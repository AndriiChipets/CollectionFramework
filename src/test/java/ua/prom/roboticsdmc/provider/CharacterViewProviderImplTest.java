package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CharacterViewProviderImplTest {

    CharacterViewProviderImpl characterViewProviderImpl = new CharacterViewProviderImpl();

    @Test
    void provideView_shouldReturnOneSymbolAndNumberOfSymbols_whenInputIsManyTheSameSymbols() {

        String text = "aaaaaa";
        Map<Character, Integer> charterToCountActual = new LinkedHashMap<>();
        charterToCountActual.put('a', 6);
        String expected = "aaaaaa\r\n" 
                        + "\"a\" - 6\r\n";

        assertEquals(expected, characterViewProviderImpl.provideView(text, charterToCountActual));
    }

    @Test
    void provideView_shouldReturnCountingAllCharactersSeparately_whenInputIsCopleOfWordsWithSymbols() {

        String text = "hello world!";
        Map<Character, Integer> charterToCountActual = new LinkedHashMap<>();
        charterToCountActual.put('h', 1);
        charterToCountActual.put('e', 1);
        charterToCountActual.put('l', 3);
        charterToCountActual.put('o', 2);
        charterToCountActual.put(' ', 1);
        charterToCountActual.put('w', 1);
        charterToCountActual.put('r', 1);
        charterToCountActual.put('d', 1);
        charterToCountActual.put('!', 1);
        String expected = "hello world!\r\n" 
                        + "\"h\" - 1\r\n" 
                        + "\"e\" - 1\r\n" 
                        + "\"l\" - 3\r\n" 
                        + "\"o\" - 2\r\n"
                        + "\" \" - 1\r\n" 
                        + "\"w\" - 1\r\n" 
                        + "\"r\" - 1\r\n" 
                        + "\"d\" - 1\r\n" 
                        + "\"!\" - 1\r\n";

        assertEquals(expected, characterViewProviderImpl.provideView(text, charterToCountActual));
    }
}
