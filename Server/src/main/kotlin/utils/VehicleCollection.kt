package utils

import data.*
import java.time.LocalDate
import java.util.PriorityQueue
import kotlinx.serialization.builtins.ListSerializer

/**
 * Класс, отвечающий за управление коллекцией транспортных средств VehicleCollection. *
 */
class VehicleCollection(private val fileName: String) {
     private val vehicleQueue = PriorityQueue<Vehicle>()


    init {
        loadFromFile()
    }
    private fun loadFromFile() {
        try {
            val vehicleList = JsonUtil.loadFromFile(fileName, ListSerializer(Vehicle.serializer()))
            vehicleList?.let { vehicleQueue.addAll(it) }
        } catch (e: kotlinx.serialization.SerializationException) {
            println("Error: Failed to load data from file. The file might be empty or contain invalid content. Starting with an empty VehicleCollection.")
        }
    }



    fun saveToFile() {
        JsonUtil.saveToFile(vehicleQueue.toList(), fileName, ListSerializer(Vehicle.serializer()))
    }

    companion object {

        fun fromFile(fileName: String): VehicleCollection {
            return VehicleCollection(fileName)
        }
    }


    fun add(vehicle: Vehicle) {
        vehicleQueue.add(vehicle)
    }


    fun removeById(id: Int): Boolean {
        val initialSize = vehicleQueue.size
        vehicleQueue.removeIf { it.id == id }
        return vehicleQueue.size < initialSize
    }


    fun clear() {
        vehicleQueue.clear()
    }

    fun show(): List<Vehicle> {
        return vehicleQueue.toList()
    }

     fun size(): Int {
        return vehicleQueue.size
    }


    private fun getCreationDate(): LocalDate {
        return LocalDate.now()
    }

    fun addIfMax(vehicle: Vehicle): Boolean {
        val maxVehicle = vehicleQueue.maxWithOrNull(compareBy { it.id })
        if (maxVehicle == null || compareBy<Vehicle> { it.id }.compare(maxVehicle, vehicle) < 0) {
            vehicleQueue.add(vehicle)
            return true
        }
        return false
    }

    fun removeHead(): Vehicle? {
        return vehicleQueue.poll()
    }


    fun getInfo(): String {
        return "VehicleCollection type: ${vehicleQueue::class.simpleName}\n" +
                "Initialization date: ${getCreationDate()}\n" +
                "Number of elements: ${size()}"
    }


    fun update(id: Int, newVehicle: Vehicle): Boolean {
        val vehicle = vehicleQueue.find { it.id == id }
        return if (vehicle != null) {
            val updatedVehicle = Vehicle(
                id = vehicle.id,
                name = newVehicle.name,
                coordinates = newVehicle.coordinates,
                creationDate = vehicle.creationDate,
                enginePower = newVehicle.enginePower,
                distanceTravelled = newVehicle.distanceTravelled,
                type = newVehicle.type,
                fuelType = newVehicle.fuelType
            )

            vehicleQueue.remove(vehicle)
            vehicleQueue.add(updatedVehicle)

            true
        } else {
            false
        }
    }
    fun removeGreater(vehicle: Vehicle): Int {
        val iterator = vehicleQueue.iterator()
        var count = 0
        while (iterator.hasNext()) {
            val currentVehicle = iterator.next()
            if (currentVehicle >= vehicle) {
                iterator.remove()
                count++
            }
        }
        return count
    }



}
