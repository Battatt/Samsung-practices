import java.util.Random

class Wizard(health: Int = 100, power: Int = 15, var mana: Int = 100, desc: String = "Маг"): Entity(health, power, desc) {

    override fun attack(): Int {
        if (manaCheck(10)) {
            mana -= 10
            return power
        }
        return pass()
    }

    override fun damage(decrease: Int) {
        health -= decrease
    }

    override fun getInfo(): Array<Any> {
        return arrayOf(health, power, mana, desc)
    }

    override fun toString(): String {
        return "$desc. Здоровье: $health. Мана: $mana."
    }

    override fun heal(): Int {
        if (manaCheck(20)) {
            mana -= 20
            health += 25
            if (health > 100)
                health = 100
            return 1
        }
        return pass()
    }


    fun manaCheck(decrease: Int):Boolean {
        return decrease <= mana
    }

    override fun pass(): Int{
        mana += 15
        if (mana > 100)
            mana = 100
        return 0
    }

    override fun ai(): Int {
        if (mana < 10)
            return pass()
        if (health <= 40 || Random().nextInt(150) % 5 == 0)
            return heal()
        return attack()
    }

    override fun full() {
        health = 100
        mana = 100
    }



}