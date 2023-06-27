package ua.prom.roboticsdmc.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Source is null");
        }
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Source is empty");
        }
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException("Source contain only spaces or (and) tabs");
        }
    }
}
