package com.gabrielbmoro.crazymath.core.extensions

import org.junit.Test
import com.google.common.truth.Truth.*

class ArrayExtTest {

    @Test
    fun `when the multi-dimension array has 36 string elements - countTotalElements`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("123", "123", "123", "123", "123", "123"),
                arrayOf("123", "123", "123", "123", "123", "123"),
                arrayOf("123", "123", "123", "123", "123", "123"),
                arrayOf("123", "123", "123", "123", "123", "123"),
                arrayOf("123", "123", "123", "123", "123", "123"),
                arrayOf("123", "123", "123", "123", "123", "123")
        )

        // act
        val actual = arrayOfArrays.countTotalElements()

        // assert
        val expectedResult = 36
        assertThat(actual).isEqualTo(expectedResult)
    }

    @Test
    fun `when the multi-dimension array has 25 int elements - countTotalElements`() {
        // arrange
        val arrayOfArrays = arrayOf(
                arrayOf(10, 20, 30, 40, 50),
                arrayOf(10, 20, 30, 40, 50),
                arrayOf(10, 20, 30, 40, 50),
                arrayOf(10, 20, 30, 40, 50),
                arrayOf(10, 20, 30, 40, 2)
        )

        // act
        val actual = arrayOfArrays.countTotalElements()

        // assert
        val expectedResult = 25
        assertThat(actual).isEqualTo(expectedResult)
    }

    @Test
    fun `when the row index is invalid - safeSetValue - false`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("12", "42", ""),
                arrayOf("12", "42", ""),
                arrayOf("12", "42", "")
        )

        // act
        val actual = arrayOfArrays.safeSetValue(
                i = 4,
                j = 2,
                value = "2"
        )

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `when the column index is invalid - safeSetValue - false`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("12", "42", ""),
                arrayOf("12", "42", ""),
                arrayOf("12", "42", "")
        )

        // act
        val actual = arrayOfArrays.safeSetValue(
                i = 0,
                j = 3,
                value = "2"
        )

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `when the indexes are valid - safeSetValue - true`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("12", "42", ""),
                arrayOf("12", "42", ""),
                arrayOf("12", "42", "")
        )

        // act
        val actual = arrayOfArrays.safeSetValue(
                i = 0,
                j = 1,
                value = "2"
        )
        val currentValue = arrayOfArrays[0][1]

        // assert
        assertThat(actual).isTrue()
        assertThat(currentValue).isEqualTo("2")
    }

    @Test
    fun `when the indexes are invalid - safeGetValue - null`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("12", "42", ""),
                arrayOf("12", "42", ""),
                arrayOf("12", "42", "")
        )

        // act
        val actual = arrayOfArrays.safeGetValue(
                i = -1,
                j = 4
        )

        // assert
        assertThat(actual).isNull()
    }

    @Test
    fun `when the indexes are valid - safeGetValue - value`() {
        // arrange
        val arrayOfArrays: Array<Array<String>> = arrayOf(
                arrayOf("12", "42", ""),
                arrayOf("12", "42", ""),
                arrayOf("12", "42", "")
        )

        // act
        val actual = arrayOfArrays.safeGetValue(
                i = 0,
                j = 2
        )

        // assert
        assertThat(actual).isEmpty()
    }
}