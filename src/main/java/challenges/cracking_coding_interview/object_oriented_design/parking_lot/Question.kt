package challenges.cracking_coding_interview.object_oriented_design.parking_lot

import challenges.util.AssortedMethods.randomIntInRange

/**
 * Parking Lot: Design a parking lot using object-oriented principles.
 */
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