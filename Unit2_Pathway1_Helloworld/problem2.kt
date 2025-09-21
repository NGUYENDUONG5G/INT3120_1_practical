package Unit2_Pathway1_Helloworld

fun main() {
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    var res:Int
    if(age>100) res=-1
    else if (age>60) res=20
    else if (age>=13)
        if(isMonday) res=25
        else res =30
    else res=15

    return res
}