package commands


import utils.CommandExecutor
import java.util.*

/**
 * Класс ExecuteScript читает и выполняет команды из указанного файла.
 *
 */
class ExecuteScript(
    private val commandExecutor: CommandExecutor,
) : Command() {
    private val stack: Stack<String> = Stack()



    override fun execute(args: List<Any>): String {
        if (args.isEmpty() || args[0] !is String) {
            return "Script is not provided or has an incorrect format."
        }

        val script = args[0].toString()
        val lines = script.lines().iterator()
        val results = StringBuilder()

        if (stack.contains(script)) return "Error: Recursion"

        stack.add(script)

        while (lines.hasNext()) {
            val line = lines.next().trim()
            if (line.isNotBlank()) {
                val commandResult = executeLine(line, lines::next)
                if (commandResult != null) {
                    results.append(commandResult).append("\n")
                }
            }
        }
        stack.remove(script)

        return results.append("Script executed successfully").toString()
    }

    private fun executeLine(line: String, input: () -> String): String? {
        val commandParts = line.split(" ", limit = 2)
        val commandName = commandParts[0]

        val command = commandExecutor.getCommand(commandName)
            ?: throw IllegalArgumentException("Unknown command: $commandName")

        val initialArg = commandParts.getOrElse(1) { "" }
        val args = readArguments(command, input, initialArg)

        return when {
            command is ExecuteScript -> {
                command.execute(args)
            }
            line.isNotBlank() -> {
                command.execute(args)
            }
            else -> null // Return null if the input is empty (ignores empty lines)
        }
    }

    private fun readArguments(command: Command, input: (() -> String)?, initialArg: String): List<Any> {
        val args = mutableListOf<Any>()
        if (initialArg.isNotBlank()) {
            args.add(initialArg)
        }
        return args
    }
}
