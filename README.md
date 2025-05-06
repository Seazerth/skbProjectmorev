
# Spring DI, Validation, Security, Events & Messaging Showcase

ФИО: Морев Владислав Витальевич  
Преподаватель: Никита Карсканов

## О проекте
Это демонстрационное Spring Boot-приложение, реализующее:
- Dependency Injection (DI) разными способами
- Профили конфигурации
- Кастомные валидаторы и аннотации
- Валидацию JSON и работу с БД
- Контроллеры с HTML и JSON логикой
- Обработку событий (в т.ч. асинхронных и транзакционных)
- Ограничение количества вызовов API через AOP
- Аутентификацию с ролями через InMemoryUserDetailsManager
- Интеграцию с брокером сообщений RabbitMQ
- Метрики

---

## 1. Dependency Injection
- **Через конструктор**, поле и сеттер
- Используются 2 бина, реализующих общий интерфейс
- Логируются события создания и уничтожения бина

---

## 2. Контроллеры
- `GET /headers` — возвращает HTML со всеми заголовками запроса
- `POST /json` — принимает JSON, добавляет поле `id`, возвращает изменённый JSON

---

## 3. Обработка ошибок
- Кастомная ошибка с кодом **502 Bad Gateway**

---

## 4. Профили (dev, test, prod)
- Каждый профиль содержит:
    - Название приложения
    - Лист значений
    - Переменная окружения `EXAMPLE_TEST` (значение по умолчанию: `default`)
- Бины:
    - 1 создаётся только в `test`
    - 2 зависит от 1
    - 3 создаётся при `EXAMPLE_TEST != "default"`

---

## 5. Валидация
- **Кастомная аннотация** + валидатор
- Композитная аннотация
- Кастомный респонс при ошибке

---

## 6. Работа с БД
- Сохраняет объекты:
```json
{
  "name": "Мой список дел",
  "events": ["дело1", "дело2"]
}
```
- Возвращает массив сохранённых объектов

---

## 7. Безопасность
- **RolesAllowed**:
    - `/public/api` — доступен всем
    - `/admin/api` — только ROLE_ADMIN
    - `/support/api` — только ROLE_SUPPORT
- **Basic Auth**
- BCryptPasswordEncoder
- InMemoryUserDetailsManager

---

## 8. События
- Слушатели:
    - обычный
    - асинхронный
    - транзакционный
- Одно из транзакционных событий не обрабатывается

---

## 9. AOP
- Ограничение количества вызовов API
- Значение задаётся в конфигурации

---

## 10. RabbitMQ
- Два сервиса обмениваются JSON-сообщениями
- Пример тела:
```json
{
  "message": "Пример"
}
```
- Входящее сообщение логируется
- Используемая зависимость:
```groovy
implementation 'org.springframework.boot:spring-boot-starter-amqp'
```

---

## 11. Репозиторий

https://github.com/Seazerth/2conteyner

---

## 12. Метрики и Актуаторы (Monitoring & Actuators)

### Метрики

Приложение отслеживает:
- Общее количество выполненных задач (`todo.completed.tasks`) по типам: `home`, `work`, `other`
- Время выполнения каждого запроса
- Стандартные Spring Boot метрики (включены через Micrometer и Actuator)

Метрики доступны по адресу:
```
GET /actuator/metrics
GET /actuator/metrics/todo.completed.tasks
```

### Кастомный актуатор `/actuator/tasklog`

Позволяет записать в лог дату и время вызова, например:
```
2025-05-06 15:43:27 Актуатор tasklog вызван
```

Пример запроса:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/actuator/tasklog" -Method GET
```

### Эмуляция действий

Выполнение задачи (например, `home`):
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/tasks/complete/home" -Method POST
```

Получение кастомных метрик:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/tasks/metrics" -Method GET
```
