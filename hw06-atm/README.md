# Написать эмультор ATM(банкомата).

### Цель: Применить на практике принципы SOLID.

Объект класса АТМ должен уметь:

1) принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка);
2) выдавать запрошенную сумму минимальным количеством банкнот или ошибку если сумму нельзя выдать. Это задание не на
   алгоритмы, а на проектирование. Поэтому оптимизировать выдачу не надо.
3) выдавать сумму остатка денежных средств.

- UI не нужен ни в каком виде(НЕ надо консоль, web, swing)
- Надо продемонстрировать работу в main(), а лучше в тестах.
- Не надо: пользователей, авторизация, клавиатура, дисплей, валюта, счет, карта и т.п.
- Начните проектирование с диаграммы классов. Сдавать диарамму для проверки не нужно.
- Если уже знаете паттерны, то делайте с ними.

В этом задании больше думайте об архитектуре приложения. Не отвлекайтесь на создание таких объектов как: пользователь,
авторизация, клавиатура, дисплей, UI (консольный, Web, Swing), валюта, счет, карта, т.д. Все это не только не нужно, но
и вредно!

* [GitHub - petrelevich/otus_java_2021_12](https://github.com/petrelevich/otus_java_2021_12)
* [Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
* [Закон Деметры](https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%BA%D0%BE%D0%BD_%D0%94%D0%B5%D0%BC%D0%B5%D1%82%D1%80%D1%8B)
* [Coupling (зацепление)](https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D1%86%D0%B5%D0%BF%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5_(%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5))
* [Полиморфизм (информатика)](https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%BB%D0%B8%D0%BC%D0%BE%D1%80%D1%84%D0%B8%D0%B7%D0%BC_(%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0))
* [Объектно-ориентированное программирование](https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BD%D0%BE-%D0%BE%D1%80%D0%B8%D0%B5%D0%BD%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D0%BE%D0%B5_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)
* [Принцип подстановки Барбары Лисков](https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF_%D0%BF%D0%BE%D0%B4%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B8_%D0%91%D0%B0%D1%80%D0%B1%D0%B0%D1%80%D1%8B_%D0%9B%D0%B8%D1%81%D0%BA%D0%BE%D0%B2)
* [Java 14 Record Keyword](https://www.baeldung.com/java-record-keyword)