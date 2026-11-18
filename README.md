<div align="center">

# Lilith's Throne — Русская локализация (RU)

**Неофициальный проект полной русской локализации RPG *Lilith's Throne* с открытым исходным кодом**

[![Origin](https://img.shields.io/badge/Оригинал-Innoxia%2Fliliths--throne--public-purple?style=flat-square)](https://github.com/Innoxia/liliths-throne-public)
[![Branch](https://img.shields.io/badge/Ветка-dev%20(0.4.11.9)-blue?style=flat-square)](https://github.com/SlopCoreGames/liliths-throne-public-RUS/tree/dev)
[![Java](https://img.shields.io/badge/Java-11%20%2B%20JavaFX%2018-orange?style=flat-square)](#сборка-из-исходников)
[![LLM](https://img.shields.io/badge/Перевод-Gemma%204%2026B%20A4B%20(Local)-green?style=flat-square)](#как-устроен-пайплайн-перевода)
[![Status](https://img.shields.io/badge/Статус-В%20процессе%20локализации-yellow?style=flat-square)](#статус-локализации)

</div>

---

> [!WARNING]
> **18+ / NSFW:** *Lilith's Throne* — взрослая текстовая RPG. Используя исходный код и сборку, вы соглашаетесь с условиями оригинального дисклеймера и лицензии проекта Innoxia.

---

## О проекте

Этот репозиторий — форк [`Innoxia/liliths-throne-public`](https://github.com/Innoxia/liliths-throne-public) (ветка `dev`, версия `0.4.11.9`), созданный для **полного перевода игры на русский язык** — не только внешних XML-сценариев, но и зашитого в Java-код интерфейса, атрибутов, квестов, статусов, заклинаний и генератора описаний.

Главная техническая особенность *Lilith's Throne* в том, что текст в игре не статичен: движок собирает предложения на лету из десятков переменных, тегов подстановки (`[npc.name]`, `[pc.verb(...)]`, `[npc.speech(...)]`) и условных блоков `#IF / #ELSEIF / #ELSE / #ENDIF`. Поэтому локализация строится как полноценный инженерный конвейер с сохранением синтаксиса движка, проверкой компиляции и последующей адаптацией русской морфологии.

---

## Статус локализации

- **Базовая сборка:** проект собирается на **Java 11 + Maven + JavaFX 18.0.2** в рабочий shaded JAR с принудительной кодировкой `UTF-8` и кириллическими шрифтами (`Carlito`, `DejaVu Sans`).
- **Переведено и проверено компиляцией:**
  - Приоритетные системные и UI-классы Java (атрибуты персонажа, меню, настройки, создание персонажа, пролог, квесты, статусы, перки, заклинания, типы урона, контроллеры интерфейса).
  - Начальные и ключевые XML-файлы (`res/txt/misc/`, `res/txt/quests/`, `res/txt/characters/`, `res/txt/places/dominion/`, часть локаций `fields/` и др.) — конвейер продолжает работу в полуавтоматическом режиме с инкрементальными коммитами в `dev`.
- **Запланировано после основного прогона текста:**
  - Доперевод оставшихся `.java`-классов с игровыми строками и диалогами.
  - Пост-обработка аргументов внутри составных тегов `[...]` (например, `[npc.verb(...)]`, `[npc.speech(...)]`).
  - Расширение русской морфологии ([`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java)) и парсера [`UtilText.java`](src/com/lilithsthrone/game/dialogue/utils/UtilText.java) для корректных падежей предметов/одежды и согласования глаголов по роду.
  - Визуальная проверка интерфейса (сетка кнопок действий 3×5) и финальная сборка релиза.

---

## Как устроен пайплайн перевода

Вся автоматизация находится в папке [`ru_tools/`](ru_tools/):

```
  XML (res/) / Java (src/)
         │
         ▼
  1. Экстракция и сплиттер директив (#IF / #ELSEIF / #ELSE / #ENDIF)
         │
         ▼
  2. Маскирование синтаксиса:
     • HTML-теги (<span ...>, <p>, <i>) ──▶ @@H0@@, @@H1@@...
     • Теги движка ([pc.name], [...])    ──▶ @@T0@@, @@T1@@...
         │
         ▼
  3. Дедупликация через Translation Memory (tm_cache.json)
         │
         ▼
  4. Локальный перевод (Gemma 4 26B A4B Q5_K_M @ llama.cpp)
     + глоссарий (GLOSSARY_RU.json) + строгая валидация масок и XML
         │
         ▼
  5. Инкрементальная проверка: mvn compile -DskipTests ──▶ git push dev
```

### Ключевые компоненты в `ru_tools/`

| Компонент | Назначение |
| --- | --- |
| [`translate_lt.py`](ru_tools/translate_lt.py) | Батч-переводчик XML с маскированием `@@H*@@` / `@@T*@@`, разбиением `#IF`-веток, проверкой валидности XML (`ElementTree`), защитой от эха и кэшем `tm_cache.json`. |
| [`translate_java_lt.py`](ru_tools/translate_java_lt.py) | Безопасный экстрактор и переводчик строковых литералов из `.java` (трогает только UI/диалоговый текст, не затрагивая пути, CSS, идентификаторы и флаги). |
| [`queue_worker.py`](ru_tools/queue_worker.py) | Приоритетный воркер очереди (`queue.json`), ведущий учёт завершённых файлов в `progress.json` и энергопотребления GPU в `energy_stats.json`. |
| [`GLOSSARY_RU.json`](ru_tools/GLOSSARY_RU.json) | Единый словарь терминов, имён и правил сохранения тегов. |
| [`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java) | Модуль склонения русских существительных и прилагательных (винительный падеж и дальнейшее расширение под инвентарь/экипировку). |

### Локальная модель и железо

Перевод выполняется **полностью локально и без цензуры** на рабочей станции:
- **Модель:** `Gemma-4-26B-A4B-It-Abliterated` (`Q5_K_M`) на базе `llama.cpp` (`llama-server.exe`, CUDA, `--reasoning off`, `temperature: 0.25`).
- **Термозащита по HotSpot:** встроенный в `translate_lt.py` `adaptive_thermal_guard` опрашивает датчики GPU напрямую через `nvapi64.dll` (`NvAPI_GPU_GetThermalSensors`), не допуская перегрева GPU Hot Spot и памяти при длительных ночных прогонах.

### Эффективность конвейера и отсутствие простоя GPU

1. **100% слоёв модели в VRAM (`-ngl 99 -c 4096 -np 1`):** вся модель целиком загружается в видеопамять RTX 3090 (`~22.6 ГБ` / `24 ГБ`), исключая узкое место шины DDR3 и обеспечивая стабильные `65–80 tok/s`.
2. **Непрерывная очередь без ожидания человека (`queue_worker.py`):** воркер сам перебирает приоритетные файлы, сбрасывает прогресс в `progress.json` после каждого файла и автоматически восстанавливается после любых пауз.
3. **Пакетная подача + Translation Memory (`tm_cache.json`):** строки объединяются в JSON-батчи до `1600` символов (до 10 фрагментов за один запрос), а повторяющиеся фразы мгновенно подставляются из локального кэша (`30 000+` записей), не тратя GPU-время впустую.
4. **Адаптивный термоконтроль вместо жёстких простоев:** вместо длинных холостых пауз используется короткое сглаживание (`0.5 с` при `HotSpot >= 92°C`) и точечное охлаждение только при достижении порога `98°C -> 86°C` — видеокарта почти непрерывно держит ~`70%` полезной нагрузки (`~290–308 Вт`) без перегрева.
5. **Инкрементальная верификация:** каждый час компиляция проверяется через `mvn compile -DskipTests` и фиксируется в GitHub.

---

## Сборка из исходников

Для сборки требуются **JDK 11** и **Apache Maven 3.x** (кодировка исходников — `UTF-8`):

```bash
# Проверка компиляции
mvn compile -DskipTests

# Сборка runnable shaded JAR в target/Lilith's Throne (win)/
mvn package -DskipTests
```

---

## Благодарности

- **[Innoxia](https://github.com/Innoxia)** — огромная благодарность автору *Lilith's Throne* за титанический труд, открытый исходный код ([`Innoxia/liliths-throne-public`](https://github.com/Innoxia/liliths-throne-public)), продуманную архитектуру тегов и мир игры.
- **Команде `llama.cpp` и сообществу открытых LLM** — за быстрый локальный инференс и качественные открытые модели серии Gemma, позволяющие переводить огромные объёмы нецензурированного RPG-текста локально.
- **Авторам шрифтов Carlito и DejaVu Sans** — за отличную поддержку кириллицы, которая работает в клиенте без искажений.
- **Сообществу игроков и переводчиков** — за идеи, разбор особенностей движка и поддержку проекта.

---

<details>
<summary>Оригинальный Readme / Original Notice</summary>

This is the source code for Lilith's Throne.

You must agree to the terms of the attached disclaimer and abide by the terms of the license if you wish to view this source code.

Dev-note: This project is relying on JavaFX which isn't included in the Openjdk, which is often the common choice for Linux enthusiasts. If you're using Linux, please make sure to use the Oracle JDK to build this project or install OpenJFX.

Copyright 2016 Innoxia (innoxia7@gmail.com) all rights reserved.

</details>
