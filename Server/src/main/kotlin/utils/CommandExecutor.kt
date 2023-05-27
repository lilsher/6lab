package utils

import commands.*
import java.util.*

/**
 * Класс, отвечающий за управление и выполнение команд.
 */
class CommandExecutor {
    private val commandMap: MutableMap<String, Command> = mutableMapOf()

    init {
        commandMap["help"] = Help(this)
        commandMap["info"] = Info()
        commandMap["show"] = Show()
        commandMap["add"] = Add()
        commandMap["update"] = Update()
        commandMap["remove_by_id"] = RemoveById()
        commandMap["clear"] = Clear()
        commandMap["save"] = Save()
        commandMap["remove_greater"] = RemoveGreater()
        commandMap["execute_script"] = ExecuteScript(this)
        commandMap["group_counting_by_fuel_type"] = GroupCountingByFuelType()
        commandMap["filter_greater_than_engine_power"] = FilterGreaterThanEnginePower()
        commandMap["remove_head"] = RemoveHead()
        commandMap["add_if_max"] = AddIfMax()
        commandMap["print_unique_type"] = PrintUniqueFuelType()
    }



    fun getCommand(name: String): Command? {
        return commandMap[name.lowercase(Locale.getDefault())]
    }

    fun getAvailableCommands(): Map<String, Command> {
        return commandMap
    }
}