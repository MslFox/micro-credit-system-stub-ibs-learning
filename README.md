## Заглушка

#### Система процессинга обработки и подтверждения платежей, совершаемых с использованием банковских карт, электронных кошельков и других платежных инструментов.

---

### Сборка

```bash
mvn clean package
```

### Запуск

#### Без указания профиля (`default`)

```bash
java -Xms2g -Xmx4g -jar target/stub-0.0.1-SNAPSHOT.jar
```

#### С профилем test1

```bash
java -Xms2g -Xmx4g -jar target/stub-0.0.1-SNAPSHOT.jar --spring.profiles.active=test1
```

#### С профилем test2

```bash
java -Xms2g -Xmx4g -jar target/stub-0.0.1-SNAPSHOT.jar --spring.profiles.active=test2
```

### Swagger UI & Metrics
| Профиль  | Порт | Swagger UI URL                                                                             | Metrics                                                          |
|----------|------|--------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| default* | 8080 | [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) | [http://localhost:8080/actuator](http://localhost:8080/actuator) |
| test1*   | 7770 | [http://localhost:7770/swagger-ui/index.html](http://localhost:7770/swagger-ui/index.html) | [http://localhost:7770/actuator](http://localhost:7770/actuator) |
| test2*   | 7775 | [http://localhost:7775/swagger-ui/index.html](http://localhost:7775/swagger-ui/index.html) | [http://localhost:7775/actuator](http://localhost:7775/actuator) |\

***\*Тестирование DELETE /v1/transactions/cleare/{id} с промежуточным ответом HTTP 100***
```
при запросе использовать заголовок `Expect: 100-continue`
curl -v -X DELETE "http://localhost:<порт>/v1/transactions/cleare/{id}" -H "Expect: 100-continue"
```

### Логирование

#### Пути к файлам логирования

```
./logs/default/stub.log
./logs/test1/stub.log
./logs/test2/stub.log
```

### БД Н2 (Для каждого профиля своя)

```
./loсal/default
./loсal/test1
./loсal/test2
```