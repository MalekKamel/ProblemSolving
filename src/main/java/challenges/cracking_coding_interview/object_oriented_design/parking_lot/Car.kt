package challenges.cracking_coding_interview.object_oriented_design.parking_lot

class Car : Vehicle() {
    override fun canFitInSpot(spot: ParkingSpot): Boolean {
        return spot.size == VehicleSize.Large || spot.size == VehicleSize.Compact
    }

    override fun print() {
        print("C")
    }

    init {
        spotsNeeded = 1
        size = VehicleSize.Compact
    }
}