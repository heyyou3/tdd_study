package tdd_study

class Bank {
    val rates: MutableMap<Pair, Int> = HashMap()

    fun rate(from: String, to: String): Int {
        if (from.equals(to)) return 1
        return rates.get(Pair(from, to)) ?: throw IllegalArgumentException("Unregistered rate: $from to $to")
    }

    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }

    fun addRate(from: String, to: String, rate: Int) {
        rates.put(Pair(from, to), rate)
    }
}
