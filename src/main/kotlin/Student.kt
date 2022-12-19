//region Студент
class Student(val id: Int = 0, var name: String = "") {
    var socialized: Boolean = false
    //region Наличие социальной стипендии
    fun social() {
        socialized = true
    }
    //endregion

    //region Отсутствие социальной стипендии
    fun unsocial() {
        socialized = false
    }
    //endregion

    //region Вывод пометки о социальной стипендии
    override fun toString(): String { //вывод не toString
        return "$id) [${if (socialized) "✅" else " "}] $name "
    }
    //endregion


}
//endregion