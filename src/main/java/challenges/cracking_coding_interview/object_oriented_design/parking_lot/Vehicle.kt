package challenges.cracking_coding_interview.object_oriented_design.parking_lot

abstract class Vehicle {
    protected var parkingSpots = ArrayList<ParkingSpot>()
    protected var licensePlate: String? = null
    var spotsNeeded = 0
    var size: VehicleSize? = null
        protected set

    /* Park vehicle in this spot (among others, potentially) */
    fun parkInSpot(spot: ParkingSpot) {
        parkingSpots.add(spot)
    }

    /* Remove car from spot, and notify spot that it's gone */
    fun clearSpots() {
        for (i in parkingSpots.indices) {
            parkingSpots[i].removeVehicle()
        }
        parkingSpots.clear()
    }

    abstract fun canFitInSpot(spot: ParkingSpot): Boolean
    abstract fun print()
}