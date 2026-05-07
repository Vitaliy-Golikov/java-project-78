# Путь к gradlew.bat внутри папки app
GRADLE = cd app && gradlew.bat

# Цель по умолчанию
all: build

# Сборка проекта
build:
	$(GRADLE) build

# Запуск тестов
test:
	$(GRADLE) test --stacktrace

# Запуск конкретного теста
test-class:
	$(GRADLE) test --tests $(TEST)

# Запуск приложения
run:
	$(GRADLE) run

# Очистка
clean:
	$(GRADLE) clean

# Справка
help:
	@echo "Доступные команды:"
	@echo "  make build    - сборка проекта"
	@echo "  make test     - запуск всех тестов"
	@echo "  make clean    - очистка"
	@echo "  make run      - запуск программы"

.PHONY: all build test test-class run clean help