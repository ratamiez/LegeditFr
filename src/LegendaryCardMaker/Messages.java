package LegendaryCardMaker;

import java.util.Locale;
import java.util.ResourceBundle;



public final class Messages {

	// Internationalization
	static Locale frLocale;
	static Locale currentLocale;
	static ResourceBundle messages;

	public static String getString(String key) {
		if (messages == null) {
			frLocale = new Locale("fr","FR");
			currentLocale = frLocale;
				messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		}
		String translation = "Introuvable";
		
		try {
			translation = messages.getString(key);
		} catch (Exception e) {
			System.out.println("========================> "+key+" introuvable");
		}

		return translation;
	}
	
}
