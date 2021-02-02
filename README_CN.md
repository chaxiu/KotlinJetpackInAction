![](https://ftp.bmp.ovh/imgs/2021/02/90b17bf6fcb6ea42.jpeg)

# KotlinJetpackInAction
这是一个简单到极致的项目，旨在帮助 Android 开发者快速掌握 Kotlin + Jetpack + MVVM 的开发模式。配套的技术博客也在掘金同步更新：[《Kotlin Jetpack 实战》](https://juejin.im/post/5ee624756fb9a047bb6a69cf)


## 为什么要写这个项目?

- GitHub 上不缺高大上的 Kotlin Jetpack 开源项目，但它们太复杂了，对初学者不太友好。所以我写了一个`极其简单`的项目，方便初学者理解。
- 在这里，我会一步一步，一行一行代码展示：如何将一个“传统 Java”工程改造成：“Kotlin + Coroutines + Jetpack + Clean MVVM” 的工程。

## 如何使用这个项目来学习 Kotlin Jetpack?

| 练习内容 | 练习的分支 | 对应的文章 |
| --- | --- | --- |
| 快速入门 Kotlin | / | [《写给 Java 开发者的 Kotlin 入坑指南》](https://juejin.im/post/5ee633ee51882542e8542e4f) |  |
| Kotlin DSL 练习 | [chapter_02_kotlin_dsl_training](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_02_kotlin_dsl_training) | [《Kotlin 写 Gradle 脚本是一种什么体验？》](https://juejin.im/post/5ee75805f265da76fb0c5db1) |
| Kotlin 重构 Java 代码 | [chapter_03_kotlin_refactor_training](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_03_kotlin_refactor_training) | [《Kotlin 编程的三重境界》](https://juejin.im/post/5ef939e05188252e644cdc4c)
| Kotlin HTML DSL 练习 | [chapter_04_lambda](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_04_lambda) | [《Kotlin 高阶函数写 HTML 是一种什么体验？》](https://juejin.im/post/5f202f816fb9a07ebd4a95ea)
| Kotlin 泛型 优化 HTML DSL | [chapter_05_generics](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_05_generics) | [《Kotlin 泛型》](https://juejin.im/post/6856553487598256141)
| Kotlin 扩展 Span | [chapter_06_extension](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_06_extension) | [《Kotlin 扩展》](https://juejin.im/post/6857678090794237959)
| Kotlin 委托 SharedPreferences | [chapter_07_delegate](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_07_delegate) | [《Kotlin 委托》](https://juejin.im/post/6859172099680894989)
| Kotlin 协程调试 | [chapter_08_coroutine_debug](https://github.com/chaxiu/KotlinJetpackInAction/tree/chapter_08_coroutine_debug) | [《协程“不为人知”的调试技巧》](https://juejin.im/post/6860647298926379021)
| Kotlin 原理 | 对应源码在这里[chapter09](https://github.com/chaxiu/KotlinJetpackInAction/tree/master/app/src/main/java/com/boycoder/kotlinjetpackinaction/chapter/c09) | [《图解协程原理》](https://juejin.cn/post/6883652600462327821)

-----------------
### 👉 [图解协程原理](https://boycoder.medium.com/kotlin-coroutines-animation-explanation-dba6d4d5888b)

#### [Thread & Coroutines](https://boycoder.medium.com/kotlin-coroutines-animation-explanation-dba6d4d5888b)

![Thread & Coroutines](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/436f83cbae9f407db24538c0922b6adc~tplv-k3u1fbpfcp-watermark.image)

#### [CPS 转换](https://boycoder.medium.com/kotlin-coroutines-animation-explanation-dba6d4d5888b)

![CPS](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/de2b6b97c0284becbc6d329cbd66e4ab~tplv-k3u1fbpfcp-watermark.image)

#### [协程执行流程](https://boycoder.medium.com/kotlin-coroutines-animation-explanation-dba6d4d5888b)

![flow](https://ftp.bmp.ovh/imgs/2021/02/2e0ed7717ffc0a25.gif)

#### [协程动图解释](https://boycoder.medium.com/kotlin-coroutines-animation-explanation-dba6d4d5888b)

![Coroutines Under The Hood](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/56ba74c4febf4140a26174eac73e1880~tplv-k3u1fbpfcp-watermark.image)