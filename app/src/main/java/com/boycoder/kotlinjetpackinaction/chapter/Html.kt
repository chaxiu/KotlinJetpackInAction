package com.boycoder.kotlinjetpackinaction.chapter

import kotlin.reflect.KProperty

interface Element {
    fun render(builder: StringBuilder, indent: String): String
}

/**
 * 每个节点都有 name，content: <title> Kotlin Jetpack In Action </title>
 */
open class BaseElement(var name: String, var content: String = "") : Element {

    val children = ArrayList<Element>()
    val hashMap = HashMap<String, String>()

    /**
     * 拼接 Html: <title> Kotlin Jetpack In Action </title>
     */
    override fun render(builder: StringBuilder, indent: String): String {
        builder.append("$indent<$name>\n")
        if (content.isNotBlank()) {
            builder.append("  $indent$content\n")
        }
        children.forEach {
            it.render(builder, "$indent  ")
        }
        builder.append("$indent</$name>\n")
        return builder.toString()
    }


    protected fun <T : BaseElement> initString(element: T, init: T.() -> String): T {
        val content = element.init()
        element.content = content
        children.add(element)
        return element
    }

    protected fun <T : Element> init(element: T, init: T.() -> Unit): T {
        element.init()
        children.add(element)
        return element
    }

    operator fun String.invoke(block: BaseElement.() -> Unit): BaseElement {
        val element = BaseElement(this)
        element.block()
        this@BaseElement.children += element
        return element
    }

    operator fun String.invoke(value: String) {
        this@BaseElement.hashMap[this] = value
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

class Body : BaseElement("body") {
    fun h1(block: H1.() -> String) = initString(H1("h1"), block)
    fun p(block: P.() -> String) = initString(P("p"), block)

    fun img(src: String, alt: String): IMG {
        val img = IMG().apply {
            this.src = src
            this.alt = alt
        }

        this.children += img
        return img
    }
}

class Head : BaseElement("head") {
    fun title(block: Title.() -> String) = initString(Title(), block)
}

// 这两个扩展函数可以注释掉，这样 by hashMap 会使用官方的实现
// 感兴趣的可以看看官方怎么实现的。
operator fun HashMap<String, String?>.getValue(thisRef: Any, property: KProperty<*>): String? =
        get(property.name)

operator fun HashMap<String, String>.setValue(thisRef: Any, property: KProperty<*>, value: String) =
        put(property.name, value)

class IMG : BaseElement("img") {
    var src: String by hashMap

    var alt: String by hashMap

    override fun render(builder: StringBuilder, indent: String): String {
        builder.append("$indent<$name")
        builder.append(renderAttributes())
        builder.append(" /$name>\n")
        return builder.toString()
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in hashMap) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }
}

class P(content: String = "") : BaseElement("p", content)
class H1(content: String = "") : BaseElement("h1", content)
class Title(content: String = "") : BaseElement("title", content)

class HTML : BaseElement("html") {
    fun head(block: Head.() -> Unit) = init(Head(), block)
    fun body(block: Body.() -> Unit) = init(Body(), block)
}

fun html(block: HTML.() -> Unit): HTML {
    val html = HTML()
    html.block()
    return html
}

fun main() {
    val htmlContent = html {
        head {
            title { "Kotlin Jetpack In Action" }
        }
        body {
            h1 { "Kotlin Jetpack In Action" }
            p { "-----------------------------------------" }
            p { "A super-simple project demonstrating how to use Kotlin and Jetpack step by step." }
            p { "-----------------------------------------" }
            p {
                "I made this project as simple as possible," +
                        " so that we can focus on how to use Kotlin and Jetpack" +
                        " rather than understanding business logic."
            }
            p {
                "We will rewrite it from \"Java + MVC\" to" +
                        " \"Kotlin + Coroutines + Jetpack + Clean MVVM\"," +
                        " line by line, commit by commit."
            }
            p { "-----------------------------------------" }
            p { "ScreenShot:" }
            img(src = "https://user-gold-cdn.xitu.io/2020/6/15/172b55ce7bf25419?imageslim",
                    alt = "Kotlin Jetpack In Action")
        }
    }.toString()

    println(htmlContent)
}