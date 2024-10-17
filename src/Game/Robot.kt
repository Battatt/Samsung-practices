import java.util.*

class Robot(health: Int = 100, power: Int = 10, var battery: Int = 100, var k: Int = 3, desc: String = "Робот"): Entity(health, power, desc) {

    override fun attack(): Int {
        batteryChecker()
        battery -= 20
        batteryChecker()
        return power * k

    }

    override fun damage(decrease: Int) {
        health -= decrease
    }

    override fun getInfo(): Array<Any> {
        return arrayOf(health, power, battery, desc)
    }

    override fun toString(): String {
        return "$desc. Здоровье: $health. Батарея: $battery."
    }


    override fun heal(): Int {
        batteryChecker()
        health += 35
        battery -= 15
        if (health > 100)
            health = 100
        batteryChecker()
        return 1
    }

    fun batteryChecker(): Int{
        if (battery <= 0) {
            k = -1
            health = 0
            return -1
        }
        if (battery <= 50) {
            k = 1
            return 0
        }
        if (battery <= 75){
            k = 2
            return 0
        }
        k = 3
        return 0
    }

    override fun pass(): Int{
        batteryChecker()
        battery += 20
        if (battery > 100)
            battery = 100
        return 0
    }

    override fun ai(): Int {
        if (Random().nextInt(15) % 3 == 0)
            return pass()
        if (battery < 30 || Random().nextInt(150) % 3 == 0)
            return pass()
        if (health <= 40 || Random().nextInt(150) % 5 == 0)
            return heal()
        return attack()
    }

    override fun full() {
        health = 100
        battery = 100
    }

}