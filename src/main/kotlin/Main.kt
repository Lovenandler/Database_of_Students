/*База данных студентов колледжа. Поля: ФИО, группа, признак бюд-
жетности, стипендия (нет/обычная/повышенная), флаг наличия со-
циальной стипендии, дата рождения.*/
//если признак бюджетности = null, то соц стипендии и стипендии быть не может
//SOLID
//D - dependency iversion - > injection
//D - надо писать в конструкторе класса
//interface
//класс реализующий интерфейс


//отделить способ вывода от общей логики

//interface DataBase {
//    fun connect()
//}
//
//class SQL: DataBase {
//    override fun connect() {
//        TODO("Not yet implemented")
//    }
//}
//
//class PostgreSQL: DataBase {
//    override fun connect() {
//        TODO("Not yet implemented")
//    }
//
//}
//
//class DBConnection(private val conn: DataBase){
//
//    fun s() {
//        conn.connect()
//    }
//
//}


//sql()
//

fun main() {
    val ui = MenuUI()

    while (true) {
        ui.displayMenu()
        ui.doMenuOperation()
    }
}

//region Ввод данных о студенте
fun getUserString(message: String): String {
    var finished = false
    var name = ""
    while (!finished) {
        print(message)
        print("> ")
        val inputName = readLine()
        if (inputName.isNullOrBlank()) {
            error("Введите ФИО")
        } else {
            name = inputName
            finished = true
        }
    }
    return name
}
//endregion