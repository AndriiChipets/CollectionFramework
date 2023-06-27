package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.prom.roboticsdmc.cache.CacheProvider;

@ExtendWith(MockitoExtension.class)
class CountProviderWithCacheTest {

    @Mock
    CharacterCountProvider characterCountProvider;

    @Mock
    CacheProvider<String, Map<Character, Integer>> cacheProvider;

    @InjectMocks
    CountProviderWithCache сountProviderWithCache;

    @Test
    void countCharacters_shouldReturnDataFromCache_whenDataExistInCache() {

        String text = "hello world!";
        Map<Character, Integer> expected = cacheProvider.getValue(text);
        when(cacheProvider.isPresent(text)).thenReturn(true);

        assertEquals(expected, сountProviderWithCache.countCharacters(text));
    }

    @Test
    void countCharacters_shouldMakeCounting_whenDataDoNotExistInCache() {

        String text = "hello world!";
        Map<Character, Integer> expected = characterCountProvider.countCharacters(text);
        when(cacheProvider.isPresent(text)).thenReturn(false);

        assertEquals(expected, сountProviderWithCache.countCharacters(text));
    }
}
