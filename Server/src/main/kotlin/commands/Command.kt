package commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.VehicleCollection

/**
 * Абстрактный класс, опрежеляющий команды.
 */

    abstract class Command: KoinComponent {
        val vehicleCollection: VehicleCollection by inject()
        abstract fun execute(args: List<Any>): String
        open fun readArguments(input: () -> String): List<Any>{
            return emptyList()
        }
    }