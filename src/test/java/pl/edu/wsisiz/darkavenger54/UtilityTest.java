package pl.edu.wsisiz.darkavenger54;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void validNumberWithinBounds() {
        assertTrue(Utility.tryParseIntAndCheckIntegerClosed("5", 1, 10));
    }

    @Test
    void numberBelowMin() {
        assertFalse(Utility.tryParseIntAndCheckIntegerClosed("0", 1, 10));
    }

    @Test
    void numberAboveMax() {
        assertFalse(Utility.tryParseIntAndCheckIntegerClosed("11", 1, 10));
    }

    @Test
    void nonNumericInput() {
        assertFalse(Utility.tryParseIntAndCheckIntegerClosed("abc", 1, 10));
    }
}
