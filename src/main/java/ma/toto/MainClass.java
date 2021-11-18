package ma.toto;

import java.util.List;
import java.util.stream.IntStream;

public class MainClass {

  public void fizzBuzzUseCase() {
    ReplacementEngine fizzBuzz = new ReplacementEngine();
    IntStream.range(1, 101).forEach(i -> System.out.print(fizzBuzz.apply(i)+ " "));
  }

  public void extendedUseCase() {
    ReplacementEngine moneyEngine = new ReplacementEngine(
        List.of(
        new ReplacementRule(i -> i % 10 == 0, "--------Multiple of ten--------"),
        new ReplacementRule(i -> i == 100, "This is the End")
    )
    );
    IntStream.range(1, 101).forEach(i -> System.out.println(moneyEngine.apply(i)));
  }

  public static void main(String[] args) {
    new MainClass().fizzBuzzUseCase();
    System.out.println();
    new MainClass().extendedUseCase();
  }


}
