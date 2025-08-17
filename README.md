# Users App

Users App — современное Android-приложение для просмотра информации о пользователях, реализованное
на Kotlin с использованием Jetpack Compose и Material 3.

## Описание

Users App позволяет просматривать список пользователей, а также подробную информацию о каждом
пользователе, включая город, email, телефон, адрес и фото. Поддерживает анимированные переходы между
экранами и анимацию элементов UI.

---
## Скриншоты
<p align="center">
<img width="300" height="810" alt="image" src="https://github.com/user-attachments/assets/2ed2ae8a-b2d3-4987-a2f1-d7d9db3517d1" />
<img width="300" height="817" alt="image" src="https://github.com/user-attachments/assets/6d66b439-fb49-4330-9dd4-f8df1503df49" />
</p>

## Основные возможности

- Просмотр списка пользователей
- Экран подробной информации о пользователе
- Отправка email, вызов телефона, открытие адреса на карте прямо из приложения
- Современное оформление с Material 3
- Анимированные переходы между экранами

---

## Технологии и зависимости

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose 
- Coil (загрузка изображений)
- ViewModel + StateFlow (архитектура MVVM)
- Koin (Dependency Injection)

## Установка и запуск

1. Клонируйте репозиторий:
    ```sh
    git clone <url_вашего_репозитория>
    cd users-app
    ```
2. Откройте проект в Android Studio (Arctic Fox или выше).
3. Дождитесь завершения сборки Gradle и скачивания зависимостей.
4. Запустите проект на устройстве или эмуляторе с API 23+.

---

## Структура проекта

```
/app
  /src/main/java/ru/vsls/users/
    /data         — data-слой и модели
    /domain         — бизнес-логика и модели
    /presentation
      /screens      — экраны (list, details)
      /items        — пользовательские UI компоненты
      /navigation   — классы навигации и роуты
    /ui/theme       — тема и цвета
```
---
