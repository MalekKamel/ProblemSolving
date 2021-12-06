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

object Question {
    /**
     * @param args
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val lot = ParkingLot()
        var v: Vehicle? = null
        while (v == null || lot.parkVehicle(v)) {
            lot.print()
            val r = randomIntInRange(0, 10)
            v = if (r < 2) {
                Bus()
            } else if (r < 4) {
                Motorcycle()
            } else {
                Car()
            }
            print("\nParking a ")
            v.print()
            println("")
        }
        println("Parking Failed. Final state: ")
        lot.print()
    }
}