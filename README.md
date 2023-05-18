
#  Дипломный проект курса "Java разработчик".
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
## Командная дипломная работа. Поток - DSprint 5.0.
![](https://resizer.mail.ru/p/06d6c943-6937-5f12-9350-41be07318717/AAAKp6KFfs4XdXiFM7jMkGtKxGcciXMoDnELBxbYtSEBIop40u9T70kOZX2G8NZA41HJ5rIvT8efi_L5oOBlEUgFe_U.jpg)

Задача команды написать бекенд-часть сайта на Java для готовой фронтенд части и реализовать следующий функционал:

* Авторизация и аутентификация пользователей.
* Распределение ролей между пользователями: пользователь и администратор.
* CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления, а пользователи — только свои.
* Под каждым объявлением пользователи могут оставлять отзывы.
* В заголовке сайта можно осуществлять поиск объявлений по названию.
* Показывать и сохранять картинки объявлений.
  В качестве шаблона был предоставлен файл [Openapi](https://drive.google.com/file/d/1NInRupH5y59DMAFvUDcd2C0kIWaMk93Q/view).
--------

## Техническое задание проекта:
- [ТЗ](https://skyengpublic.notion.site/02df5c2390684e3da20c7a696f5d463d)
- ----


## Комада разработчиков "KK":

- [Екатерина Одокиенко](https://github.com/KatOli4ka)
- [Екатерина Токан](https://github.com/KaterinaT666/)
 - -----



## Стек технологий:
**В проекте используются** :

* Backend:
    - Java 17
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data
    - Spring JPA
    - Spring Security
    - GIT
    - REST
    - Swagger
    - Lombok
    - Stream API
* SQL:
    - PostgreSQL
    - Liquibase
* Frontend:
    - Docker образ
- ----

## Запуск :
**Для запуска нужно:**
- Клонировать проект в среду разработки
- Прописать properties в файле **[application.properties](src/main/resources/application.properties)**
- Запустить **[Docker](https://www.docker.com)**
- Запустить **[Docker образ](https://drive.google.com/file/d/1UZTpeTAQpC4ANkHEFAGK2yjTFzZhXLPz/view)**
- Запустить метод **main** в файле **[DiplomApplication.java](src/main/java/pro/sky/diplom/DiplomApplication.java)**
-----

После выполнения всех действий сайт будет доступен по ссылке http://localhost:3000 и Swagger по [ссылке](https://editor.swagger.io/).
