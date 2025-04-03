package Filipino_Language;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.Language;

import java.io.IOException; 
import java.util.ArrayList;

/**
 * This rule checks for correct usage of the Filipino verb-nang-verb construction.
 * In Filipino grammar, when two identical verbs are separated by "nang", they form
 * a specific grammatical structure that indicates repeated or continuous action.
 * This rule verifies that verbs surrounding "nang" are the same when this structure is used.
 * 
 * @author Zhean Ganituen (zrygan)
 */
public class VerbNangVerbRule extends Rule {

    /**
     * Array of common Filipino verbs (Pandiwa) that this rule checks for in 
     * verb-nang-verb constructions.
     */
    private static final String[] VERBS = {
        "mangyari", "gumawa", "sumulat", "tumakbo", 
        "kumain", "maglaro", "magbasa"  
        // a Pandiwa in Filipino
    };

    /**
     * Constructs a new VerbNangVerbRule for the specified language.
     * 
     * @param language the Language object for Filipino
     */
    public VerbNangVerbRule(Language language) {

    }

    /**
     * Returns the unique identifier for this rule.
     * 
     * @return a string representing the rule's ID in the LanguageTool system
     */
    @Override
    public String getId() {
        return "VERB-NANG-VERB_RULE";
    }

    /**
     * Returns a human-readable description of what this rule checks for.
     * 
     * @return a string describing the grammatical rule being checked
     */
    @Override
    public String getDescription() {
        return "A rule that detects errors in the phrase structure VERB-Nang-VERB.";
    }

    /**
     * Analyzes a sentence to find instances where the verb-nang-verb structure is used incorrectly.
     * The rule checks sequences of tokens for the pattern [verb][nang][verb] and verifies
     * that both verbs are identical.
     * 
     * @param sentence the analyzed sentence to check for rule violations
     * @return an array of RuleMatch objects indicating where violations were found
     * @throws IOException if an error occurs during analysis
     */
    @Override
    public RuleMatch[] match(AnalyzedSentence sentence) 
        throws IOException {
        ArrayList<RuleMatch> ruleMatches = new ArrayList<>();
        AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();

        for (int i = 1; i < tokens.length - 1; i++) {
            AnalyzedTokenReadings prevWord = tokens[i - 1];
            AnalyzedTokenReadings nextWord = tokens[i + 1];

            if ("nang".equals(tokens[i])) {
                // Check if the tokens surrounding "nang" 
                // are in the verbs array and are the same
                if (isVerb(prevWord) && 
                    isVerb(nextWord) && 
                    prevWord.equals(nextWord)) {
                    
                    // this RuleMatch constructor is depracated.
                    RuleMatch ruleMatch = new RuleMatch(this, tokens[i].getStartPos(), tokens[i + 1].getEndPos(), getMessage());
                    
                    ruleMatches.add(ruleMatch);

                    RuleMatch newMatch = new RuleMatch(
                            this,
                            sentence,
                            i - 1,
                            i + 1,
                            this.getMessage()
                    );
                    ruleMatches.add(newMatch);
                }
            }
        }

        return ruleMatches.toArray(new RuleMatch[0]);
    }

    /**
     * Checks if a given token is a recognized Filipino verb.
     * 
     * @param word the token to check
     * @return true if the token is in the list of recognized verbs, false otherwise
     */
    private boolean isVerb(AnalyzedTokenReadings word) {
        for (String verb : VERBS) {
            if (verb.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the error message that will be displayed when a rule violation is found.
     * 
     * @return a string explaining the grammatical error and how to correct it
     */
    private String getMessage(){
        return "Pandiwa-Nang-Pandiwa misuse; the verbs surrounding 'nang' must be the same.";
    }
}