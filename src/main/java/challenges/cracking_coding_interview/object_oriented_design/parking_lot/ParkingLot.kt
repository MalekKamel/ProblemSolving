package challenges.cracking_coding_interview.object_oriented_design.parking_lot

class ParkingLot {
    private val levels: Array<Level?>
    private val NUM_LEVELS = 5

    /* Park the vehicle in a spot (or multiple spots). Return false if failed. */
    fun parkVehicle(vehicle: Vehicle): Boolean {
        for (i in levels.indices) {
            if (levels[i]!!.parkVehicle(vehicle)) {
                return true
            }
        }
        return false
    }

    fun print() {
        for (i in levels.indices) {
            print("Level$i: ")
            levels[i]!!.print()
            println("")
        }
        println("")
    }

    init {
        levels = arrayOfNulls(NUM_LEVELS)
        for (i in 0 until NUM_LEVELS) {
            levels[i] = Level(i, 30)
        }
    }
}