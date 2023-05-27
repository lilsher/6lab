import utils.Validator
import client.*
import exeptions.data.Output
import utils.Reader

fun main() {
    // Initialize required instances

    println(Output.Welcome)
    println(Output.Enter_help)
    val validator = Validator()
    val reader = Reader({ readlnOrNull() ?: throw IllegalStateException("No input provided") }, validator)

    // Initialize the ClientManager
    val clientManager = ClientManager("localhost", 12342) // assuming "localhost" and 12345 are your host and port

    // Initialize the command interpreter
    val commandInterpreter = CommandInterpreter(reader, clientManager)
    while (true) {
        print("> ")
        val input = readlnOrNull() ?: continue
        if (input.lowercase() == "exit") {
            break
        }
        val commandData = commandInterpreter.interpret(input)
        val task = Task(commandData)

        val response = task.execute(clientManager)
        println(response.message)
    }
}