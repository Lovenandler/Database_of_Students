//region Список студентов
class StudentList : SocialList() {

    //region Добавление студента
    fun addStudent(name: String) {
        val student = Student(nextId, name)
        nextId++
        itemList.add(student)
    }
    fun removeStudent(id: Int){
        val student = itemList.find { it.id == id } ?: throw Error()
        itemList.remove(student)
    }
    fun editStudent(id: Int, name: String){
        itemList.find { it.id == id } ?: throw Error()
        val studentEdit = Student(id, name)
        itemList[id-1] = studentEdit
    }
    fun sortStudent() {
        println(itemList.sortedBy { it.name })
    }
    //endregion
    fun searchStudent(name: String) {
        val student = itemList.find { it.name == name } ?: throw Error()
        println(student)
    }

    //region Функция присвоения социальной стипендии
    fun markStudentSoc(id: Int, soc: Boolean) {
        val studentSoc = itemList.find { it.id == id } ?: throw Error()
        if (soc)
            studentSoc.social()
        else
            studentSoc.unsocial()
    }
    //endregion
}
//endregion