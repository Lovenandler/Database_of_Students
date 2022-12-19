typealias menuFunction = () -> Unit //typealias - альтернативное название типа

//region Внешний вид меню
class MenuUI { //конструктор  studentlist передавать
    private val studentList = StudentList()

    //region Пункты основного меню
    private val studentMenu: Menu = Menu( //является ли нарушением dry
        arrayListOf(
            MenuOperation("Показать всех студентов") {
                studentList.display(ListFilter.All)
            },
            MenuOperation("Показать студентов с социальной стипендией") {
                studentList.display(ListFilter.Social)
            },
            MenuOperation("Показать студентов без социальной стипендии") {
                studentList.display(ListFilter.Unsocial)
            },
            MenuOperation("Добавить студента") {
                studentList.addStudent(getUserString("ФИО студента"))
            },
            MenuOperation("Изменить студента") {
                studentList.editStudent(getUserString("Номер студента").toInt(), getUserString("ФИО студента"))
            },
            MenuOperation("Удалить студента") {
                studentList.removeStudent(getUserString("Номер студента").toInt())
            },
            MenuOperation("Присвоить социальную стипендию") {
                markStudentSoc(true)
            },
            MenuOperation("Снять с социальной стипендии") {
                markStudentSoc(false)
            },
            MenuOperation("Поиск") {
                studentList.searchStudent(getUserString("ФИО студент"))
            },
            MenuOperation("Сортировка по имени") {
                studentList.sortStudent()
            },
            MenuOperation("Выход") {
                clear()
                menu = mainMenu // Switch back to main menu
            },
        )
    )
    //endregion


    //region Начальный экран
    private val mainMenu = Menu(arrayListOf(
        MenuOperation("База студентов") {
            studentList.display(ListFilter.All)
            menu = studentMenu // Switch Menus
        }
    ))
    //endregion

    private var menu: Menu = mainMenu

    //region Показ меню
    fun displayMenu() {
        print("Выберите опцию: \n")
        print(menu.render())
    }
    //endregion

    //region Выполнение операции меню
    fun doMenuOperation() {
        try {
            val choiceNum = getUserNumber()
            menu.execute(choiceNum)
        } catch (e: Error) {
            error("Неккоректный ввод")
            return
        }
    }
    //endregion


    //region Назначение или лишение социальной стипендии
    private fun markStudentSoc(social: Boolean) {
        val prompt =
            "Какому студенту ${if (social) "назначить социальную стипендию" else "убрать социальную стипендию"}? (Введите номер студента)\n>"
        val studentId = getUserNumber(prompt)
        try {
            studentList.markStudentSoc(studentId, social)
        } catch (e: Error) {
            error("Неккоректный ввод")
        }
    }
    //endregion


    //region Получить номер студента от пользователя
    private fun getUserNumber(message: String? = null): Int {

        var soc = false
        var choiceNum = 0
        while (!soc) {
            if (message != null) {
                print(message)
            }
            print("> ")
            val choice = readLine()
            if (choice.isNullOrBlank()) {
                error("Введите номер студента")
            } else {
                try {
                    choiceNum = choice.toInt()
                    soc = true
                } catch (e: java.lang.NumberFormatException) {
                    error("Неккоректный ввод")
                }
            }
        }
        return choiceNum
    }
    //endregion

    //region Обработка ошибки
    //вынести в отдельный класс Error
    private fun error(msg: String) {
        print("\nERROR: $msg\n")
    }
    //endregion

    //region Очистка экрана
    //отдельный класс Clear
    private fun clear() {
        for (i in 0..20) {
            print("\n")
        }
    }
    //endregion

}
//endregion

//region Меню
class Menu(
    private val operations: ArrayList<MenuOperation> //список массивов классов операций с меню
) {
    init {
        MenuOperation.nextId = 1
    }

    //region Функция выполнения пункта меню
    fun execute(operationId: Int) { //вызов функции пункта меню в зависимости от идентификатора
        val operation: MenuOperation = operations.find { it.id == operationId } ?: throw Error()
        operation.menuFunction.invoke()
    }
    //endregion

    //region Настройка вывода пунктов меню
    fun render(): String {
        var menuOutput = ""
        for (op in operations) {
            menuOutput += "${op.id}. ${op.description} \n"
        }
        return menuOutput
    }
    //endregion
}
//endregion

//region Операции меню
class MenuOperation(val description: String, val menuFunction: menuFunction) {

    var id: Int = nextId++

    companion object {
        var nextId = 1
    }
}
//endregion