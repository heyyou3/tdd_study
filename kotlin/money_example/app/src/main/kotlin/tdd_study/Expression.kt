package tdd_study

interface Expression {
    fun plus(addend: Expression): Expression
    fun reduce(bank: Bank, to: String): Money
    fun times(multiplier: Int): Expression
}
