fun main(args: Array<String>) {
    printWeather("Tokyo", 32, 36, 10)
}
fun printWeather(city:String, lowTemperature: Int,highTemperature: Int, changeOfRain: Int){
    println("City: $city")
    println("Low temperature: $lowTemperature, High temperature: $highTemperature")
    println("Chance of rain: $changeOfRain%")
    println()
}