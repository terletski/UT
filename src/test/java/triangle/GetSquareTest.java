package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetSquareTest {

    @DataProvider(name = "exceptionExpectedData")
    public Object[][] createExceptionData() {
        return new Object[][]{
                {0, 1.11, 2.22, "a=0"},    // одна из сторон имеет отрицательное значение или 0
                {3.33, 0, 4.44, "b=0"},
                {5.55, 6.66, 0, "c=0"},
                {-1.1, 2.2, 3.3, "a<0"},
                {4.4, -5.5, 6, "b<0"},
                {7.7, 8, -9.99, "c<0"},
                {Double.POSITIVE_INFINITY, 1.11, 2.22}, //одна из сторон  плюс бесконечность
                {3.33, Double.POSITIVE_INFINITY, 4.44},
                {5.55, 6.66, Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY, 7.77, 8.88},//одна из сторон  минус бесконечность
                {9.99, Double.NEGATIVE_INFINITY, 10.1},
                {11.11, 12.12, Double.NEGATIVE_INFINITY},
                {1.1, 2.2, 3.3, "a<=b+c"}, // длина одной из сторон <= сумме двух других
                {4.4, 5.5, 6.6, "b<=a+c"},
                {10, 5.1, 15.1, "c<=a+b"},
        };
    }

    @DataProvider(name = "data")
    public Object[][] createData() {
        return new Object[][]{
                {3, 4, 5, 6},
                {4, 5, 7, Math.sqrt(8 * (8 - 4) * (8 - 5) * (8 - 7))},
        };
    }

    @Test(dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void ExceptionTest(double a, double b, double c) {
        Triangle triangle = new Triangle(a, b, c);
        triangle.getSquare();
    }

    @Test(dataProvider = "data")
    public void Test(double a, double b, double c, double result) {
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), result);
    }
}





