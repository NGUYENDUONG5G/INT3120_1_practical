package Unit2_Pathway1_Helloworld

fun main() {
    var initialMeasurement: Double=27.0
    var initialUnit: String="Celsius"
    var finalUnit: String="Fahrenheit"
    printFinalTemperature(initialMeasurement,initialUnit,finalUnit,{9/5* (it) + 32})
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}