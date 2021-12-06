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

enum class VehicleSize {
    Motorcycle, Compact, Large
}