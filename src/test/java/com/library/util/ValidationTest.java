package com.library.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Validation}.
 */
public class ValidationTest {

    @Test
    @DisplayName("isValidISBN With Thirteen Digits -> True")
    void testThirteenDigitIsbnIsValid() {
        Assertions.assertTrue(Validation.isValidISBN("9780132350884"));
    }

    @Test
    @DisplayName("isValidISBN With Twelve Digits -> False")
    void testTwelveDigitIsbnIsInvalid() {
        Assertions.assertFalse(Validation.isValidISBN("978013235088"));
    }

    @Test
    @DisplayName("isValidISBN With Non Numeric -> False")
    void testNonNumericIsbnIsInvalid() {
        Assertions.assertFalse(Validation.isValidISBN("ABCDEFGHIJKLM"));
    }

    @Test
    @DisplayName("isValidISBN With Null -> False")
    void testNullIsbnIsInvalid() {
        Assertions.assertFalse(Validation.isValidISBN(null));
    }

    @Test
    @DisplayName("isValidUserId With Valid Pattern -> True")
    void testValidUserIdAccepted() {
        Assertions.assertTrue(Validation.isValidUserId("S001"));
        Assertions.assertTrue(Validation.isValidUserId("T123"));
        Assertions.assertTrue(Validation.isValidUserId("ADM"));
    }

    @Test
    @DisplayName("isValidUserId With Lowercase -> False")
    void testLowercaseUserIdRejected() {
        Assertions.assertFalse(Validation.isValidUserId("s001"));
    }

    @Test
    @DisplayName("isNonBlank With Whitespace -> False")
    void testBlankValuesFailIsNonBlank() {
        Assertions.assertFalse(Validation.isNonBlank("   "));
        Assertions.assertFalse(Validation.isNonBlank(""));
        Assertions.assertFalse(Validation.isNonBlank(null));
    }

    @Test
    @DisplayName("isNonBlank With Real Content -> True")
    void testNonBlankValuesPass() {
        Assertions.assertTrue(Validation.isNonBlank("hello"));
    }
}
