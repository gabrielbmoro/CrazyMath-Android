package com.gabrielbmoro.crazymath.core.extensions

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtTest{

    @Test
    fun `when the email is invalid`(){
        // arrange
        val email = "@test.com"

        // act
        val result = email.isEmailValid()

        // assert
        assertFalse(result)
    }

    @Test
    fun `when the email is valid`(){
        // arrange
        val email = "sd2@test.com"

        // act
        val result = email.isEmailValid()

        // assert
        assertTrue(result)
    }
}