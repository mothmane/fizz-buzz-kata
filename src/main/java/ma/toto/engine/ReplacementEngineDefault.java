package ma.toto.engine;

import java.util.Collections;
import java.util.List;
import ma.toto.rules.ReplacementRule;

public class ReplacementEngineDefault implements ReplacementEngine {

  private final List<ReplacementRule> replacementRules;

  public ReplacementEngineDefault(List<ReplacementRule> replacementRules) {
    this.replacementRules = Collections.unmodifiableList(replacementRules);
  }

  public String apply(Integer i) {
    return replacementRules.stream()
        .filter(r -> r.appliable(i))
        .map(ReplacementRule::getReplacement)
        .reduce(String::concat)
        .orElse(String.valueOf(i));
  }
}
