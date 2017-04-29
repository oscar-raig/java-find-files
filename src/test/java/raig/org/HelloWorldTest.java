package raig.org;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.mockito.Mockito;





public class HelloWorldTest {

  @Test
  public void testFail() {

    assertEquals(2, 2);
  }


  @Test
  public void testAddEmpty() {
    int result = new Calculator().add("");
    assertEquals(0,result);
    // this test fails :P
  }

  @Test
  public void testAddOne() {
    int result = new Calculator().add("1");
    assertEquals(1,result);
  }

  @Test
  public void testMockitoCalculator() {
    Calculator calculator = Mockito.mock(Calculator.class);
    when(calculator.add(anyString())).thenReturn(14);
    int result  = calculator.add("1");
    assertEquals(14,result);

  }
}

