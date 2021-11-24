package ma.toto.rules;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ReplacementRuleImpl implements ReplacementRule {

  private final Predicate<Integer> rule;
  private final Supplier<String> replacementSupplier;

  public ReplacementRuleImpl(Predicate<Integer> rule, Supplier<String> replacementSupplier) {
    this.rule = rule;
    this.replacementSupplier = replacementSupplier;
  }

  public boolean appliable(Integer i) {
    return rule.test(i);
  }

  public String getReplacement() {
    return replacementSupplier.get();
  }
}
