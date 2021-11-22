package ma.toto;

import java.util.List;
import java.util.stream.IntStream;
import ma.toto.engine.ReplacementEngine;
import ma.toto.engine.ReplacementEngineDefault;
import ma.toto.rules.ReplacementRuleImpl;

public class MainClass {

  public static void main(String[] args) {
    new MainClass().fizzBuzzUseCase();
    System.out.println();
    new MainClass().extendedUseCase();
  }

  public void fizzBuzzUseCase() {
    ReplacementEngine fizzBuzz = new FizzBuzz();
    IntStream.range(1, 101).forEach(i -> System.out.print(fizzBuzz.apply(i) + " "));
  }

  public void extendedUseCase() {
    ReplacementEngine moneyEngine = new ReplacementEngineDefault(
        List.of(
            new ReplacementRuleImpl(i -> i % 10 == 0, () -> "--------Multiple of ten--------"),
            new ReplacementRuleImpl(i -> i == 100, () -> "This is the End")
        )
    );
    IntStream.range(1, 101).forEach(i -> System.out.println(moneyEngine.apply(i)));
  }


}
