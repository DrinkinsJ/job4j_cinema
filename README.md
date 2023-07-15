job4j Cinema Service

Веб-Сервис киноотеатр, позволяющий пользователю покупать билеты на интересующий его сеанс, 
так же можно выбрать ряд и место. Пользоваться сайтом может любой пользователь, для покупки билета необходима регистрация.
На сайте есть несколько страниц, таких как:   
-Главная страница   
-Регистрация/вход  
-Расписания сеансов  
-Кинотека(список всех фильмов)  
-Страница покупки билетов  
-Страницы с результатом покупки билета

### Стек технологий:  
-Java 17  
-Spring Boot 2.7.6  
-Thymeleaf  
-Bootstrap  
-Liquibase  
-Sql2o 1.6.0  
-PostgreSQL 10  
-H2  
-Mockito
-Maven 3.1.2.

### Требования к окружению:  
Java 17, Maven 3.8, PostgreSQL 14;

### Запуск приложения:  
-клонировать проект  
-создать базу данных "cinema"  
-в папке проекта выпонить команду mvn spring-boot:run  
-в браузере перейти на страницу http://localhost:8080/  

# Взаимодействие с приложением:
1. Главная страница.  
![](files/readme_img/index.png)
2. Расписание фильмов.  
![](files/readme_img/schedule.png)
3. Описание/библиотека фильмов.  
![](files/readme_img/library.png)
4. Страница регистрации.  
![](files/readme_img/register.png)
5. Страница входа.  
![](files/readme_img/login.png)
6. Страница покупки билета, ряд и место можно выбрать, колличество рядов и мест в выпадающих списках, соотвествует залу.  
![](files/readme_img/buying_page.png)
7. Страница успешной покупки. Сюда могут попасть только авторизированые пользователи.  
![](files/readme_img/ticket_success.png)
8. Страница неудачной покупки. Например, место уже куплено.
![](files/readme_img/ticket_fail.png)
  
  
## ? Мои контакты
tg: @drinkinswo

Cinema Service  
[![Readme Quotes](https://quotes-github-readme.vercel.app/api?type=horizontal&theme=dark)](https://github.com/piyushsuthar/github-readme-quotes)