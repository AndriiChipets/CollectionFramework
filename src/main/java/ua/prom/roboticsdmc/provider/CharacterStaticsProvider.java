package ua.prom.roboticsdmc.provider;

import java.util.Map;
import ua.prom.roboticsdmc.validator.Validator;

public class CharacterStaticsProvider {

    private final Validator validator;
    private final CharacterCountProvider characterCountProvider;
    private final CharacterViewProvider characterViewProvider;

    public CharacterStaticsProvider(Validator validator, CharacterCountProvider characterCountProvider,
            CharacterViewProvider characterViewProvider) {
        this.validator = validator;
        this.characterCountProvider = characterCountProvider;
        this.characterViewProvider = characterViewProvider;
    }

    public String provideStatics(String text) {
        validator.validate(text);
        final Map<Character, Integer> characterToCount = characterCountProvider.countCharacters(text);

        return characterViewProvider.provideView(text, characterToCount);
    }
}
