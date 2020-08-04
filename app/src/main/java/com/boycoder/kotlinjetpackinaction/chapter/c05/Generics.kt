package com.boycoder.kotlinjetpackinaction.chapter.c05

/**
 * Created by zhu.tao on 2020/7/29.
 */

fun main() {
    testGenericsClass()
    testGenericsFunction()
    testGenericsInvariant()
    testGenericsDeclarationSiteCovariant()
    testGenericsDeclarationSiteContravariant()

    testGenericsUseSiteCovariant(University<FemaleStudent>("女子大学"))
    testGenericsUseSiteContravariant(University<Student>("普通大学"))
}

/**
 * 测试：泛型类
 */
fun testGenericsClass() {

    // 定义
    class TV()
    class Controller<T>() {
        fun turnOn(obj: T) {
            println("turnOn")
        }

        fun turnOff(obj: T) {
            println("turnOff")
        }
    }

    // 测试
    val tvController: Controller<TV> = Controller<TV>()
    val tv = TV()

    tvController.turnOn(tv)
    tvController.turnOff(tv)
}

/**
 * 测试：泛型函数
 */
fun testGenericsFunction() {

    // 定义
    class TV()

    fun <T> turnOn(obj: T) {
        println("function turnOn")
    }

    fun <T> turnOff(obj: T) {
        println("function turnOff")
    }

    // 测试
    val tv = TV()
    turnOn<TV>(tv)
    turnOff<TV>(tv)
}


/**
 * 泛型不变性
 */
fun testGenericsInvariant() {
    open class Student()
    class FemaleStudent : Student()

    class University<T>(val name: String) {
        fun get(): T? { println("Invariant get"); return null }
        fun put(student: T?) { println("Invariant put") }
    }

    // 测试
    lateinit var university: University<Student>
    university = University<Student>("某大学")
    val student: Student? = university.get()
    university.put(student)

    // 报错
//     university = University<FemaleStudent>("女子大学")


    val sister: FemaleStudent = FemaleStudent()
    // 报错！！
//    val inUniversity: University<FemaleStudent> = University<Student>("普通大学")
//    inUniversity.put(sister)
}


/**
 * 声明处协变 (Declaration-Site-Covariant)
 */
fun testGenericsDeclarationSiteCovariant() {
    open class Student()
    class FemaleStudent : Student()

    //              here
    //                ↓
    class University<out T>(val name: String) {
        fun get(): T? { println("Covariant get"); return null }

        /*fun put(student: T?){ println("Covariant put") }*/
    }

    lateinit var university: University<Student>
    university = University<FemaleStudent>("女子大学")
    val student: Student? = university.get()
}

/**
 * 声明处协变 (Declaration-Site-Contravariant)
 */
fun testGenericsDeclarationSiteContravariant() {
    open class Student()
    class FemaleStudent : Student()

    //               here
    //                ↓
    class University<in T>(val name: String) {
        /*fun get(): T? { println("Contravariant get"); return null }*/

        fun put(student: T?) { println("Contravariant put") }
    }

    val sister: FemaleStudent = FemaleStudent()
    lateinit var femaleUniversity: University<FemaleStudent>
    femaleUniversity = University<Student>("普通大学")
    femaleUniversity.put(sister)
}


open class Student()
class FemaleStudent : Student()

class University<T>(val name: String) {
    fun get(): T? { println("Use Site Covariant get"); return null }
    fun put(student: T?) { println("Use Site Covariant put") }
}

/**
 * 使用处协变 (Use-Site-Covariant)
 */
fun testGenericsUseSiteCovariant(university: University<out Student>) {
    val femaleStudent: Student? = university.get()

    // 报错
    // university.put(femaleStudent)
}

/**
 * 使用处逆变 (Use-Site-Contravariant)
 */
fun testGenericsUseSiteContravariant(universityIn: University<in FemaleStudent>) {
    universityIn.put(FemaleStudent())

    // 报错
    // val femaleStudent: FemaleStudent? = universityIn.get()
}