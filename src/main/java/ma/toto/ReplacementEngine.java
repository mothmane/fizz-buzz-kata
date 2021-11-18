package ma.toto;

import java.util.List;

public class ReplacementEngine {

  private List<ReplacementRule> replacementRules;

  public ReplacementEngine() {
    this.replacementRules = List.of(new ReplacementRule(i -> i % 3 == 0, "Fizz"),
        new ReplacementRule(i -> i % 5 == 0, "Buzz"));

  }

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
