# Study Reactive

The main purpose of Reactive Streams is to allow the subscriber to control how fast or how slow the publisher will produce data.

If a publisher can’t slow down then it has to decide whether to buffer, drop, or fail.

## Ref

- [响应式宣言](https://www.reactivemanifesto.org/zh-CN)
- [Reactive Streams](http://www.reactive-streams.org/) - include JDK9 java.util.concurrent.Flow
- [Project Reactor](https://projectreactor.io/)
- [ReactiveX](http://reactivex.io/)
- 2017-06-07 [使用 Reactor 进行反应式编程](https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html) - 学习 Mono 和 Flux 看这篇
- 2017-01-25 [Reactor 实例解析](http://www.bijishequ.com/detail/48966?p=34-14) - Reactor是第四代响应式框架，跟RxJava 2有些相似。Reactor项目由Pivotal启动，以响应式流规范、Java8和ReactiveX术语表为基础。它的设计是Reactor 2（上一个主要版本）和RxJava核心贡献者共同努力的结果。
- 2017-09-06 [Java - Execute tasks concurrently and wait for completion](https://vividcode.io/Java-task-executor/) - fromCallable + publishOn
- 2017-04-15 [Multithreading with Reactor](http://blog.schauderhaft.de/2017/04/15/multithreading-reactor/)