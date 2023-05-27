package exeptions

/**
 * Вызыввается если команда не обнаружена
 */

class CommandException(message: String) : Exception(message)