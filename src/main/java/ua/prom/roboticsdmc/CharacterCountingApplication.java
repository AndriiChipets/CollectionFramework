package ua.prom.roboticsdmc;

import ua.prom.roboticsdmc.cache.CacheProvider;
import ua.prom.roboticsdmc.cache.CacheProviderImpl;
import ua.prom.roboticsdmc.provider.CharacterCountProvider;
import ua.prom.roboticsdmc.provider.CharacterStaticsProvider;
import ua.prom.roboticsdmc.provider.CharacterViewProvider;
import ua.prom.roboticsdmc.provider.CharacterViewProviderImpl;
import ua.prom.roboticsdmc.provider.CountProviderWithCache;
import ua.prom.roboticsdmc.provider.SingleCharacterCountProvider;
import ua.prom.roboticsdmc.validator.Validator;
import ua.prom.roboticsdmc.validator.ValidatorImpl;

import java.util.Map;
import java.util.Scanner;

public class CharacterCountingApplication {

    public static void main(String[] args) {

        String stopWord = "-";
        int cacheCapacity = 4;

        Validator validator = new ValidatorImpl();
        CacheProvider<String, Map<Character, Integer>> cacheProvider = new CacheProviderImpl<>(cacheCapacity);
        CharacterCountProvider singleCharacterCountProvider = new SingleCharacterCountProvider();
        CharacterCountProvider countProviderWithCache = new CountProviderWithCache(cacheProvider,
                singleCharacterCountProvider);
        CharacterViewProvider characterViewProvider = new CharacterViewProviderImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            CharacterStaticsProvider characterStaticsProvider = new CharacterStaticsProvider(validator,
                    countProviderWithCache, characterViewProvider);

            System.out.print("Input text: ");

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text.equals(stopWord)) {
                    break;
                }
                String result = characterStaticsProvider.provideStatics(text);
                System.out.println();
                System.out.println("Result:");
                System.out.println();
                System.out.println(result);
                System.out.println("If you want to stop program, please enter " + "\"" + stopWord + "\"");
                System.out.println();
                System.out.print("Input next text: ");
            }
        }
        System.out.println("The program is stopped");
    }
}
