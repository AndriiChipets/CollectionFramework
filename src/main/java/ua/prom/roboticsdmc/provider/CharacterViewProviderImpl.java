package ua.prom.roboticsdmc.provider;

import java.util.Map;

public class CharacterViewProviderImpl implements CharacterViewProvider {

    @Override
    public String provideView(String text, Map<Character, Integer> charterToCount) {
        StringBuilder viewResultStringBuilder = new StringBuilder();
        viewResultStringBuilder.append(text + "\r\n");
        charterToCount.forEach((a, b) -> viewResultStringBuilder.append("\"" + a + "\"" + " - " + b + "\r\n"));
        
        return String.valueOf(viewResultStringBuilder);
    }
}
