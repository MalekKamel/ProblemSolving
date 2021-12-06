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

class ParkingSpot(private val level: Level, val row: Int, val spotNumber: Int, val size: VehicleSize) {
    private var vehicle: Vehicle? = null
    val isAvailable: Boolean
        get() = vehicle == null

    /* Checks if the spot is big enough for the vehicle (and is available). This compares
	 * the SIZE only. It does not check if it has enough spots. */
    fun canFitVehicle(vehicle: Vehicle): Boolean {
        return isAvailable && vehicle.canFitInSpot(this)
    }

    /* Park vehicle in this spot. */
    fun park(v: Vehicle): Boolean {
        if (!canFitVehicle(v)) {
            return false
        }
        vehicle = v
        vehicle!!.parkInSpot(this)
        return true
    }

    /* Remove vehicle from spot, and notify level that a new spot is available */
    fun removeVehicle() {
        level.spotFreed()
        vehicle = null
    }

    fun print() {
        if (vehicle == null) {
            if (size == VehicleSize.Compact) {
                print("c")
            } else if (size == VehicleSize.Large) {
                print("l")
            } else if (size == VehicleSize.Motorcycle) {
                print("m")
            }
        } else {
            vehicle!!.print()
        }
    }
}