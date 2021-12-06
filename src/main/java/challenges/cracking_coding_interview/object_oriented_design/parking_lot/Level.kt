package challenges.cracking_coding_interview.object_oriented_design.parking_lot

/* Represents a level in a parking garage */
class Level(private val floor: Int, numberSpots: Int) {
    private val spots: Array<ParkingSpot?>
    private var availableSpots = 0 // number of free spots
    fun availableSpots(): Int {
        return availableSpots
    }

    /* Try to find a place to park this vehicle. Return false if failed. */
    fun parkVehicle(vehicle: Vehicle): Boolean {
        if (availableSpots() < vehicle.spotsNeeded) {
            return false
        }
        val spotNumber = findAvailableSpots(vehicle)
        return if (spotNumber < 0) {
            false
        } else parkStartingAtSpot(spotNumber, vehicle)
    }

    /* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */
    private fun parkStartingAtSpot(spotNumber: Int, vehicle: Vehicle): Boolean {
        vehicle.clearSpots()
        var success = true
        for (i in spotNumber until spotNumber + vehicle.spotsNeeded) {
            success = success and spots[i]!!.park(vehicle)
        }
        availableSpots -= vehicle.spotsNeeded
        return success
    }

    /* find a spot to park this vehicle. Return index of spot, or -1 on failure. */
    private fun findAvailableSpots(vehicle: Vehicle): Int {
        val spotsNeeded = vehicle.spotsNeeded
        var lastRow = -1
        var spotsFound = 0
        for (i in spots.indices) {
            val spot = spots[i]!!
            if (lastRow != spot.row) {
                spotsFound = 0
                lastRow = spot.row
            }
            if (spot!!.canFitVehicle(vehicle)) {
                spotsFound++
            } else {
                spotsFound = 0
            }
            if (spotsFound == spotsNeeded) {
                return i - (spotsNeeded - 1)
            }
        }
        return -1
    }

    fun print() {
        var lastRow = -1
        for (i in spots.indices) {
            val spot = spots[i]!!
            if (spot.row != lastRow) {
                print("  ")
                lastRow = spot.row
            }
            spot!!.print()
        }
    }

    /* When a car was removed from the spot, increment availableSpots */
    fun spotFreed() {
        availableSpots++
    }

    companion object {
        private const val SPOTS_PER_ROW = 10
    }

    init {
        spots = arrayOfNulls(numberSpots)
        val largeSpots = numberSpots / 4
        val bikeSpots = numberSpots / 4
        val compactSpots = numberSpots - largeSpots - bikeSpots
        for (i in 0 until numberSpots) {
            var sz = VehicleSize.Motorcycle
            if (i < largeSpots) {
                sz = VehicleSize.Large
            } else if (i < largeSpots + compactSpots) {
                sz = VehicleSize.Compact
            }
            val row = i / SPOTS_PER_ROW
            spots[i] = ParkingSpot(this, row, i, sz)
        }
        availableSpots = numberSpots
    }
}