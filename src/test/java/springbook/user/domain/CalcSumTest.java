package springbook.user.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Before;

public class CalcSumTest {
   Calculator calculator;
   String numFilePath;

   @Before
    public void setUp(){
       this.calculator = new Calculator();
       this.numFilePath = Objects.requireNonNull(getClass().getResource("numbers.txt")).getPath();
   }

   @Test
    public void sumOfNumbers() throws IOException{
       assertThat(calculator.calcSum(this.numFilePath), is(10));
   }

   @Test
    public void multiplyOfNumbers() throws IOException{
       assertThat(calculator.calcMultiply(this.numFilePath), is(24));
   }
}
