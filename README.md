#   how to make  a well designed (maybe over engineered), FizzBuzz Kata with The Open/Closed Principal and TDD


In this article we will see how the **TDD** and the  (S**O**LID) **Open/Closed principal** can help improve code quality and extensibility,

##### For this example we will use  :

- gradle
- java 17
- junit 5
- assertj 3
 

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

#### step by  step solution  :

##### configuration 1 :

before we start let's configure our dependencies 

```groovy

dependencies {
    testImplementation 'org.assertj:assertj-core:3.20.2'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter-params:5.7.0'
}
```

##### iteration 1 :

we write our first test and then we implement it with the simplest solution.

```java
class FizzBuzzTest {
  FizzBuzz fizzbuzz= new FizzBuzz();
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
_tests results : green_
##### iteration 2 :

we write a second test and make sure our implementation validate both tests

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

##### iteration 2 : refactoring

at this point our tests are basically identical (except for imput and expected result), the second one was created with a copy/past, and for our next test it will be also identical ,
maybe creating a junit  parametrized test could be a good idea.

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

##### iteration 3 : 
let's add the third test  and  make it pass

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

##### iteration 4 :


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

##### iteration 5 :

add the fift test that will test the buzz output 

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


##### iteration 6 :

continue adding more tests

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

##### iteration 7 :

continue adding more tests

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

##### iteration 8 :

add a fizzbuzz test use case and make it green 

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


##### iteration 9 :

add more tests.

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

First we have an implementation of fizzbuzz that is working, is tested, that can be "ready to production" ,second, now as a side effect of **TDD**, the coverage is quite good we covered a lot of usecases, and this can help us refactor mercilessly our code. 
The test beeing green is an absolute garantee that our code has no error, and it will garantee in the futur no regression in our code so let's 
refactor it and make it better.

##### refractoring 1 :

first let get rid of the line with the number 15 in it, it that does not match any functional requirement,  it's just a workaround to solve problem, when a  number is both multiple of 3 and 5 
but it make it difficult to map the code to the specifications, if we read the problem description the number 15 is not mentioned at all. 

NB : this kind  of gap between what is described and what is in the code  can create confusion for other developers in real life scenarios.

```java

public class FizzBuzz {  
  
  public String apply(int input){

      String value="";
      if(input % 3 ==0)
        value="Fizz";
      if(input % 5 ==0)
        value=value+ "Buzz";
      return value.isEmpty()?String.valueOf(input):value;
  }
}
```
_tests results : green_

##### refractoring 2 :
our code now is good but it lacks an important property wish is extensibility, 

whenever the fizzbuzz requirement changes, we will need to modify the fizzbuzz class, adding new if statements,  changing modulo  values, 
for each small change this class should change.
if this class were sealed in a module or a jar, there will be now way to add or change behavior, without changing it's code of creating new one via inheritance, 
this class is  closed to extension, the only way to change it behavior is to modify it,

In the next refactoring steps we will try to reverse this tendency and make it open to extension, closed to modification


```java

public class FizzBuzz {  
  
  Predicat<Integer> moduloThree= i -> input % 3 ==0;
  String fizz = "Fizz";
  
  Predicat<Integer> moduloFive= i -> input % 5 ==0;
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

  ReplacementRule fizzReplacementRule=  new ReplacementRule(i -> input % 3 ==0,"Fizz");
  ReplacementRule buzzReplacementRule=  new ReplacementRule(i -> input % 5 ==0,"Buzz");
 
  
  public String apply(int input){

      String value="";
      if(fizzReplacementRule.getRule().test(input))
        value=fizzReplacementRule.getReplacement();
      if(buzzReplacementRule.getRule().test(input))
        value=value+ fizzReplacementRule.getRule();
      return value.isEmpty()?String.valueOf(input):value;
  }
}
```

```java
public class ReplacementRule {

  private Predicate<Integer> rule;
  private String replacement;

  public ReplacementRule(Predicate<Integer> rule, String replacement) {
    this.rule = rule;
    this.replacement = replacement;
  }

  public Predicate<Integer> getRule() {
    return rule;
  }

  public String getReplacement() {
    return replacement;
  }
}
```
_tests results : green_

##### refractoring 4 :
let's make trhe number of rule not fixed to two but non limited.
```java

public class ReplacementEngine {
  private List<ReplacementRule> replacementRules;

  public ReplacementEngine() {
    this.replacementRules=List.of(new ReplacementRule(i-> i%3==0,"Fizz"), new ReplacementRule(i-> i%5==0,"Buzz"));
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

##### refractoring 4 :

we can now offer a constructur that help client,  configure  his initial fizzbuzz whenever he want.
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




##### extend the engine behavior by test client  :

we can see how without modifiyinn the code our client code can extend the  behavior 


```java
class ReplacementEngineTest {

  ReplacementEngine fizzbuzz = new ReplacementEngine();

  ReplacementEngine blabliOupla = new ReplacementEngine(
      List.of(new ReplacementRule(i-> i%7==0,"Bla")
          , new ReplacementRule(i-> i%8==0,"Bli"),
          new ReplacementRule(i-> i==100,"Oupla")));

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,Fizz","4,4","5,Buzz","6,Fizz","10,Buzz","15,FizzBuzz","39,Fizz","45,FizzBuzz"})
  void should_return_the_expected_string(int input,String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "should return {1} when input is {0}")
  @CsvSource({"1,1", "2,2", "3,3","4,Bla","5,5","7,Bli","56,BlaBli","100,Oupla","101,101"})
  void should_return_the_expected_string(int input,String expected) {
    assertThat(fizzbuzz.apply(input)).isEqualTo(expected);
  }


}
```


```java
public class MainClass {

public void fizzBuzzUseCase() {
ReplacementEngine fizzBuzz = new ReplacementEngine();
IntStream.range(1, 101).forEach(i -> System.out.println(fizzBuzz.apply(i)));
}

public void extendedUseCase() {
ReplacementEngine moneyEngine = new ReplacementEngine(List.of(
new ReplacementRule(i -> i % 1 == 0, "$"),
new ReplacementRule(i -> i % 2 == 0, "$$"),
new ReplacementRule(i -> i % 3 == 0, "$$$"),
new ReplacementRule(i -> i % 4 == 0, "$$$$"),
new ReplacementRule(i -> i % 5 == 0, "$$$$$"),
new ReplacementRule(i -> i % 6 == 0, "$$$$$$"),
new ReplacementRule(i -> i % 7 == 0, "$$$$$$$"),
new ReplacementRule(i -> i % 8 == 0, "$$$$$$$$"),
new ReplacementRule(i -> i % 9 == 0, "$$$$$$$$$"),
new ReplacementRule(i -> i % 10 == 0, "----------------"),
new ReplacementRule(i -> i == 100, "This is the End")
)

    );
    IntStream.range(1, 101).forEach(i -> System.out.println(moneyEngine.apply(i)));
}

public static void main(String[] args) {
new MainClass().fizzBuzzUseCase();
new MainClass().extendedUseCase();
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
