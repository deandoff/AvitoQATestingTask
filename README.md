# Avito QA Testing Assignment

## Описание проекта
Данный проект предназначен для тестирования API Avito с использованием Java, JUnit5 и Rest Assured.
Проект содержит решения 2 тестовых заданий <br> 
### Задание 1 
- [Задание 1](/Task1/Task1.md) представлено в виде Markdown-файла с описанием багов на странице сайта

### Задание 2
- Представляет собой комплексное задание с написанием [тест-кейсов](/Task2/TESTCASES.md), написанием [автотестов](/src/test/java) на любом языке и фреймворке и последующим составлением [баг-репорта](/Task2/BUGS.md)

## Установка
Для запуска данного проекта необходимо:
1. Установить [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (версии 11 или выше).
2. Установить [Apache Maven](https://maven.apache.org/download.cgi).
3. Клонировать данный репозиторий:
   ```bash
   git clone https://github.com/deandoff/AvitoQATestingTask.git
   cd AvitoQATestingTask/src
4. Собрать проект с помощью Maven
    ```bash
    mvn clean install
5. Запустить тесты
    ```bash
    mvn test
   
## Структура тестов
### Каждая группа тестов организована в отдельном классе:

- GetItemByIDTests: тесты для проверки получения объявления по ID.
- GetItemStatisticByIDTests: тесты для проверки статистики объявления по ID.
- GetSellersItemsByIDTests: тесты для получения объявлений продавца по ID.
- ItemCreateTests: тесты для создания объявления.