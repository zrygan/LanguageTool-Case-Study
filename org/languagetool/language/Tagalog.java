package org.languagetool.language;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.language.tl.MorfologikTagalogSpellerRule;
import org.languagetool.language.tokenizers.TagalogWordTokenizer;
import org.languagetool.rules.CommaWhitespaceRule;
import org.languagetool.rules.DoublePunctuationRule;
import org.languagetool.rules.GenericUnpairedBracketsRule;
import org.languagetool.rules.MultipleWhitespaceRule;
import org.languagetool.rules.Rule;
import org.languagetool.rules.UppercaseSentenceStartRule;
import org.languagetool.rules.spelling.SpellingCheckRule;
import org.languagetool.tagging.Tagger;
import org.languagetool.tagging.tl.TagalogTagger;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
import org.languagetool.tokenizers.Tokenizer;

/** @deprecated */
@Deprecated
public class Tagalog extends Language {
   public Tagalog() {
   }

   public String getName() {
      return "Tagalog";
   }

   public String getShortCode() {
      return "tl";
   }

   public String[] getCountries() {
      return new String[]{"PH"};
   }

   public SentenceTokenizer createDefaultSentenceTokenizer() {
      return new SRXSentenceTokenizer(this);
   }

   public Tokenizer createDefaultWordTokenizer() {
      return new TagalogWordTokenizer();
   }

   @NotNull
   public Tagger createDefaultTagger() {
      return new TagalogTagger();
   }

   public Contributor[] getMaintainers() {
      return new Contributor[]{new Contributor("Nathaniel Oco"), new Contributor("Allan Borra")};
   }

   public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
      return Arrays.asList(new CommaWhitespaceRule(messages), new DoublePunctuationRule(messages), new GenericUnpairedBracketsRule(messages), new UppercaseSentenceStartRule(messages, this), new MultipleWhitespaceRule(messages, this), new MorfologikTagalogSpellerRule(messages, this, userConfig, altLanguages));
   }

   @Nullable
   protected SpellingCheckRule createDefaultSpellingRule(ResourceBundle messages) throws IOException {
      return new MorfologikTagalogSpellerRule(messages, this, (UserConfig)null, (List)null);
   }
}
