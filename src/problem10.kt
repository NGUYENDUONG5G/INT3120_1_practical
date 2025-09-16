fun main(args: Array<String>) {
    val timeSpentToday = 200
    val timeSpentYesterday = 220
    println(compareTimeTo(timeSpentToday, timeSpentYesterday))
}

fun compareTimeTo(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
    return timeSpentToday > timeSpentYesterday
}