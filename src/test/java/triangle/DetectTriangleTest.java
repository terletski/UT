package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DetectTriangleTest {

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
                {3, 4, 5, 8},//прямоугольный 8
                {4, 5, Math.sqrt(4 * 4 + 5 * 5), 8},//прямоугольный 8
                {Math.sqrt(3 * 3 + 4 * 4), 3, 4, 8},//прямоугольный 8
                {4, 4, 4, 1,2},//равносторонний и равнобедренный 1 и 2
                {1.1, 1.1, 3.3, 2}, //равнобедренный 2
                {1.1, 3.3, 3.3, 2}, //равнобедренный 2
                {1.1, 4.4, 1.1, 2}, //равнобедренный 2
                {4.1, 7.5, 7.8, 4}, // обычный 4
        };
    }

    @Test(dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void ExceptionTest(double a, double b, double c) {
        Triangle triangle = new Triangle(a, b, c);
        triangle.detectTriangle();
    }

    @Test(dataProvider = "data")
    public void Test(double a, double b, double c, int result) {
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), result);
    }
}




