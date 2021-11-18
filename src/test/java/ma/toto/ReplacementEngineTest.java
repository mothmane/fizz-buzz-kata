package ma.toto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReplacementEngineTest {

  ReplacementEngine fizzbuzz = new ReplacementEngine();

  ReplacementEngine blabliOupla = new ReplacementEngine(
      List.of(new ReplacementRule(i -> i % 7 == 0, "Bla")
          , new ReplacementRule(i -> i % 8 == 0, "Bli"),
          new ReplacementRule(i -> i == 100, "Oupla")));

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz", "6,Fizz", "10,Buzz", "15,FizzBuzz",
      "39,Fizz", "45,FizzBuzz"})
  void test_fizzBuzz(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,3", "4,4", "5,5", "7,Bla", "56,BlaBli", "100,Oupla", "101,101"})
  void test_for_bla_bli_blou(int input, String expected) {
    assertThat(blabliOupla.apply(input)).isEqualTo(expected);
  }


}
