import org.example.Discount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JUnit5Test {
    //Testing Berdasarkan EV
    private static Stream<Arguments> providePrice(){
        return Stream.of(
               Arguments.of(0, 3500000),
               Arguments.of(10, 4500000),
                Arguments.of(22, 15500000),
                Arguments.of(40, 40500000),
                Arguments.of(-1, -4000000)
        );
    }

    @ParameterizedTest
    @MethodSource("providePrice")
    void parameterizedTestDiscount(int expected, int price){
        Assertions.assertEquals(expected, Discount.getPajak(price));
    }

    //Testing Berdasarkan BVA
    //vEC1 => 0 <= x <= 4.000.000
    private static Stream<Arguments> BVA1Parameters(){
        return Stream.of(
                //Testing Batas Bawah (-1,0,1) (Nilai Error)
                Arguments.of(false, -1),
                Arguments.of(true, 0),
                Arguments.of(true, 1),
                //Testing Batas Atas (3.999.999, 4.000.000, 4.001.000) (Nilai Valid)
                Arguments.of(true, 3999999),
                Arguments.of(true, 4000000),
                Arguments.of(false, 4001000)
        );
    }
    @ParameterizedTest
    @MethodSource("BVA1Parameters")
    public void testBVA1Discount(boolean expected, int price){
        assertEquals(expected, Discount.getPajak(price) == 0);
    }

    //vEC2 => 4.000.000 < x <= 15.000.000
    private static Stream<Arguments> BVA2Parameters(){
        return Stream.of(
                //Testing Batas Bawah (3.999.999, 4.000.000, 4.001.000)
                Arguments.of(false, 3999999),
                Arguments.of(false, 4000000),
                Arguments.of(true, 4001000),
                //Testing Batas Atas (14.999.999, 15.000.000, 15.001.000)
                Arguments.of(true, 14999999),
                Arguments.of(true, 15000000),
                Arguments.of(false, 15001000)
        );
    }
    @ParameterizedTest
    @MethodSource("BVA2Parameters")
    public void testBVA2Discount(boolean expected, int price){
        assertEquals(expected, Discount.getPajak(price) == 10);
    }

    //vEC3 => 15.000.000 < x <= 40.000.000
    private static Stream<Arguments> BVA3Parameters(){
        return Stream.of(
                //Testing Batas Bawah (14.999.999, 15.000.000, 15.001.000)
                Arguments.of(false, 14999999),
                Arguments.of(false, 15000000),
                Arguments.of(true, 15001000),
                //Testing Batas Atas (39.999.999, 40.000.000, 40.001.000)
                Arguments.of(true, 39999999),
                Arguments.of(true, 40000000),
                Arguments.of(false, 40001000)
        );
    }
    @ParameterizedTest
    @MethodSource("BVA3Parameters")
    public void testBVA3Discount(boolean expected, int price){
        assertEquals(expected, Discount.getPajak(price) == 22);
    }

    //vEC4 => x > 40.000.000
    private static Stream<Arguments> BVA4Parameters(){
        return Stream.of(
                Arguments.of(false, 39999999),
                Arguments.of(false, 40000000),
                Arguments.of(true, 40001000)
        );
    }
    @ParameterizedTest
    @MethodSource("BVA4Parameters")
    public void testBVA4Discount(boolean expected, int price){
        assertEquals(expected, Discount.getPajak(price) == 40);
    }
}
