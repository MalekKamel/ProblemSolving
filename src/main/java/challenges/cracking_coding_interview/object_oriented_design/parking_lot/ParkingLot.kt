package challenges.cracking_coding_interview.object_oriented_design.parking_lot

import challenges.util.AssortedMethods.randomIntInRange
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.Vehicle
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.ParkingSpot
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.VehicleSize
import kotlin.jvm.JvmStatic
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.ParkingLot
import challenges.util.AssortedMethods
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.Bus
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.Motorcycle
import challenges.cracking_coding_interview.object_oriented_design.parking_lot.Car

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