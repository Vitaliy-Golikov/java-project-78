### Hexlet tests and linter status:
[![Actions Status](https://github.com/Vitaliy-Golikov/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Vitaliy-Golikov/java-project-78/actions)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Vitaliy-Golikov_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Vitaliy-Golikov_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Vitaliy-Golikov_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Vitaliy-Golikov_java-project-78)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Vitaliy-Golikov_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Vitaliy-Golikov_java-project-78)

# Валидатор данных (Java)

Библиотека для валидации строк, чисел и объектов `Map`.

---

## Класс Validator

Фасад для создания схем валидации.

| Метод | Возвращает | Описание |
|-------|------------|----------|
| `string()` | `StringSchema` | Создаёт схему для валидации строк |
| `number()` | `NumberSchema` | Создаёт схему для валидации чисел |
| `map()` | `MapSchema` | Создаёт схему для валидации `Map` |

```java
Validator v = new Validator();

StringSchema stringSchema = v.string();
NumberSchema numberSchema = v.number();
MapSchema mapSchema = v.map();
```

---

## StringSchema

Схема для валидации строк.

### Методы

| Метод | Возвращает | Описание |
|-------|------------|----------|
| `required()` | `StringSchema` | Делает данные обязательными. Запрещает `null` и пустую строку |
| `minLength(int length)` | `StringSchema` | Задаёт минимальную длину строки |
| `contains(String substring)` | `StringSchema` | Требует наличия подстроки в строке |
| `isValid(String value)` | `boolean` | Проверяет значение по всем добавленным правилам |

### Примеры использования

```java
StringSchema schema = new StringSchema();

// Без required() null и пустая строка считаются валидными
assertTrue(schema.isValid(null));
assertTrue(schema.isValid(""));

// После required() null и пустая строка невалидны
schema.required();
assertFalse(schema.isValid(null));
assertFalse(schema.isValid(""));
assertTrue(schema.isValid("Hello"));

// Проверка минимальной длины
schema.minLength(5);
assertTrue(schema.isValid("Hello"));
assertTrue(schema.isValid("Welcome"));
assertFalse(schema.isValid("Hi"));

// Проверка содержания подстроки
schema.contains("llo");
assertTrue(schema.isValid("Hello"));
assertFalse(schema.isValid("World"));

// Цепочка вызовов
schema.required().minLength(5).contains("el");
assertTrue(schema.isValid("Hello"));
```

---

## NumberSchema

Схема для валидации чисел.

### Методы

| Метод | Возвращает | Описание |
|-------|------------|----------|
| `required()` | `NumberSchema` | Делает данные обязательными. Запрещает `null` |
| `positive()` | `NumberSchema` | Число должно быть положительным (больше 0) |
| `range(int min, int max)` | `NumberSchema` | Число должно быть в диапазоне [min, max] включительно |
| `isValid(Integer value)` | `boolean` | Проверяет значение по всем добавленным правилам |

### Примеры использования

```java
NumberSchema schema = new NumberSchema();

// Без required() null считается валидным
assertTrue(schema.isValid(null));

// Проверка на положительность
schema.positive();
assertTrue(schema.isValid(null));  // null не проверяется
assertTrue(schema.isValid(5));
assertFalse(schema.isValid(0));
assertFalse(schema.isValid(-10));

// После required() null невалиден
schema.required();
assertFalse(schema.isValid(null));
assertTrue(schema.isValid(10));

// Проверка диапазона
schema.range(2, 6);
assertTrue(schema.isValid(2));
assertTrue(schema.isValid(4));
assertTrue(schema.isValid(6));
assertFalse(schema.isValid(1));
assertFalse(schema.isValid(9));

// Цепочка вызовов
schema.required().positive().range(5, 10);
assertTrue(schema.isValid(7));
assertFalse(schema.isValid(0));
```

---

## MapSchema

Схема для валидации объектов `Map`.

### Методы

| Метод | Возвращает | Описание |
|-------|------------|----------|
| `required()` | `MapSchema` | Делает данные обязательными. Запрещает `null` |
| `sizeof(int size)` | `MapSchema` | Задаёт точный размер карты (количество ключей) |
| `shape(Map<String, BaseSchema<?>> schemas)` | `MapSchema` | Задаёт схемы валидации для значений по указанным ключам |
| `isValid(Map<String, Object> value)` | `boolean` | Проверяет значение по всем добавленным правилам |

### Примеры использования

```java
MapSchema schema = new MapSchema();

// Без required() null валиден
assertTrue(schema.isValid(null));
assertTrue(schema.isValid(new HashMap<>()));

// required() запрещает null
schema.required();
assertFalse(schema.isValid(null));
assertTrue(schema.isValid(new HashMap<>()));

// Проверка размера
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");

schema.sizeof(2);
assertFalse(schema.isValid(data));

data.put("key2", "value2");
assertTrue(schema.isValid(data));

// Проверка размера с null
schema.sizeof(2);
assertTrue(schema.isValid(null)); // null не проверяется на размер
schema.required();
assertFalse(schema.isValid(null)); // null не проходит required

// Вложенная валидация (shape)
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", validator.string().required());
schemas.put("lastName", validator.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human = new HashMap<>();
human.put("firstName", "John");
human.put("lastName", "Smith");
assertTrue(schema.isValid(human));

// Ошибка: фамилия слишком короткая
human.put("lastName", "B");
assertFalse(schema.isValid(human));

// Ошибка: отсутствует обязательное поле
human.remove("firstName");
assertFalse(schema.isValid(human));

// Валидация с разными типами данных
Map<String, BaseSchema<?>> mixedSchemas = new HashMap<>();
mixedSchemas.put("name", validator.string().required());
mixedSchemas.put("age", validator.number().required().positive());

MapSchema<Object> mixedSchema = validator.map();
mixedSchema.shape(mixedSchemas);

Map<String, Object> person = new HashMap<>();
person.put("name", "John");
person.put("age", 20);
assertTrue(mixedSchema.isValid(person));

// Ошибка: возраст отрицательный
person.put("age", -5);
assertFalse(mixedSchema.isValid(person));
```