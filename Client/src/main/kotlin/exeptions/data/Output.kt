package exeptions.data

/**
 *объект-синглтон. Внутри этого объекта определены константы, которые могут быть использованы в других частях приложения.
 * содержаться строки(содержание) ошибок и сообщений
 */

object Output {

    const val File_Not_Found = "FileNotFound"
    const val File_Path ="Enter File Path"
    const val Invalid_DistanceTravelled = "Wrong input.Distance must be greater than 0."
    const val Invalid_EnginePower = "Wrong input. Engine power must be greater than 0."
    const val removedGreater = "Elements that grater than this element were removed"
    const val Infinity_cycle = "Infinity cycle"
    const val nothingToRemove = "there is nothing to remove"
    const val Success_script_execute = "script executed successfully"
    const val File_not_found = "File not found"
    const val Success_clear = "Collection successfully cleared"
    const val Not_max = "It is not max"
    const val Success_added = "Element successfully added"
    const val Success_save = "Collection saved successfully"
    const val Invalid_FuelType= "Invalid Fuel TypeIt can be ELECTRICITY, NUCLEAR, PLASMA or leave it empty to null value: "
    const val Enter_FuelType = "Enter Fuel Type. It can be ELECTRICITY, NUCLEAR, PLASMA or leave it empty to null value: "
    const val Invalid_VehicleType = "invalid Vehicle Type. It can be CAR, SUBMARINE, SHIP, BICYCLE, HOVERBOARD or leave it empty to null value): "
    const val Enter_VehicleType = "Enter vehicle type. It can be CAR, SUBMARINE, SHIP, BICYCLE, HOVERBOARD or leave it empty to null value): "
    const val Enter_distance_traveled = "Enter distance traveled: "
    const val Enter_engine_power ="Enter engine power: "
    const val Enter_name ="Enter name: "
    const val Enter_x = "Please, enter coordinates [x] (only INTEGER): "
    const val Enter_y = "Please, enter coordinates [y] (only LONG, !>297): "
    const val Invalid_Coordinates = "Invalid value. Please enter valid value for coordinates"
    const val Welcome  = "Hi there, here you can manage the VehicleCollection"
    const val Enter_help = "type [help] to see all commands"

}