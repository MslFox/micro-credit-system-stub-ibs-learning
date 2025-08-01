Рекомендуемое время
1 час
Инструкция
Требования к инструментам разработки:
IDE: idea Community
Версии java и spring boot надо выбирать из поддерживаемых в https://start.spring.io/
Язык: java
Система сборки: maven

Предметная область НТ: Выдача микрозаймов

Объект тестирования: Система выдачи микрокредитов

Интеграция (что эмулируется заглушкой):
Система процессинга (в сфере финансов процессинг
– это процесс обработки и подтверждения платежей, совершаемых с использованием банковских карт, электронных кошельков и других платежных инструментов).

Общие требования к разработке заглушки:
-Протокол: http
-Формат обмена данными: json
-Предусмотреть возможность работы заглушки с двумя профилями (помимо профиля по умолчанию): test1 и test2. При использовании профиля test1 заглушка запускается на порту 7770. При использовании профиля test1 заглушка запускается на порту 7775.
-Заглушка должна запускаться через терминал с начальным кол-вом памяти в 2Gb и с максимально доступным кол-вом памяти в куче 4Gb
-Заглушка должна поддерживать 3 профиля. Профиль по умолчанию(application.yml), профили test1 и test2
-Заглушка должна поддерживать документацию всех API.
-заглушка должна поддерживать логирование. Логировать в файл вызовы методов заглушки. Настройки логирования вынести в профиль по умолчанию
-Заглушка должна поддерживать БД H2. (достаточно после запуска убедиться что открывается интерфейс БД). Настройки соединения к БД вынести в профиль по умолчанию
-Заглушка должна поддерживать сбор метрик через micrometr.
-Задание для самостоятельной работы(это проверять не надо): нагрузить ступеньками из jmeter заглушку. Посмотреть как работает JVM через javaVisualVM \jconsole. Попробовать настроить вывод метрик micrometr в графану

//---------------------------------------------------------------


В заглушке, для взаимодействия с объектом НТ необходимо реализовать 3 api (ручки, endpoints):

1. GET /v2/checkAccount?acc=1234567890&days=2 - Проверить информацию о задолженностях по карте клиента

тело и заголовки запроса отсутствуют
Тело ответа заглушки:
{
"account": "1234567890",
"vip-client": false,
"blocked": false,
"inn": "1234567890111",
"debt": [
{
"sum": 1000,
"description": "parking"
},
{
"sum": 3000,
"description": "gkh"
}
]
}

Поле inn получается из конкатенации параметра acc и "111"
Значение поля account берется из параметра запроса acc
Массив debt содержит количество объектов-задолженностей равным параметру days из запроса
Если в запросе acc заканчивается на нечетную цифру, в ответе поле vip-client должно быть true
http-код ответа: 202

//-------------------------------------------------

2. POST /v2/payment/ - Подтвердить платеж

Обязательные хидеры в запросе:
BankCode: 22

Тело запроса, приходящего в заглушку:
{
"transaction_id": "T-342-67777",
"sum": 133.12,
"need_processing": true
}

Тело ответа:
{
"transaction_id": "T-342-67777",
"bank_bik": "2345678997",
"status": "accepted",
"contact" : [
{
"name" : "HL pay company",
"telecom" :[
"43t5h7", k8fg534, 67hr333f, gj6iire
]
}
]
}

Обязательные хидеры ответа:
ProcessingTIme: dd-MM-yyyy HH:mm:ss


transaction_id ответа = transaction_id запроса
bank_bik - строка не менее 10 символов(числовая строка)
массив telecom должен содержать в ответе кол-во строк (цифры\буквы) равное сумме цифр BankCode.
Хидер ответа - время ответа заглушки в указанном формате
http-код ответа: 200

//----------------------------------------------------------------

3. DELETE /v1/transactions/cleare/{id} - Удалить транзакцию процессинга

Код ответа 100, тело ответа - "deleted success" (формат: plain text)
Заглушка должна возвратить ответ с задержкой X сек. (эмуляция задержки реальной системы).
При использовании профиля test1, значение Х = 2. При использовании профиля test2, значение Х = 4. Если никакой профиль не задан при старте заглушки, значение X = 1.

Выполненное задание нужно залить в git:
-проект (основная папка проекта в idea, где содержатся исходники заглушки + jar-файл)
-в readme гита указать как пользоваться заглушкой:
1) команду для запуска jar-файла без явного указания профиля настроек
2) команду для запуска jar-файла с профилем test1
3) команду для запуска jar-файла с профилем test2
   Отправить ссылку в поле ответа.