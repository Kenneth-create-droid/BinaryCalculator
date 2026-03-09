package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Binary class.
 */
public class BinaryTest {

    /**
     * Test The constructor with a valid binary value
     */
    @Test
    public void normalConstructor() {
        Binary binary = new Binary("1001001");
        assertTrue(binary.getValue().equals("1001001"));
    }

    /**
     * Test The constructor with an invalid binary value of out-of-range digits
     */
    @Test
    public void constructorWithInvalidDigits() {
        Binary binary = new Binary("1001001211");
        assertTrue(binary.getValue().equals("0"));
    }

    /**
     * Test The constructor with an invalid binary value of alphabetic characters
     */
    @Test
    public void constructorWithInvalidChars() {
        Binary binary = new Binary("1001001A");
        assertTrue(binary.getValue().equals("0"));
    }

    /**
     * Test The constructor with an invalid binary value that has a sign
     */
    @Test
    public void constructorWithNegativeSign() {
        Binary binary = new Binary("-1001001");
        assertTrue(binary.getValue().equals("0"));
    }

    /**
     * Test The constructor with a zero leading valid binary value
     */
    @Test
    public void constructorWithZeroTailing() {
        Binary binary = new Binary("00001001");
        assertTrue(binary.getValue().equals("1001"));
    }

    /**
     * Test The constructor with an empty string
     */
    @Test
    public void constructorEmptyString() {
        Binary binary = new Binary("");
        assertTrue(binary.getValue().equals("0"));
    }

    /**
     * Test The add function with two binary numbers of the same length
     */
    @Test
    public void add() {
        Binary binary1 = new Binary("1000");
        Binary binary2 = new Binary("1111");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("10111"));
    }

    /**
     * Test The add function with two binary numbers, first length < second length
     */
    @Test
    public void add2() {
        Binary binary1 = new Binary("1010");
        Binary binary2 = new Binary("11");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1101"));
    }

    /**
     * Test The add function with two binary numbers, first length > second length
     */
    @Test
    public void add3() {
        Binary binary1 = new Binary("11");
        Binary binary2 = new Binary("1010");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1101"));
    }

    /**
     * Test The add function with a binary number with zero
     */
    @Test
    public void add4() {
        Binary binary1 = new Binary("0");
        Binary binary2 = new Binary("1010");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1010"));
    }

    /**
     * Test The add function with two zeros
     */
    @Test
    public void add5() {
        Binary binary1 = new Binary("0");
        Binary binary2 = new Binary("0");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("0"));
    }

    /* =========================================================
       NEW REQUIRED TESTS: OR (3+)
       ========================================================= */

    @Test
    public void or_basicSameLength() {
        Binary a = new Binary("1010");
        Binary b = new Binary("1100");
        assertEquals("1110", Binary.or(a, b).getValue());
    }

    @Test
    public void or_differentLengths() {
        Binary a = new Binary("101");
        Binary b = new Binary("11");
        // 101 OR 011 = 111
        assertEquals("111", Binary.or(a, b).getValue());
    }

    @Test
    public void or_withLeadingZeros() {
        Binary a = new Binary("0010");
        Binary b = new Binary("0001");
        // 0010 OR 0001 = 0011 -> constructor trims => "11"
        assertEquals("11", Binary.or(a, b).getValue());
    }

    /* =========================================================
       NEW REQUIRED TESTS: AND (3+)
       ========================================================= */

    @Test
    public void and_basicSameLength() {
        Binary a = new Binary("1010");
        Binary b = new Binary("1100");
        assertEquals("1000", Binary.and(a, b).getValue());
    }

    @Test
    public void and_differentLengths() {
        Binary a = new Binary("101");
        Binary b = new Binary("11");
        // 101 AND 011 = 001 -> constructor trims => "1"
        assertEquals("1", Binary.and(a, b).getValue());
    }

    @Test
    public void and_allZeroResult() {
        Binary a = new Binary("1000");
        Binary b = new Binary("0111");
        assertEquals("0", Binary.and(a, b).getValue());
    }

    /* =========================================================
       NEW REQUIRED TESTS: MULTIPLY (3+)
       ========================================================= */

    @Test
    public void multiply_basic() {
        // 101 (5) * 11 (3) = 1111 (15)
        Binary a = new Binary("101");
        Binary b = new Binary("11");
        assertEquals("1111", Binary.multiply(a, b).getValue());
    }

    @Test
    public void multiply_byZero() {
        Binary a = new Binary("101010");
        Binary b = new Binary("0");
        assertEquals("0", Binary.multiply(a, b).getValue());
    }

    @Test
    public void multiply_byOne() {
        Binary a = new Binary("111000");
        Binary b = new Binary("1");
        assertEquals("111000", Binary.multiply(a, b).getValue());
    }

    // Optional extra (safe to keep)
    @Test
    public void multiply_larger() {
        // 110 (6) * 101 (5) = 11110 (30)
        Binary a = new Binary("110");
        Binary b = new Binary("101");
        assertEquals("11110", Binary.multiply(a, b).getValue());
    }
}
