package utils

import data.*
import exeptions.ValidationException
import exeptions.data.*
import java.time.LocalDateTime
import java.util.*

/**
 * Класс, считываюзий вводимые значения пользователя
 */
class Reader(private val readLineFn: () -> String, private val validator: Validator) {


    private fun readName(): String {
        while (true) {
            print(Output.Enter_name)
            val name = readLineFn()
            try {
                validator.validateName(name)
                return name
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }


    private fun readCoordinates(): Coordinates {
        while (true) {
            try {
                print(Output.Enter_x)
                val x = readLineFn().trim().toInt()
                print(Output.Enter_y)
                val y = readLineFn().trim().toLong()
                val coordinates = Coordinates(x, y)
                validator.validateCoordinates(coordinates)
                return coordinates
            } catch (e: NumberFormatException) {
                println(Output.Invalid_Coordinates)
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }



    private fun readEnginePower(): Int {
        while (true) {
            print(Output.Enter_engine_power)
            val enginePower = try {
                readLineFn().toInt()
            } catch (e: NumberFormatException) {
                println(Output.Invalid_EnginePower)
                continue
            }
            try {
                validator.validateEnginePower(enginePower)
                return enginePower
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }

    private fun readDistanceTravelled(): Double {
        while (true) {
            print(Output.Enter_distance_traveled)
            val distanceTravelled = try {
                readLineFn().toDouble()
            } catch (e: NumberFormatException) {
                println(Output.Invalid_DistanceTravelled)
                continue
            }
            try {
                validator.validateDistanceTravelled(distanceTravelled)
                return distanceTravelled
            } catch (e: ValidationException) {
                println(e.message)
            }
        }
    }



    private fun readVehicleType(): VehicleType? {
        print(Output.Enter_VehicleType)
        while (true) {
            val input = readLineFn().trim()
            if (input.isEmpty()) {
                return null
            }

            return try {
                VehicleType.valueOf(input.uppercase(Locale.getDefault()))
            } catch (e: IllegalArgumentException) {
                print(Output.Invalid_VehicleType)
                continue
            }
        }
    }
    private fun readFuelType (): FuelType? {
        print(Output.Enter_FuelType)
        while (true) {
            val input = readLineFn().trim()
            if (input.isEmpty()) {
                return null
            }

            return try {
                FuelType .valueOf(input.uppercase(Locale.getDefault()))
            } catch (e: IllegalArgumentException) {
                print(Output.Invalid_FuelType)
                continue
            }
        }
    }




    fun readVehicle(
        id: Int = IdGenerator.generateUniqueId(),
        creationDate: LocalDateTime = LocalDateTime.now()
    ): Vehicle {
        val name = readName()
        val coordinates = readCoordinates()
        val enginePower = readEnginePower()
        val distanceTravelled = readDistanceTravelled()
        val type= readVehicleType()
        val fuelType = readFuelType()
        return Vehicle(
            id,
            name,
            coordinates,
            creationDate,
            enginePower,
            distanceTravelled,
            type,
            fuelType
        )
    }
}



