#   TDD and Open/Closed Principle applied to FizzBuzz Kata 

In this article we will see how the **TDD** and the  (S**O**LID) **Open/Closed principle** can help us create a clean and extendable fizzbuzz kata solution.

### What You Need

Any text editor or IDE

 - JDK 11 or later (we are using 17)

 - Gradle 4+


# **Fizz Buzz Kata**

## Problem Description 

Write a program that prints one line for each number from 1 to 100
* For multiples of **three** print **Fizz** instead of the number
* For the multiples of **five** print **Buzz** instead of the number
* For numbers which are multiples of both **three** and **five** print **FizzBuzz** instead of the number

### Result example

```
1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, Fizz Buzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, Fizz Buzz, 31, 32, Fizz, 34, Buzz, Fizz, ...

```


### Step by step solution

First let's configure our dependencies by adding the below lines to our gradle.build file dependencies section

```groovy

dependencies {
    testImplementation 'org.assertj:assertj-core:3.20.2'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter-params:5.7.0'
}
```

##### iteration 1

Let's write a first test and then make it pass with the simplest solution.

```java
class FizzBuzzTest {
  FizzBuzz fizzbuzz = new FizzBuzz();
  @Test
  void should_return_1_when_input_is_1() {
    assertThat(fizzbuzz.apply(1)).isEqualTo("1");
  }
}
```

_tests results : red_

```java
public class  FizzBuzz {
   public String apply(int input){
    return "1";
   }
}
```

_tests results : green _

##### interation 2

Write a second test and make sure the new implementation validates both tests

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @Test
  void should_return_1_when_input_is_1() {
    assertThat(fizzbuzz.apply(1)).isEqualTo("1");
  }

  @Test
  void should_return_2_when_input_is_2() {
    assertThat(fizzbuzz.apply(2)).isEqualTo("2");
  }
}
```

_tests results : red_

```java

public class  FizzBuzz {
  public String apply(int input){
    return String.valueOf(input);
  }
}
```
_tests results : green_

##### iteration 2 :  refactoring

At this point, our tests are basically identical (except for input and expected result),so let's create a junit parameterized test.

```java
class FizzBuzzTest {
  
  FizzBuzz fizzbuzz= new FizzBuzz();
  
  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2"})
  void should_return_the_expected_string(int input,String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```
_tests results : green _

##### iteration 3 

Let's add the third test and  make it pass.

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```

_tests results : red_

```java

public class FizzBuzz {
public String apply(int input){
    if(input % 3 ==0)
       return "Fizz";
    return String.valueOf(input);
}
}
````

_tests results : green_

##### iteration 4

Another test ...

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```
_tests results : green_

##### iteration 5

Add a test that will test the 'Buzz' output.

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```
_tests results : red_

```java

public class FizzBuzz {  
  
  public String apply(int input){
      if(input % 3 ==0)
        return "Fizz";
      if(input % 5 ==0)
        return "Buzz";
      return String.valueOf(input);
  }
}
```
_tests results : green_


##### iteration 6

Continue adding more tests.

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz","6,Fizz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```
_tests results : green_

##### iteration 7

Continue adding more tests.

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz","6,Fizz","10,Buzz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```

_tests results : green_

##### iteration 8

Add a 'FizzBuzz' output test use case and make it green. 

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz","6,Fizz","10,Buzz","15,FizzBuzz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```
_tests results : red_

```java

public class FizzBuzz {  
  
  public String apply(int input){

      if(input % 15 ==0)
        return "FizzBuzz";
      if(input % 3 ==0)
        return "Fizz";
      if(input % 5 ==0)
        return "Buzz";
     
      return String.valueOf(input);
  }
}
```
_tests results : green_


##### iteration 9

Add more tests.

```java
class FizzBuzzTest {

  FizzBuzz fizzbuzz = new FizzBuzz();

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz", "4,4", "5,Buzz","6,Fizz","10,Buzz","15,FizzBuzz","39,Fizz","45,FizzBuzz"})
  void should_return_the_expected_string(int input, String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }
}
```

_tests results : green_

##### to summarize

First we have a working, well tested, and "ready to production"  implementation of FizzBuzz, 
second,  as a side effect of **TDD**, the coverage is quite high, 
we covered a lot of use cases and this can help us refactor mercilessly our code. 
The tests being green is an absolute guarantee that our code has no error,
It make us confident in test capacity to detect any potential regression.

Let's refactor the code and make it better.


### refactoring 

##### iteration 1

First let's get rid of the line with the number 15 in it, 
in the kata problem description the number '15' is not mentioned once,
it's was just our solution to return 'FizzBuzz' when a number is both multiple of 3 and 5.

```java

public class FizzBuzz {  
  
  public String apply(int input){

      String value = "";
      if(input % 3 == 0)
        value = "Fizz";
      if(input % 5 == 0)
        value = value + "Buzz";
      return value.isEmpty() ?  String.valueOf(input) :v  alue;
  }
}
```
_tests results : green_

##### refractoring 2 :
Our code now is better, but it still lacks an important property wish is extensibility.

Whenever the FizzBuzz requirement changes, we will need to edit and recompile the FizzBuzz class code,

if this class was sealed in a module or a jar, there will be no way to add or change behavior
without creating a new implementation via inheritance.

This class is closed to extension.

In the next refactoring steps we will try to reverse this tendency and make it open to extension, closed to modification.


```java

public class FizzBuzz {  
  
  Predicate<Integer> moduloThree= i -> input % 3 ==0;
  String fizz = "Fizz";
  
  Predicate<Integer> moduloFive= i -> input % 5 ==0;
  String buzz = "Buzz";
  
  public String apply(int input){

      String value="";
      if(moduloThree.test(input))
        value=fizz;
      if(moduloFive.test(input))
        value=value+ buzz;
      return value.isEmpty()?String.valueOf(input):value;
  }
}
```

_tests results : green_

##### refractoring 3 :


```java

public class FizzBuzz {

  ReplacementRule fizzReplacementRule=  new ReplacementRuleImpl(i -> input % 3 ==0,"Fizz");
  ReplacementRule buzzReplacementRule=  new ReplacementRuleImpl(i -> input % 5 ==0,"Buzz");
 
  
  public String apply(int input){

      String value="";
      if(fizzReplacementRule.appliable(input))
        value=fizzReplacementRule.getReplacement();
      if(buzzReplacementRule.appliable(input))
        value=value+ fizzReplacementRule.getReplacement();
      return value.isEmpty()?String.valueOf(input):value;
  }
}
```
```java

public interface ReplacementRule {

   boolean appliable(Integer i);

   String  getReplacement();
}

```
```java

public class ReplacementRuleImpl implements  ReplacementRule{

  private Predicate<Integer> rule;
  private Supplier<String> replacementSupplier;

  public ReplacementRuleImpl(Predicate<Integer> rule, Supplier<String>  replacementSupplier) {
    this.rule = rule;
    this.replacementSupplier = replacementSupplier;
  }

  public boolean appliable(Integer i) {
    return rule.test(i);
  }

  public String  getReplacement() {
    return replacementSupplier.get();
  }
}
```
_tests results : green_

##### iteration 4


```java

public class FizzBuzz {

  ReplacementRule fizzReplacementRule=  new ReplacementRuleImpl(i -> input % 3 ==0,"Fizz");
  ReplacementRule buzzReplacementRule=  new ReplacementRuleImpl(i -> input % 5 ==0,"Buzz");
 
  
  public String apply(int input){

      String value="";
      if(fizzReplacementRule.appliable(input))
        value=fizzReplacementRule.getReplacement();
      if(buzzReplacementRule.appliable(input))
        value=value+ fizzReplacementRule.getRule();
      return value.isEmpty()?String.valueOf(input):value;
  }
}
```

```java

public interface ReplacementRule {

   boolean appliable(Integer i);

   String  getReplacement();
}

```

```java
public class ReplacementRuleImpl {

  private Predicate<Integer> rule;
  private Supplier<String> replacementSupplier;

  public ReplacementRuleImpl(Predicate<Integer> rule, Supplier<String>  replacementSupplier) {
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
```
_tests results : green_

##### iteration 5

we can change the two replacement rules to  a list of rules, that we stream and each time the rule condition applies we replace the input number with the correspondent replacement and then we concatenate all the returned strings.


```java

public class FizzBuzz {

  private List<ReplacementRule> replacementRules;

  public FizzBuzz() {
    this.replacementRules= Collections.unmodifiableList(
        List.of(
            new ReplacementRuleImpl(i -> i % 3 == 0, () -> "Fizz"),
            new ReplacementRuleImpl(i -> i % 5 == 0, () -> "Buzz")
        )
    );
  }

  public String apply(Integer i) {
    return replacementRules.stream()
        .filter(r -> r.appliable(i))
        .map(ReplacementRule::getReplacement)
        .reduce(String::concat)
        .orElse(String.valueOf(i));
  }
}
```

##### iteration 5 

 We can create a good level of abstraction for our replacement engine,

```java

public interface ReplacementEngine extends Function<Integer, String> {

}

```
```java

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

```

```java

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

```

_tests results : green_

##### refractoring 5 :

We can now offer a constructor that help clients configure their own rules.

```java

class ReplacementEngineTest {

  ReplacementEngine fizzbuzz = new FizzBuzz();

  ReplacementEngine blabliOupla = new ReplacementEngineDefault(
      List.of(new ReplacementRuleImpl(i -> i % 7 == 0, () -> "Bla")
          , new ReplacementRuleImpl(i -> i % 8 == 0, () -> "Bli"),
          new ReplacementRuleImpl(i -> i == 100, () -> "Oupla")));

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

```

```java

public class ReplacementEngine {
  private List<ReplacementRule> replacementRules;

  public ReplacementEngine() {
    this.replacementRules=List.of(new ReplacementRule(i-> i%3==0,"Fizz"), new ReplacementRule(i-> i%5==0,"Buzz"));
  }

  public ReplacementEngine(List<ReplacementRule> replacementRules) {
    this.replacementRules=replacementRules;
  }
  public String apply(int i) {
    return replacementRules.stream()
        .filter(r -> r.getRule().test(i))
        .map(ReplacementRule::getReplacement)
        .reduce(String::concat)
        .orElse(String.valueOf(i));
  }
}
```

_tests results : green_

##### the fizz buzz demo  :

```java

public class MainClass {

  public void fizzBuzzUseCase() {
    ReplacementEngine fizzBuzz = new ReplacementEngine();
    IntStream.range(1, 101).forEach(i -> System.out.println(fizzBuzz.apply(i)));
  }
  
  public static void main(String[] args) {
    new MainClass().fizzBuzzUseCase();
  }
  
}

```



<!-- CONTACT -->
## Contact

Maniar Othmane

[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othmane-maniar-2364b518/
