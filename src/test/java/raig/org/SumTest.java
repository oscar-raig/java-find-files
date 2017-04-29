package raig.org;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;


@RunWith(DataProviderRunner.class)
public class SumTest {

  private static Logger logger = Logger.getLogger(SumTest.class);

  public int functionUnderTest(int number) {
    return number + 1;
  }

  public int funcitonUnderTestRealAdd(int number1, int number2) {
    return number1 + number2;
  }

  @DataProvider
  public  static Object[][] dataProviderSumOne() {
    // @formatter:off
    return new Object[][] {
      {1,  2 },
      {2,  3},
      {3,  4}
    };
      // @formatter:on
  }


  @DataProvider
  public  static Object[][] dataProviderRealSum() {
    // @formatter:off
    return new Object[][]  {
      {1,  2, 3},
      {2,  3, 5},
      {3,  4, 7}
    };
    // @formatter:on
  }


  @Test
  @UseDataProvider("dataProviderSumOne")
  public void sumTest1to10(int number, int numberPlus1) {
    logger.info("Testing " + number ); 
    int result = functionUnderTest(number);
    Assert.assertEquals(numberPlus1, result);

  }

  @Test
  @UseDataProvider("dataProviderRealSum")
  public void sumTestRealSum(int number1, int number2, int sum) {

    int result = funcitonUnderTestRealAdd(number1, number2);
    Assert.assertEquals(sum, result);
  }
}
