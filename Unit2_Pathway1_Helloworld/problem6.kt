package Unit2_Pathway1_Helloworld

open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(isScreenLightOn: Boolean = false, var isFoldable: Boolean = false) : Phone(isScreenLightOn) {
    override fun switchOn() {
        if (isFoldable) {
            isScreenLightOn = true
        }
    }
}