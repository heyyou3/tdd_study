package tdd_study

import org.junit.Assert.*
import org.junit.Test

class MoneyTest {
    @Test
    fun testMultiplication() {
        val five = Money.dollar(5)
        assertEquals(Money.dollar(10), five.times(2))
        assertEquals(Money.dollar(15), five.times(3))
    }

    @Test
    fun testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)))
        assertFalse(Money.dollar(5).equals(Money.dollar(6)))
        assertFalse(Money.franc(5).equals(Money.dollar(5)))
    }

    @Test
    fun testCurrency() {
        assertEquals("USD", Money.dollar(1).currency())
        assertEquals("CHF", Money.franc(1).currency())
    }

    @Test
    fun testSimpleAddition() {
        val five = Money.dollar(5)
        val sum = five.plus(five)
        val bank = Bank()
        val reduced = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(10), reduced)
    }

    @Test
    fun testPlusReturnSums() {
        val five = Money.dollar(5)
        val result = five.plus(five) as Sum
        assertEquals(five, result.augend)
        assertEquals(five, result.addend)
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(Money.dollar(3), Money.dollar(4))
        val bank = Bank()
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(7), result)
    }

    @Test
    fun testReduceMoney() {
        val bank = Bank()
        val result = bank.reduce(Money.dollar(1), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun testReduceMoneyDifferentCurrency() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun testIdentityRate() {
        assertEquals(1, Bank().rate("USD", "USD"))
    }

    @Test
    fun testMixedAddition() {
        val fiveBucks: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result: Money = bank.reduce(fiveBucks.plus(tenFrancs), "USD")
        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun testSumPlusMoney() {
        val fiveBucks = Money.dollar(5)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum: Expression = Sum(fiveBucks, Money.franc(10)).plus(fiveBucks)
        val result: Money = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(15), result)
    }

    @Test
    fun testSumTimes() {
        val fiveBucks: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum: Expression = Sum(fiveBucks, tenFrancs).times(2)
        val result: Money = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(20), result)
    }
}
