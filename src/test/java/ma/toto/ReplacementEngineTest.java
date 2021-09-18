package ma.toto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReplacementEngineTest {

  ReplacementEngine fizzbuzz = new ReplacementEngine(
      List.of(new ReplacementRule(i-> i%3==0,"Fizz"), new ReplacementRule(i-> i%5==0,"Buzz")));

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz","4,4","5,Buzz","6,Fizz","10,Buzz","15,FizzBuzz","39,Fizz","45,FizzBuzz"})
  void should_return_the_expected_string(int input,String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }

}
