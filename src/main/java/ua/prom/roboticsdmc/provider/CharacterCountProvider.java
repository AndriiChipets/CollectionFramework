package ua.prom.roboticsdmc.provider;

import java.util.Map;

public interface CharacterCountProvider {

    Map<Character, Integer> countCharacters(String text);
}
