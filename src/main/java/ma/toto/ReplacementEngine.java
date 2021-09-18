package ma.toto;

import java.util.List;

public class ReplacementEngine {

  private List<ReplacementRule> replacementRules;

  public ReplacementEngine(List<ReplacementRule> replacementRules) {
    this.replacementRules = replacementRules;
  }

  public String apply(int i) {
    return replacementRules.stream()
        .filter(r -> r.getRule().test(i))
        .map(ReplacementRule::getReplacement)
        .reduce(String::concat)
        .orElse(String.valueOf(i));
  }
}
