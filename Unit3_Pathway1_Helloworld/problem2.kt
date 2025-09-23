package Unit3_Pathway1_Helloworld


enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING
}

data class Event1(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)

fun main(args: Array<String>) {
    val event = Event1("Study Kotlin", "Commit to studying Kotlin at least 15 minutes per day.", Daypart.MORNING, 15)
    println(event)
}