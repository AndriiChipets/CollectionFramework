package ua.prom.roboticsdmc.provider;

import java.util.Map;
import ua.prom.roboticsdmc.cache.CacheProvider;

public class CountProviderWithCache implements CharacterCountProvider {

    private final CacheProvider<String, Map<Character, Integer>> cacheProvider;
    private final CharacterCountProvider characterCountProvider;

    public CountProviderWithCache(CacheProvider<String, Map<Character, Integer>> cacheProvider,
            CharacterCountProvider characterCountProvider) {
        this.cacheProvider = cacheProvider;
        this.characterCountProvider = characterCountProvider;
    }

    @Override
    public Map<Character, Integer> countCharacters(String text) {
        final Map<Character, Integer> symbolToCount;
        if (cacheProvider.isPresent(text)) {
            symbolToCount = cacheProvider.getValue(text);
        } else {
            symbolToCount = characterCountProvider.countCharacters(text);
            cacheProvider.put(text, symbolToCount);
        }
        return symbolToCount;
    }
}
