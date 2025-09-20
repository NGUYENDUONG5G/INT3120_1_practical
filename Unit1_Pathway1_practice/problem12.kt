fun main(args: Array<String>) {
    println("Use the val keyword when the value doesn't change.")
    println("Use the var keyword when the value can change.")
    println("When you define a function, you define the parameters that can be passed to it.")
    println("When you call a function, you pass arguments for the parameters.")

    println("New chat message from a friend")

    val discountPercentage = 20
    val item = "Google Chromecast"
    val offer = "Sale  - Up to $discountPercentage% discount off $item! Hurry Up!"

    println(offer)

    val numberOfAdults = 20
    val numberOfKids = 30
    val total = numberOfAdults + numberOfKids
    println("The total party size is: $total")

    val firstNumber = 10
    val secondNumber = 5
    val result = firstNumber + secondNumber
    println("$firstNumber + $secondNumber = $result")
}
