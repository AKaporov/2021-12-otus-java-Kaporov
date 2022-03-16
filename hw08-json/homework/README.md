# Обработчик JSON-ов (сокращение от JavaScript Object Notation)

###Цель:
Научиться обрабатывать json, научиться работать с файлами.

###Описание/Пошаговая инструкция выполнения домашнего задания:
Некая система, которая:
1) принимает входящий json файл;
2) обрабатывает данные из файла;
3) формирует ответный файл.

Надо реализовать недостающий функционал Более подробно смотрите в примерах к вебинару.

* [Package java.io](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/package-summary.html) - В этом пакете находятся функции для реализации операций ввода/вывода.
* [Java I/O Streams](https://docs.oracle.com/javase/tutorial/essential/io/streams.html) - Туториал от Oracle. I/O Strem – представляют собой источник данных или место назначения для вывода данных.
* [JSR 374: JavaTM API for JSON Processing 1.1](https://www.jcp.org/en/jsr/detail?id=374)
* [API module of JSR 374:Java API for Processing JSON](https://mvnrepository.com/artifact/javax.json/javax.json-api)
* [Gson](https://github.com/google/gson) - одна из самых популярных и простых библиотек для сериализации/десериализации в json (Особенно удобно пользоваться, если уже есть готовый java-объект, с которым надо поработать).
* [Jackson](https://github.com/FasterXML/jackson) – еще одна популярная библиотека для работы с JSON (Используется в проектах Spring).
* [Google ProtoBuf](https://developers.google.com/protocol-buffers/) - платформо-независимый протокол обмена сообщениями между системами.