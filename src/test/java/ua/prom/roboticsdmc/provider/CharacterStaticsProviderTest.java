package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ua.prom.roboticsdmc.validator.Validator;

@ExtendWith(MockitoExtension.class)
class CharacterStaticsProviderTest {

    @Mock
    Validator validator;

    @Mock
    CharacterCountProvider characterCountProvider;

    @Mock
    CharacterViewProvider characterViewProvider;

    @InjectMocks
    CharacterStaticsProvider сharacterStaticsProvider;

    @Test
    void provideStatics_shouldReturnCountingAllCharactersSeparately_whenInputIsCopleOfWordsWithSymbols() {

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

        doNothing().when(validator).validate(text);
        when(characterCountProvider.countCharacters(text)).thenReturn(charterToCountActual);
        when(characterViewProvider.provideView(text, charterToCountActual)).thenReturn(expected);

        assertEquals(expected, сharacterStaticsProvider.provideStatics(text));

        InOrder inOrder = inOrder(validator, characterCountProvider, characterViewProvider);
        inOrder.verify(validator).validate(text);
        inOrder.verify(characterCountProvider).countCharacters(text);
        inOrder.verify(characterViewProvider).provideView(text, charterToCountActual);
    }

    @Test
    void provideStatics_shouldReturnIllegalArgumentException_whenInputStringIsNull() {
        doThrow(new IllegalArgumentException("Source is null"))
        .when(validator)
        .validate(null);
        assertThrows(IllegalArgumentException.class, () -> сharacterStaticsProvider.provideStatics(null));
    }

    @Test
    void provideStatics_shouldReturnIllegalArgumentException_whenInputStringIsEmpty() {
        doThrow(new IllegalArgumentException("Source is empty"))
        .when(validator)
        .validate("");
        assertThrows(IllegalArgumentException.class, () -> сharacterStaticsProvider.provideStatics(""));
    }

    @Test
    void provideStatics_shouldReturnIllegalArgumentException_whenInputStringContainsOnlyTabsOrSpaces() {
        doThrow(new IllegalArgumentException("Source is contain only spaces or (and) tabs"))
        .when(validator)
        .validate("     ");
        assertThrows(IllegalArgumentException.class, () -> сharacterStaticsProvider.provideStatics("     "));
    }
}
