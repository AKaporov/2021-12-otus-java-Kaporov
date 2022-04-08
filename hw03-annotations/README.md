# Аннотации. Свой тестовый фреймворк

### Цель:

### научиться работать с reflection и аннотациями, понять принцип работы фреймворка junit.

Описание/Пошаговая инструкция выполнения домашнего задания:

- Поддержать свои аннотации @Test, @Before, @After.
- Запускать вызовом статического метода с именем класса с тестами.

Т.е. надо сделать:

1) Создать три аннотации - @Test, @Before, @After.
2) Создать класс-тест, в котором будут методы, отмеченные аннотациями.
3) Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить
   методы отмеченные аннотациями и пункта 1.
4) Алгоритм запуска должен быть следующий:
    - метод(ы) Before;
    - текущий метод Test;
    - метод(ы) After.
5) Для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
6) Исключение в одном тесте не должно прерывать весь процесс тестирования.
7) На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло успешно,
   сколько упало, сколько было всего)
8) "Запускалка теста" не должна иметь состояние, но при этом весь функционал должен быть разбит на приватные методы.
   Надо придумать, как передавать информацию между методами.

* [Invoke methods of an object using reflection : Method « Reflection « Java Tutorial](http://www.java2s.com/Tutorial/Java/0125__Reflection/Invokemethodsofanobjectusingreflection.htm)