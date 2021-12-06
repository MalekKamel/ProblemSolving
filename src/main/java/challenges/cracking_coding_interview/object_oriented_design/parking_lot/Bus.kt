package challenges.cracking_coding_interview.object_oriented_design.parking_lot

class Bus : Vehicle() {
    override fun canFitInSpot(spot: ParkingSpot): Boolean {
        return spot.size == VehicleSize.Large
    }

    override fun print() {
        print("B")
    }

    init {
        spotsNeeded = 5
        size = VehicleSize.Large
    }
}