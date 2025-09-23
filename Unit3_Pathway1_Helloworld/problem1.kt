package Unit3_Pathway1_Helloworld

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: String,
    val durationInMinutes: Int,
)

fun main(args: Array<String>) {
    val event = Event("Study Kotlin", "Commit to studying Kotlin at least 15 minutes per day.", "Evening", 15)
    println(event)
}