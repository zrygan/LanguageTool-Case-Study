# LanguageTool-Case-Study

By Zhean Ganituen, Stephen Borja, & Justin Ching

This repository contains a modified version of LanguageTool, originally licensed under the GNU Lesser General Public License (LGPL) version 2.1.

The original LanguageTool project [LanguageTool GitHub Repository](https://github.com/languagetool-org/languagetool) and the license can be found in `LanguageTool_COPYING.txt`.

The original LanguageTool project uses third-party libraries, the licenses of these libraries can be found in `third-party-licenses/`.

This modified version includes significant changes made for a case study on Tagalog are in `CHANGES.txt`.

The case study can be viewed here [Case Study on the Filipino Language using Theory of Computation](https://github.com/zrygan/Filipino-Case-Study). 

## How To

### Requirements

- JDK 17

**This project does not require*:

- Maven or Gradle
- Or, *rebuilding* the LanguageTool source code.

### Simple Grammar

Simply run the batch file, if on Windows:

```
$ LT_RUN
```

Or, alternatively, run the `languagetool.jar` (for GUI) in the command-line:
```
$ java -jar languagetool.jar
```

Finally, you may also run the `languagetool-commandline.jar` (for CLI) along with a text file in the command-line:
```
$ java -jar languagetool-commandline.jar -l tl-PH <your_text_file.txt>
```

### Complex Grammar

Some grammar in the case study require context-sensitive grammars which cannot be represented by a Finite-State Machine (FSM) or Regular Expressions (regex). Since regex are not turing complete, we implemented this in Java (a Turing-complete programming language).

Simply run the 

## Creating your own Java Grammar Rules

**First**, familiarize yourself with the LanguageTool objects. The current Javadoc of LanguageTool is [here](https://languagetool.org/development/api/overview-summary.html). The important ones are:

* `org.languagetool.AnalyzedSentence`
* `org.languagetool.AnalyzedTokenReadings`
* `org.languagetool.rules.Rule`
* `org.languagetool.rules.RuleMatch`

**Second**, create the boilerplate Java code for the Grammar rule:

```java
package Filipino_Language;

// imports

/**
 * Sample Java rule file for https://github.com/zrygan/LanguageTool-Case-Study
 * @Author: Zhean Ganituen (Zrygan)
*/
public class NAME_OF_RULE extends Rule {
   public NAME_OF_RULE() {
   }

   /**
    * Creates the ID of the rule 
    */
   public String getId() {
      return "NAME_OF_RULE";
   }

   /**
    * Creates the description of the rule
    */
   public String getDescription() {
      return "This is some description of what the rule is for and what it checks, you may also provide an example.";
   }
   /**
    * Determines if the sentence contains a section that DOES NOT follow the rule
    */
   public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
        // Creates an array that will contain the errors
        List<RuleMatch> ruleMatches = new ArrayList<>();

        // Converts the sentence to a list of tokens or words
        AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();

        // Your rule checking procedure
        // In this example, it checks if a word is "test" and returns an error
        for (AnalyzedTokenReadings token : tokens) {

            // If the current token is the string "test"
            if (token.getToken().equalsIgnoreCase("test")) {
                
                // We add a a match that determines the rule that was not met
                // the sentence, the location of that error, and an error message.
                RuleMatch ruleMatch = new RuleMatch(
                        this,
                        sentence,
                        token.getStartPos(),
                        token.getEndPos(),
                        "Avoid using 'test'. Consider rewording."
                );

                // We suggest a replacement
                ruleMatch.setSuggestedReplacement("example");

                // We add the match to the array of matches
                ruleMatches.add(ruleMatch);
            }
        }

        // Convert the List<> to an array and return it
        return toRuleMatchArray(ruleMatches);
    }
}
```

**Third**, include the rule created in the `Filipino.java` file.

```java
package Filipino_Language;

// imports

/**
 * Java Driver code for the Filipino language and implementing the grammar rules (as Java Classes) not in LanguageTool.
 * @author Zhean Ganituen (zrygan)
 */
class Filipino {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    JLanguageTool langTool = new JLanguageTool(Languages.getLanguageForShortCode("tl-PH"));
    
    // IMPORTANT:
    // Keep adding rules here if needed
    langTool.addRule(new NAME_OF_RULE());

    System.out.print("Enter text: ");
    String text = sc.nextLine().trim().replaceAll("\\s+", " ");
    
    List<RuleMatch> matches = langTool.check(text);

    for (RuleMatch match : matches) {
      System.out.println("Potential error at characters " +
              match.getFromPos() + "-" + match.getToPos() + ": " +
              match.getMessage());
      System.out.println("Suggested correction: " +
              match.getSuggestedReplacements());
    }
  }
}
```

**Finally**, debug and run the file. See section `How To/Complex Grammar`
