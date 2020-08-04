package com.boycoder.kotlinjetpackinaction.chapter.c06

/**
 * Created by zhu.tao on 2020/7/31.
 */

open class Base { }

class Derived : Base() { }

open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    val Derived.test: Int
        get() = 1

    fun call(b: Base) {
        b.printFunctionInfo()   // 调用扩展函数
    }
}

class DerivedCaller: BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}

/**
 * 步骤：先运行代码，然后调试代码，最后反编译代码。
 *
 * 理解这个例子的关键在于：
 *
 * BaseCaller().call(), DerivedCaller().call() 是多态的。
 *
 * 而 call 函数里的 base.printFunctionInfo() 是静态的。
 *
 * 这段话一定要结合反编译后的代码看
 *
 */
fun main() {
    BaseCaller().call(Base())
    BaseCaller().call(Derived())
    DerivedCaller().call(Base())
    DerivedCaller().call(Derived())
}