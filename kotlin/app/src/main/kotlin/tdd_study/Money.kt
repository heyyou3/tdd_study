package tdd_study

open class Money(open val amount: Int, open val currency: String) : Expression {

    companion object {
        fun dollar(amount: Int): Money {
            return Money(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
        }
    }

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    fun currency(): String {
        return currency
    }

    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)

    }

    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency().equals(money.currency())
    }
}
