package Filipino_Language;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.Languages;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

/**
 * Java class that checks if a specific language recognizes a word.
 * Used in Kump_KumbRule.java to check if the word has Spanish-origin.
 * 
 * @author Zhean Ganituen (zrygan)
 */
public class Recognizer {
    private String word;

    public Recognizer(String word) {
        this.word = word;
    }

    /**
     * Recognizes if a Filipino word with the prefix "kump" or "kumb" has a Spanish
     * equivalent prefixed with "conf" or "conv" respectively.
     *
     * @return 1 if the word has a Spanish equivalent with the expected prefix,
     *         -1 otherwise.
     * 
     * @author Zhean Ganituen (zrygan)
     */
    public int recognizeWord() throws IOException{
        // Initialize LanguageTool for Spanish
        Language spanish = Languages.getLanguageForShortCode("es");
        JLanguageTool langTool = new JLanguageTool(spanish);

        // Check if the word is recognized in Spanish
        List<RuleMatch> matches = langTool.check(word);

        if (!matches.isEmpty()) {
            // If there are matches, the word might be incorrect in Spanish
            for (RuleMatch match : matches) {
                List<String> suggestions = match.getSuggestedReplacements();
                for (String suggestion : suggestions) {
                    if (word.startsWith("kump") && suggestion.startsWith("conf")) {
                        return 1;
                    } else if (word.startsWith("kumb") && suggestion.startsWith("conv")) {
                        return 1;
                    }
                }
            }
        } else {
            // If there are no matches, the word is recognized in Spanish
            if (word.startsWith("kump") && word.replaceFirst("kump", "conf").equals(word)) {
                return 1;
            } else if (word.startsWith("kumb") && word.replaceFirst("kumb", "conv").equals(word)) {
                return 1;
            }
        }
        return -1;
    }
}
