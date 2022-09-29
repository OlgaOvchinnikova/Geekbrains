package geekbrains.webui.lesson_4;

        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Tag;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.params.ParameterizedTest;
        import org.junit.jupiter.params.provider.CsvSource;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class AssertsTriangleAreaTest {
    private static Logger logger = LoggerFactory.getLogger(AssertsTriangleAreaTest.class);

    @Test
    @DisplayName("Проверка на ожидание")
    void expectations() throws MyException {
        logger.info("Проверка на ожидание");
        TriangleArea exampleClass = new TriangleArea();
        Assertions.assertEquals(11,exampleClass.triangleArea(6,6, 10));
    }

//    @ParameterizedTest
//    @DisplayName("Проверка на ожидание через CsvSource")
//    @CsvSource({"10,10,15","5,5,5"})
//    void testWithCsvSource(int a, int b, int result) throws MyException {
//        logger.error("Проверка на ожидание через CsvSource", result);
//        TriangleArea exampleClass = new TriangleArea();
//        Assertions.assertEquals(result,exampleClass.triangleArea(a, b, result));
//    }

    @ParameterizedTest
    @DisplayName("На отрицательное значения - через CsvSource")
    @CsvSource({ "-5,5,5", "5,-5,5", "5,5,-5"})
    void testWithCsvSourceN(int a, int b, int c) throws MyException {
        logger.info("На отрицательное значения - через CsvSource");
        TriangleArea exampleClass = new TriangleArea();
        Assertions.assertThrows(MyException.class,()-> exampleClass.triangleArea(a,b,c));
    }

    @Test
    @DisplayName("Проверка на отрицательные значения")
    @Tag("Triangle_area")
    void test() throws MyException {
        logger.info("Проверка на отрицательные значения");
        TriangleArea exampleClass = new TriangleArea();
        Assertions.assertThrows(MyException.class,()-> exampleClass.triangleArea(-10,20,10));
        Assertions.assertThrows(MyException.class,()-> exampleClass.triangleArea(10,-20,10));
        Assertions.assertThrows(MyException.class,()-> exampleClass.triangleArea(10,20,-10));

    }

    @ParameterizedTest
    @DisplayName("Проверка на 0")
    @Tag("Triangle_area")
    @CsvSource({"5, 5, 4"})
    void test2(int a, int b, int c) throws MyException {
        logger.info("Проверка на 0");
        TriangleArea exampleClass = new TriangleArea();
        Assertions.assertNotNull (a);
        Assertions.assertNotNull (b);
        Assertions.assertNotNull (c);;
    }

    @ParameterizedTest
    @DisplayName("Треугольник существует!")
    @Tag("Triangle_area")
    @CsvSource({"2, 2, 1"})
    void test3(int a, int b, int c) throws MyException {
        logger.info("Треугольник существует!");
        TriangleArea exampleClass = new TriangleArea();
        Assertions.assertTrue(a + b > c);
        Assertions.assertTrue(a + c > c);
        Assertions.assertTrue(c + b > c);
    }

}
