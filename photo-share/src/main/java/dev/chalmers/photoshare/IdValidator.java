package dev.chalmers.photoshare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidator {
    private final Pattern uuidPattern = Pattern.compile("^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$");

    public boolean isValidateUuid(String photoId) {
        try {
            Matcher matcher = uuidPattern.matcher(photoId);

            return matcher.find();
        } catch (Exception ex) {
            return false;
        }
    }
}
