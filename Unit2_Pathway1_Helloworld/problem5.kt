package Unit2_Pathway1_Helloworld

fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        var exception:String="Doesn't have a referrer."
        if(referrer!=null){
            exception="Has a referrer named ${referrer.name}, who likes to ${referrer.hobby}."
        }
        println("Name: $name\n" +
                "Age: $age\n" +
                "Likes to $hobby. $exception")
    }
}