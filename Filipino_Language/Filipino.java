package Filipino_Language;

import org.languagetool.rules.RuleMatch;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

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
    langTool.addRule(new Kump_KumbRule());

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

    Recognizer recognizer = new Recognizer("Convensyon");
    System.out.println(recognizer.recognizeWord());
  }
}
