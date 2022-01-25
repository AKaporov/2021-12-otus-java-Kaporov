# Применение коллекций

### Цель:
### попрактиковать различные аспекты коллекций. Познакомиться с реализацией Map.

Надо сделать todo в классах из пакета homework.
Все тесты должны проходить. 
Предполагается использование встроенного в jdk функционала, поэтому реализация методов должна быть буквально из нескольких строк.

* [Основы Java collections](https://www.youtube.com/watch?v=Qd8-vNdo1sc)
* [Особенность устройства LinkedHashSet](https://www.youtube.com/watch?v=xTEZ4U9aUkM)
* [Использование generic wildcards для повышения удобства Java API](https://habr.com/ru/post/207360/)
* [Хеш-таблица](https://ru.wikipedia.org/wiki/%D0%A5%D0%B5%D1%88-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D0%B0)
* [When to use LinkedList over ArrayList in Java?](https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java)

----
Что бы вместо звездочек были буквы при запуске тестов нужно в terminal выполнить команду из проверяемого модуля (см. видео лекции "3 - QA и тестирование"):
chcp 1251

Что бы запустить тесты в gradle нужно выполнить команду из тестируемого модуля (clean - что бы запустились все тесты. Иначе запустятся тесты в которых были изменения, кэшируются файлы):
../gradlew.bat L03-qa:clean test или ../gradlew.bat clean test или ./gradlew.bat L04-generics:clean test (из Родительского модуля, общео для всех модулей)
