package challenges.cracking_coding_interview.object_oriented_design.parking_lot

class Motorcycle : Vehicle() {
    override fun canFitInSpot(spot: ParkingSpot): Boolean {
        return true
    }

    override fun print() {
        print("M")
    }

    init {
        spotsNeeded = 1
        size = VehicleSize.Motorcycle
    }
}