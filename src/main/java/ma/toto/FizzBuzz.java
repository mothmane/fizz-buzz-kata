package ma.toto;

import java.util.List;
import ma.toto.engine.ReplacementEngineDefault;
import ma.toto.rules.ReplacementRuleImpl;

public class FizzBuzz extends ReplacementEngineDefault {

  public static final String FIZZ = "Fizz";
  public static final String BUZZ = "Buzz";

  public FizzBuzz() {
    super(
        List.of(
            new ReplacementRuleImpl(i -> i % 3 == 0, () -> FIZZ),
            new ReplacementRuleImpl(i -> i % 5 == 0, () -> BUZZ))
    );
  }

}
