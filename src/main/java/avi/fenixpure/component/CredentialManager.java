package avi.fenixpure.component;

import org.jetbrains.annotations.NotNull;
import java.util.Base64;

public class CredentialManager {
    // Retrieve and decode the password from the Constant class
    public static final String decodedPassword = decodePassword(Constant.password);
    // This method decodes the Base64 encoded password
    @NotNull
    public static String decodePassword(String encodedPassword) {
        // Decode the Base64 encoded string
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }
}


