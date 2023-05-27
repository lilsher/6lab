package data

import kotlinx.serialization.Serializable

/**
 *Дата класс содержащий значения координат 'x' и 'y'
 */

@Serializable
data class Coordinates(
    val x: Int,//Поле не может быть null
    val y: Long//Максимальное значение поля: 297, Поле не может быть null
)