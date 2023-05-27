package commands

import utils.CommandExecutor

/**
 * Класс HelpCommand отображает справочную информацию для всех доступных команд. *
 */
class Help(private val commandExecutor: CommandExecutor) : Command() {
    private val commandDescriptions = mapOf(
        "help" to "help : print help for available commands",
        "info" to "info : print information about the VehicleCollection (type, initialization date, number of items, etc.) in the standard output.",
        "show" to "show : print all items of the VehicleCollection in the string representation in the standard output",
        "add" to "add : add a new element to the VehicleCollection",
        "update" to "update : update the value of an element in the VehicleCollection whose id is equal to the given one",
        "remove_by_id" to "remove_by_id id : remove an item from the VehicleCollection by its id",
        "clear" to "clear : clear the VehicleCollection",
        "save" to "save : save the VehicleCollection to a file",
        "execute_script" to "execute_script file_name : read and execute a script from the specified file. The script contains commands in the same form as the user enters them in interactive mode.",
        "exit" to "exit : end the program (without saving it to a file)",
        "remove_head" to "remove_head : display the first element of the VehicleCollection and remove it",
        "add_if_max" to "add_if_max {element} : add a new element to the VehicleCollection if its value exceeds the value of the largest element in this VehicleCollection",
        "remove_greater" to "remove_greater {element} : remove all elements from the collection that exceed the specified",
        "group_counting_by_fuel_type" to "group_counting_by_fuel_type : group the elements of the collection by the value of the fuelType field, display the number of elements in each group",
        "filter_greater_than_engine_power" to "filter_greater_than_engine_power enginePower : print items with an enginePower field value greater than the specified one",
        "print_unique_type" to "print_unique_fuel_type : print the unique fuelType field values of all elements in the collection")
    override fun execute(args: List<Any>): String {
        val availableCommands = commandExecutor.getAvailableCommands()
        val helpText = StringBuilder()

        availableCommands.forEach { (commandName, _) ->
            helpText.append(commandDescriptions[commandName] ?: "Unknown command: $commandName")
            helpText.append("\n")
        }

        return helpText.toString()
    }
}
