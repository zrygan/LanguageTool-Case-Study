package Filipino_Language;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Rule that detects the words prefixed with "kumb" or "kump" and suggests the correct spelling.
 * @author Zhean Ganituen (zrygan)
 */
public class Kump_KumbRule extends Rule {
    @Override
    public String getId() {
        return "KUMP_KUMB_RULE";
    }

    @Override
    public String getDescription() {
        return "A rule that detects the words prefixed with \"kumb\" or \"kump\" and suggests the correct spelling.";
    }

    @Override
    public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
        List<RuleMatch> ruleMatches = new ArrayList<>();
        AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();

        for (AnalyzedTokenReadings token : tokens) {
            Recognizer recognizer = new Recognizer(token.getToken());
            int res;
            try {
                res = recognizer.recognizeWord();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        
            if(res != -1){
                RuleMatch ruleMatch = new RuleMatch(
                    this,
                    sentence,
                    token.getStartPos(),
                    token.getEndPos(),
                    "Kumb- and Kump- prefix misuse."
                );
            }
        }
        return ruleMatches.toArray(new RuleMatch[0]);
    }
}