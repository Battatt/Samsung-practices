import java.util.*

class Knight(health: Int = 100, power: Int = 30, var defence: Int = 60, var koef: Float = 0.5f, desc: String = "Рыцарь"): Entity(health, power, desc) {

    override fun attack(): Int {
        return power
    }


    override fun getInfo(): Array<Any> {
        return arrayOf(health, power, defence, desc)
    }


    override fun heal(): Int {
        health += 15
        if (health > 100)
            health = 100
        return 1
    }

    override fun damage(decrease: Int) {
        defenceCheck()
        if (defence > 0){
            defence -= decrease
            defenceCheck()
        }
        health -= (decrease * koef).toInt()

    }

    private fun defenceCheck(): Int{
        if (defence <= 0) {
            koef = 1.0f
            return 0
        }
        koef = 0.5f
        return -1
    }

    override fun toString(): String {
        return "$desc. Здоровье: $health. Броня: $defence."
    }

    override fun pass(): Int{
        defence += 20
        if (defence > 60)
            defence = 60
        return 0
    }

    override fun ai(): Int {
        if (defence < 10) {
            return pass()
        }
        if (health < 40 || Random().nextInt(300) % 10 == 0 ){
            return heal()
        }
        return attack()
    }

    override fun full() {
        health = 100
        defence = 60
    }


}