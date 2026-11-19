<div align="center">

# Lilith's Throne — Русская локализация (RU)

**Неофициальный проект полной русской локализации RPG *Lilith's Throne* с открытым исходным кодом**

[![Origin](https://img.shields.io/badge/Оригинал-Innoxia%2Fliliths--throne--public-purple?style=flat-square)](https://github.com/Innoxia/liliths-throne-public)
[![Branch](https://img.shields.io/badge/Ветка-dev%20(0.4.11.9)-blue?style=flat-square)](https://github.com/SlopCoreGames/liliths-throne-public-RUS/tree/dev)
[![Java](https://img.shields.io/badge/Java-11%20%2B%20JavaFX%2018-orange?style=flat-square)](#сборка-из-исходников)
[![LLM](https://img.shields.io/badge/Перевод-Gemma%204%2026B%20A4B%20(Local)-green?style=flat-square)](#как-устроен-пайплайн-перевода)
[![Status](https://img.shields.io/badge/Статус-XML%20100%25%20%2B%20Сборка-brightgreen?style=flat-square)](#статус-локализации)
[![Release](https://img.shields.io/badge/Release-v0.4.11.9--ru--beta1-blue?style=flat-square)](https://github.com/SlopCoreGames/liliths-throne-public-RUS/releases/tag/v0.4.11.9-ru-beta1)

</div>

---

> [!WARNING]
> **18+ / NSFW:** *Lilith's Throne* — взрослая текстовая RPG в жанре тёмного городского фэнтези. Используя исходный код и сборку, вы соглашаетесь с условиями оригинального дисклеймера и лицензии проекта Innoxia.

---

## О чём эта игра и какие в ней кинки (18+)

*Lilith's Throne* — гигантская нелинейная текстовая 18+ RPG с пошаговыми боями, исследованием города и глубочайшим редактором внешности/тела персонажа. Практически все экстремальные или специфические темы **тонко настраиваются в меню настроек контента** до или во время игры (`PropertyValue`), а система фетишей отслеживает вкусы каждого NPC и самого игрока.

| Тег / Кинк | Понятное описание (что именно есть в игре) |
| --- | --- |
| **TF / Body Modification / Hyper** | Максимально детальные **трансформации тела**: смена расы, роста, объёма и пропорций вплоть до *Hyper*-размеров (грудь, пенис, бёдра, тестикулы, ягодицы, растяжение отверстий, мульти-органы). |
| **Furry / Monster Girls & Boys / Futanari** | Десятки **фурри- и монстр-рас** (демоны, лисы, волки, кошки, пауки/драйдеры, лошади/кентавры, коровы, драконы, аллигаторы, слизи и др.) и поддержка любых комбинаций гениталий, включая **футанари**, андрогинность и **кроссдрессинг** (*Crossdressing*). |
| **BDSM / Dom-Sub / Bondage / Sadomasochism** | **Доминирование и подчинение** (*Dom / Sub / Switch*), связывание и бондаж (*Bondage*), **садизм и мазохизм** (боль и унижение), контроль и запрет оргазмов (*Orgasm Denial / Edging*), а также **рабство** (владение рабами и попадание в плен). |
| **Breeding / Pregnancy / Impregnation / Egg-laying** | Оплодотворение и беременность как для игрока, так и для NPC (*Breeder / Impregnation / Pregnancy*), вынашивание потомства, инкубация яиц и развитие потомков в игре. |
| **Cum Play / Inflation / Lactation / Musk** | Обильные жидкости тела (**сперма, женская смазка, лактация и доение**, включая опциональный кинк *Udder / Мускус*), наполнение и заметное **раздувание живота** жидкостями (*Inflation*), а также **лишение девственности** (*Deflowering / Virginity*). |
| **Non-Con / Incest / Feral / Niche Fetishes (Отключаемые)** | Острые и нишевые фетиши: **принуждение** (*Non-Con*), **инцест** (*Incest*), **фетиш на ноги и подмышки** (*Foot / Armpit*), **эксгибиционизм и вуайеризм**, **бимбо** (*Bimbo*), *Size Queen* (огромные размеры), *Gape*, *Feral*, *Tail / Nipple / Urethral Penetration*, *Bad Ends* — **всё это можно отдельно включить или полностью отключить** в настройках игры. |

---

## О проекте

Этот репозиторий — форк [`Innoxia/liliths-throne-public`](https://github.com/Innoxia/liliths-throne-public) (ветка `dev`, версия `0.4.11.9`), созданный для **полного перевода игры на русский язык** — не только внешних XML-сценариев, но и зашитого в Java-код интерфейса, атрибутов, квестов, статусов, заклинаний и генератора описаний.

Главная техническая особенность *Lilith's Throne* в том, что текст в игре не статичен: движок собирает предложения на лету из десятков переменных, тегов подстановки (`[npc.name]`, `[pc.verb(...)]`, `[npc.speech(...)]`) и условных блоков `#IF / #ELSEIF / #ELSE / #ENDIF`. Поэтому локализация строится как полноценный инженерный конвейер с сохранением синтаксиса движка, проверкой компиляции и последующей адаптацией русской морфологии.

---

## Статус локализации

- **Скачать готовую бета-сборку (Windows):** [Release `v0.4.11.9-ru-beta1`](https://github.com/SlopCoreGames/liliths-throne-public-RUS/releases/tag/v0.4.11.9-ru-beta1) (`Lilith's Throne-0.4.11.9.jar` + `res/` + `start_lt_ru.bat`, требуется **Java 11+**).
- **Базовая сборка:** проект собирается на **Java 11 + Maven + JavaFX 18.0.2** в рабочий shaded JAR с принудительной кодировкой `UTF-8` и кириллическими шрифтами (`Carlito`, `DejaVu Sans`). Сборка проверена запуском GUI-клиента (`-Ddebug=true`) и рантайм-загрузкой всех **46/46** классов загрузчиков `res/` (`0` ошибок).
- **XML-контент (`100%` очереди):** переведено и провалидировано **`2 073` XML-файла** (`0` ошибок очереди, `0` ошибок XML/DOM-аудита), накоплено **`44 971`** записей в Translation Memory (`ru_tools/tm_cache.json`). Охвачены сюжетные диалоги (`1 369` узлов), квесты, NPC, локации, расы и подвиды (`93`), предметы (`296`), экипировка (`475`), оружие, эффекты, тату, боевые приёмы и секс-сцены (`1 073`).
- **Java-исходники (`src/`):** переведены и скомпилированы **`15` приоритетных системных и UI-классов** (атрибуты персонажа, меню, настройки, создание персонажа, пролог, квесты, статусы, перки, заклинания, типы урона, контроллеры интерфейса); остальные `.java`-классы игровых модулей (`npc/`, `dialogue/places/`, `sexActions/`, `body/`) пока остаются на английском и запланированы на следующий этап.
- **Запланировано для следующих версий:**
  - Доперевод оставшихся `.java`-классов с игровыми строками и диалогами.
  - Пост-обработка аргументов внутри составных тегов `[...]` (например, `[npc.verb(...)]`, `[npc.speech(...)]`).
  - Расширение русской морфологии ([`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java)) и парсера [`UtilText.java`](src/com/lilithsthrone/game/dialogue/utils/UtilText.java) для корректных падежей предметов/одежды и согласования глаголов по роду.
  - Полная игровая и визуальная проверка сцен перед финальным релизом.

---

## Как устроен пайплайн перевода

Вся автоматизация находится в папке [`ru_tools/`](ru_tools/):

```
  XML (res/) / Java (src/)
         │
         ▼
  1. Белый список CDATA/XML-тегов по каталогам + сплиттер #IF/#ELSEIF/#ELSE/#ENDIF
         │   (логика Nashorn, флаги, ID и <formattingNames> не затрагиваются)
         ▼
  2. Маскирование синтаксиса:
     • Nashorn [#...]/[##...]       ──▶ @@S0@@... (с балансировкой вложенных скобок)
     • Форматирование денег          ──▶ @@M0@@...
     • HTML-теги (<span ...>, <p>)   ──▶ @@H0@@...
     • Теги движка ([pc.name]...)    ──▶ @@T0@@...
         │
         ▼
  3. Дедупликация через Translation Memory (tm_cache.json)
         │
         ▼
  4. Локальный перевод (Gemma 4 26B A4B Q5_K_M @ llama.cpp)
     + глоссарий (GLOSSARY_RU.json) + строгая проверка отсутствия утечек масок и валидности XML
         │
         ▼
  5. Инкрементальная проверка: mvn compile / package -DskipTests ──▶ git push dev
```

### Ключевые компоненты в `ru_tools/`

| Компонент | Назначение |
| --- | --- |
| [`translate_lt.py`](ru_tools/translate_lt.py) | Батч-переводчик XML с белым списком тегов, балансированным маскированием Nashorn `[#...]`, `@@H*@@` / `@@T*@@`, проверкой валидности XML (`ElementTree`), защитой от утечки масок и кэшем `tm_cache.json`. |
| [`translate_java_lt.py`](ru_tools/translate_java_lt.py) | Безопасный экстрактор и переводчик строковых литералов из `.java` (трогает только UI/диалоговый текст, не затрагивая пути, CSS, идентификаторы и флаги). |
| [`queue_worker.py`](ru_tools/queue_worker.py) | Приоритетный воркер очереди (`queue.json`), ведущий учёт завершённых файлов в `progress.json` и энергопотребления GPU в `energy_stats.json`. |
| [`GLOSSARY_RU.json`](ru_tools/GLOSSARY_RU.json) | Единый словарь терминов, имён и правил сохранения тегов. |
| [`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java) | Модуль склонения русских существительных и прилагательных (винительный падеж и дальнейшее расширение под инвентарь/экипировку). |

### Локальная модель и железо

Перевод выполняется **полностью локально и без цензуры** на рабочей станции:
- **Модель:** `Gemma-4-26B-A4B-It-Abliterated` (`Q5_K_M`) на базе `llama.cpp` (`llama-server.exe`, CUDA, `--reasoning off`, `temperature: 0.25`).
- **Термозащита по HotSpot:** встроенный в `translate_lt.py` `adaptive_thermal_guard` опрашивает датчики GPU напрямую через `nvapi64.dll` (`NvAPI_GPU_GetThermalSensors`), не допуская перегрева GPU Hot Spot и памяти при длительных прогонах.

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
