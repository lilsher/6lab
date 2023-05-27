package client

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import utils.Reader
import java.io.File

class CommandInterpreter(private val reader: Reader, private val clientManager: ClientManager) {
    private var supportedCommands: List<CommandData> = emptyList()

    init {
        updateSupportedCommands()
    }

    private fun updateSupportedCommands() {
        val response = clientManager.sendCommandAndGetResponse(CommandData("getCommands"))
        supportedCommands = response.message.split(";")
            .map { it.split(",") }
            .map { CommandData(it[0], arguments = it.drop(1).chunked(2).map { arg -> CommandArgument(arg[0], arg[1]) }) }
    }


    private fun requestArguments(arguments: List<CommandArgument>) {
        arguments.forEach { argument ->
            print("Enter ${argument.name} (${argument.type}): ")
            argument.value = readlnOrNull()
        }
    }

    fun interpret(input: String): CommandData {
        val commandParts = input.split(" ")
        val commandName = commandParts[0]
        val parameters = commandParts.drop(1)

        if (commandName == "add" || commandName == "add_if_max" || commandName == "remove_greater") {
            val serializedVehicle = getSerializedVehicle()
            return CommandData(commandName, listOf(CommandArgument("Vehicle", "Vehicle", serializedVehicle)))
        } else if (commandName == "update") {
            if (parameters.isEmpty()) {
                throw IllegalArgumentException("ID is required for the update command.")
            }
            val id = parameters[0]
            val serializedVehicle = getSerializedVehicle()
            return CommandData(commandName, listOf(CommandArgument("id", "Int", id), CommandArgument("Vehicle", "Vehicle", serializedVehicle)))
        }
        if (commandName == "remove_by_id") {
            if (parameters.isEmpty()) {
                throw IllegalArgumentException("ID is required for the remove_by_id command.")
            }
            val id = parameters[0]
            return CommandData(commandName, listOf(CommandArgument("id", "Int", id)))
        }
        if (commandName == "execute_script") {
            if (parameters.isEmpty()) {
                throw IllegalArgumentException("File name is required for the execute_script command.")
            }
            val fileName = parameters[0]
            val script = File(fileName).readText()
            return CommandData(commandName, listOf(CommandArgument("script", "String", script)))
        }
        if (commandName == "filter_greater_than_engine_power") {
            if (parameters.isEmpty()) {
                throw IllegalArgumentException("Engine power is required for the filter_greater_than_engine_power command.")
            }
            val enginePower = parameters[0]
            return CommandData(commandName, listOf(CommandArgument("enginePower", "Int", enginePower)))
        }


        return CommandData(commandName, parameters.map { CommandArgument(it, it, it) })
    }


    private fun getSerializedVehicle(): String {
        val vehicle = reader.readVehicle()
        return Json.encodeToString(vehicle)
    }

}



