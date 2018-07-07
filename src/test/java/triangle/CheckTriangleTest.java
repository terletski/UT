package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckTriangleTest {

    @DataProvider(name = "exceptionExpectedData")
    public Object[][] createExceptionData() {
        return new Object[][] {
                { Double.POSITIVE_INFINITY, 5.55, 2.22 }, //одна из сторон плюс бесконечность
                { 4.44, Double.POSITIVE_INFINITY, 3.33 },
                { 1.11, 6.66, Double.POSITIVE_INFINITY },
                { Double.NEGATIVE_INFINITY, 7.77, 8.88 }, //одна из сторон минус бесконечность
                { 9.99, Double.NEGATIVE_INFINITY, 10.10 },
                { 11.11, 12.12, Double.NEGATIVE_INFINITY },
        };
    }

    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][] {
                { 0, 2, 3.5, "a<=0"}, // одна из сторон имеет отрицательное значение либо 0
                { 1.1, 0, 2.3, "b<=0"},
                { 1.1, 3.3, 0, "c<=0"},
        };
    }

    @DataProvider(name = "negativeDataSum")
    public Object[][] createNegativeDataSum() {
        return new Object[][]{
                {1, 0.1, 2.5, "a+b<=c"}, // сумма длин двух сторон <= длины третьей стороны
                {2.5, 7.5, 5, "a+c<=b"},
                {20, 5.5, 0.1, "b+c<=a"},
        };
    }

    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][] {
                { 2.11, 2.32, 3.33},        // длина одной из сторон > суммы длин двух других
                { 2.12, 2.32, 3.34},
                { 2.13, 2.33, 3.35},
        };
    }

    @Test (dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void ExceptionTest (double a, double b, double c) {
        Triangle triangle = new Triangle(a, b, c);
        triangle.checkTriangle();
    }

    @Test (dataProvider = "negativeData")
    public void NegativeTest (double a, double b, double c, String message) {
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), message);
    }

    @Test (dataProvider = "negativeDataSum")
    public void NegativeSumTest (double a, double b, double c, String message) {
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertFalse(triangle.checkTriangle());
        Assert.assertEquals(triangle.getMessage(), message);
    }

    @Test (dataProvider = "positiveData")
    public void PositiveTest (double a, double b, double c) {
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertTrue(triangle.checkTriangle());
    }
}


