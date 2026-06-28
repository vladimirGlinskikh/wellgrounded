# WellGrounded Task Platform

Тренировочный проект для книги **"Java для опытных разработчиков" (2-е издание)**.

## Цель
Эксперимент + практически пройти все главы книги, постепенно улучшая один реальный проект.

## Технологии
- Java 21
- Spring Boot 3.3+
- Gradle (Kotlin DSL)
- PostgreSQL + Testcontainers
- Модульная архитектура (JPMS)
- Virtual Threads, Structured Concurrency

## Как запустить
- docker compose down
- docker compose up -d --build
- ./gradlew clean bootRun

## Структура проекта
- `domain` - бизнес-модели (Records, Sealed types)
- `application` - сервисы
- `infrastructure` - адаптеры (БД, очереди)
- `api` - контроллеры

## План по главам
(будет обновляться)

---

**Текущий статус**: Базовая настройка + примеры из Глав 1-3.

