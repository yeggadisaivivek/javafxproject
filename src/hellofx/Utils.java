package hellofx;

import java.util.UUID;

public class Utils {
	public static String generateUniqueID() {
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
}
