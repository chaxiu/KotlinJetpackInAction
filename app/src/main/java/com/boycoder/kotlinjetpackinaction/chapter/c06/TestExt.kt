package com.boycoder.kotlinjetpackinaction.chapter.c06

/**
 * Created by zhu.tao on 2020/7/31.
 */


fun String.log() {
    println(this)
}

val String?.isNullOrBlank: Boolean
    get() = this == null || this.isBlank()

/**
 * ==========================================
 */

class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}

class Test(val host: Host, val port: Int) {
    fun printPort() { print(port) }

// 在 Test 类内给 Host 增加了一个扩展函数
//           ↓
    fun Host.printConnectionString() {
        printHostname()   // Host.printHostname()
        print(":")
        printPort()   // Test.printPort()
    }

// 在 Test 类内给 Host 增加了一个扩展属性
//          ↓
    val Host.isHomeEmpty: Boolean
        get() = hostname.isEmpty()

    fun test() {
        host.printConnectionString()
    }
}

/**
 * ==========================================
 */

class B()
class C()
class D()

class A() {
    fun test(b:B, c: C): D?{ return null }
}

fun A.testExt(b:B, c: C): D?{
    return null
}

fun testReceiver(a: A, b: B, c: C): D? {
    return null
}

fun testFunctionType() {
    var lambda: A.(B, C) -> D? = A::test
    lambda = A::testExt
    lambda = ::testReceiver
    var lambdaX: (A, B, C) -> D? = lambda
}

/**
 * ==========================================
 */

open class Shape

class Rectangle: Shape()

fun Shape.getName() = "Shape"

fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}


fun main() {

    // 报错
//    Host("").isHomeEmpty
//    Host("").printConnectionString()



    printClassName(Rectangle())
}