package com.boycoder.kotlinjetpackinaction.chapter

interface Element {
    fun render(builder: StringBuilder, indent: String): String
}

/**
 * 每个节点都有 name，content: <title> Kotlin Jetpack In Action </title>
 */
open class BaseElement(val name: String, val content: String = "") : Element {

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
    fun h1(block: () -> String): H1 {
        val content = block()
        val h1 = H1(content)
        this.children += h1
        return h1
    }

    fun p(block: () -> String): P {
        val content = block()
        val p = P(content)
        this.children += p
        return p
    }

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
    fun title(block: () -> String): Title {
        val content = block()
        val title = Title(content)
        this.children += title
        return title
    }
}

class IMG : BaseElement("img") {
    var src: String
        get() = hashMap["src"]!!
        set(value) {
            hashMap["src"] = value
        }

    var alt: String
        get() = hashMap["alt"]!!
        set(value) {
            hashMap["alt"] = value
        }

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

class P(content: String) : BaseElement("p", content)
class H1(content: String) : BaseElement("h1", content)
class Title(content: String) : BaseElement("title", content)

class HTML : BaseElement("html") {
    fun head(block: Head.() -> Unit): Head {
        val head = Head()
        head.block()
        this.children += head
        return head
    }

    fun body(block: Body.() -> Unit): Body {
        val body = Body()
        body.block()
        this.children += body
        return body
    }
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
            h1 { "Kotlin Jetpack In Action"}
            p { "-----------------------------------------" }
            p { "A super-simple project demonstrating how to use Kotlin and Jetpack step by step." }
            p { "-----------------------------------------" }
            p { "I made this project as simple as possible," +
                    " so that we can focus on how to use Kotlin and Jetpack" +
                    " rather than understanding business logic." }
            p {"We will rewrite it from \"Java + MVC\" to" +
                    " \"Kotlin + Coroutines + Jetpack + Clean MVVM\"," +
                    " line by line, commit by commit."}
            p { "-----------------------------------------" }
            p { "ScreenShot:" }
            img(src = "https://user-gold-cdn.xitu.io/2020/6/15/172b55ce7bf25419?imageslim",
                    alt = "Kotlin Jetpack In Action")
        }
    }.toString()

    println(htmlContent)
}