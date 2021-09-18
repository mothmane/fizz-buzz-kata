package ma.toto;

import java.util.function.Predicate;

public class ReplacementRule {

  private Predicate<Integer> rule;
  private String replacement;

  public ReplacementRule(Predicate<Integer> rule, String replacement) {
    this.rule = rule;
    this.replacement = replacement;
  }

  public Predicate<Integer> getRule() {
    return rule;
  }

  public String getReplacement() {
    return replacement;
  }
}
