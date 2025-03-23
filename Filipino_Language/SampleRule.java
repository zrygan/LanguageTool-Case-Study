package Filipino_Language;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleRule extends Rule {
    @Override
    public String getId() {
        return "TEST_RULE";
    }

    @Override
    public String getDescription() {
        return "A rule that detects the word 'test' and prints 'IT WORKS'";
    }

    @Override
    public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
        List<RuleMatch> ruleMatches = new ArrayList<>();
        AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();

        for (AnalyzedTokenReadings token : tokens) {
            if (token.getToken().equalsIgnoreCase("test")) {
                System.out.println("IT WORKS");

                RuleMatch ruleMatch = new RuleMatch(
                        this,
                        sentence,
                        token.getStartPos(),
                        token.getEndPos(),
                        "Avoid using 'test'. Consider rewording."
                );
                ruleMatch.setSuggestedReplacement("example");
                ruleMatches.add(ruleMatch);
            }
        }
        return toRuleMatchArray(ruleMatches);
    }
}
