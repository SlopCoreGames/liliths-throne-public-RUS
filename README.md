<div align="center">

# Lilith's Throne — Полная русская локализация (RU v1.0)

**Неофициальный проект 100% русской локализации RPG *Lilith's Throne (v0.4.11.9)* с открытым исходным кодом**

[![Origin](https://img.shields.io/badge/Оригинал-Innoxia%2Fliliths--throne--public-purple?style=flat-square)](https://github.com/Innoxia/liliths-throne-public)
[![Branch](https://img.shields.io/badge/Ветка-dev%20(0.4.11.9)-blue?style=flat-square)](https://github.com/SlopCoreGames/liliths-throne-public-RUS/tree/dev)
[![Java](https://img.shields.io/badge/Java-11%20%2B%20JavaFX%2018-orange?style=flat-square)](#сборка-из-исходников)
[![LLM](https://img.shields.io/badge/Перевод-Gemma%204%2026B%20A4B%20(Local)-green?style=flat-square)](#как-устроен-пайплайн-перевода)
[![Status](https://img.shields.io/badge/Статус-100%25%20Полный%20перевод%20(XML%20%2B%20Java)-brightgreen?style=flat-square)](#статус-локализации-v10)
[![Release](https://img.shields.io/badge/Release-v1.0-blue?style=flat-square)](https://github.com/SlopCoreGames/liliths-throne-public-RUS/releases/tag/v1.0)

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

Этот репозиторий — форк [`Innoxia/liliths-throne-public`](https://github.com/Innoxia/liliths-throne-public) (ветка `dev`, версия `0.4.11.9`), содержащий **полный 100% перевод игры на русский язык**: как всех внешних XML-файлов (`res/`), так и всего Java-кода (`src/`), включая интерфейс, редактор персонажа, диалоги, боевую систему, секс-сцены, управление рабами и динамический генератор описаний тела.

Главная техническая особенность *Lilith's Throne* в том, что текст в игре не статичен: движок собирает предложения на лету из десятков переменных, тегов подстановки (`[npc.name]`, `[pc.verb(...)]`, `[npc.speech(...)]`) и условных блоков `#IF / #ELSEIF / #ELSE / #ENDIF`. В нашей локализации реализованы полная русская морфология ([`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java)), таблица спряжения `667` глаголов ([`RuVerbTable.java`](src/com/lilithsthrone/utils/RuVerbTable.java)) и локализованный парсер [`UtilText.java`](src/com/lilithsthrone/game/dialogue/utils/UtilText.java).

---

## Статус локализации (`v1.0` — 100% Full Release)

- **Скачать готовую релизную сборку (Windows):** [Release `v1.0`](https://github.com/SlopCoreGames/liliths-throne-public-RUS/releases/tag/v1.0) (`Liliths_Throne_RU_v0.4.11.9_v1.0_Win.zip`: `Lilith's Throne-0.4.11.9.jar` + `res/` + `start_lt_ru.bat`, требуется **Java 11+**).
- **100% XML-контент (`2 448` XML-файлов):** переведены и провалидированы все `2 448` XML-файлов в `res/` (включая `res/dialogue`, `res/items`, `res/clothing`, `res/weapons`, `res/combatMove`, `res/race`, `res/statusEffects`, `res/tattoos`, `res/setBonuses`, `res/outfits`, `res/maps`, `res/mods`, книги и стикеры). `0` ошибок XML/DOM-парсинга, `0` утечек масок `@@`, `0` CJK-символов.
- **100% Java-исходники (`987` Java-файлов):** переведены и скомпилированы все `987` классов в `src/` (интерфейс, создание персонажа, пролог, все NPC и диалоги локаций, все `sexActions`, части тела и трансформации, перки, фетиши, квесты, заклинания, статусы, управление рабами и комнатами).
- **Русская морфология и парсер `UtilText`:**
  - Автоматическое спряжение и согласование `667` глаголов в тегах `[*.verb(...)]` через [`RuVerbTable.java`](src/com/lilithsthrone/utils/RuVerbTable.java).
  - Полная локализация местоимений, притяжательных форм, артиклей, связок `[*.is]`/`[*.has]`/`[*.do]`, единиц измерения, времени, стонов, запахов, рас и частей тела в [`UtilText.java`](src/com/lilithsthrone/game/dialogue/utils/UtilText.java).
  - Падежное склонение существительных и прилагательных в [`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java).
- **Интерфейс, шрифты и тестирование:**
  - Встроена поддержка кириллических шрифтов (`Carlito`, `DejaVu Sans`), адаптирована CSS-вёрстка кнопок (`webView_stylesheet.css`) и проверена целостность всех HTML/CSS-атрибутов и ID элементов управления.
  - Сборка протестирована автоматическим геймплей-пробом (`LtReleaseProbe`: загрузка всех реестров, генерация мира, парсинг диалогов и морфологии, цикл сохранения/загрузки) и визуальной проверкой GUI.
  - В базе памяти переводов (`ru_tools/tm_cache.json`) накоплено более **`70 000`** выверенных переводов.

---

## Как запустить игру

1. Скачайте и распакуйте архив [`Liliths_Throne_RU_v0.4.11.9_v1.0_Win.zip`](https://github.com/SlopCoreGames/liliths-throne-public-RUS/releases/tag/v1.0) в любую папку (папка `res/` должна находиться рядом с `.jar`-файлом).
2. Убедитесь, что у вас установлена **Java 11 или новее** (64-bit).
3. Запустите `start_lt_ru.bat` (или выполните `java -Dfile.encoding=UTF-8 -jar "Lilith's Throne-0.4.11.9.jar"`).

---

## Как устроен пайплайн перевода

Вся автоматизация находится в папке [`ru_tools/`](ru_tools/):

```
  XML (res/) / Java (src/)
         │
         ▼
  1. Белый список CDATA/XML-тегов и AST/токен-парсер Java + сплиттер #IF/#ELSEIF/#ELSE/#ENDIF
         │   (логика Nashorn, флаги, ID, CSS, пути и <formattingNames> защищены)
         ▼
  2. Маскирование синтаксиса:
     • Nashorn [#...]/[##...]       ──▶ @@S0@@... (с балансировкой вложенных скобок)
     • Глагольные теги [*.verb(..)] ──▶ @@V0@@...
     • Форматирование денег          ──▶ @@M0@@...
     • HTML-теги (<span ...>, <p>)   ──▶ @@H0@@...
     • Теги движка ([pc.name]...)    ──▶ @@T0@@...
         │
         ▼
  3. Дедупликация через Translation Memory (tm_cache.json, 70 000+ записей)
         │
         ▼
  4. Локальный перевод (Gemma 4 26B A4B Q5_K_M @ llama.cpp)
     + глоссарий (GLOSSARY_RU.json) + строгая проверка масок, HTML/CSS-атрибутов и валидности XML/Java
         │
         ▼
  5. Компиляция и автотесты: mvn clean package -DskipTests ──▶ LtReleaseProbe ──▶ Release v1.0
```

### Ключевые компоненты в `ru_tools/` и `src/`

| Компонент | Назначение |
| --- | --- |
| [`translate_lt.py`](ru_tools/translate_lt.py) | Батч-переводчик XML с белым списком тегов, балансированным маскированием Nashorn `[#...]`, `@@H*@@` / `@@T*@@`, проверкой валидности XML (`ElementTree`), защитой от утечки масок и кэшем `tm_cache.json`. |
| [`translate_java_v2.py`](ru_tools/translate_java_v2.py) | AST/токен-переводчик строковых литералов `.java` с защитой системных аргументов конструкторов (`CONSTRUCTOR_SKIP_ARGS`), флагов, путей, ID и CSS, сплиттером `#IF/#ELSE/#ENDIF` и проверкой компиляции. |
| [`queue_worker_java.py`](ru_tools/queue_worker_java.py) | Воркер пакетного перевода Java-кода с автоматической проверкой `mvn compiler:compile` и автопушем прогресса. |
| [`build_verb_table.py`](ru_tools/build_verb_table.py) | Генератор таблицы русских глагольных форм [`RuVerbTable.java`](src/com/lilithsthrone/utils/RuVerbTable.java) для динамических тегов `[*.verb(...)]`. |
| [`RuMorphology.java`](src/com/lilithsthrone/utils/RuMorphology.java) | Модуль склонения русских существительных и прилагательных и согласования форм в [`UtilText.java`](src/com/lilithsthrone/game/dialogue/utils/UtilText.java). |
| [`GLOSSARY_RU.json`](ru_tools/GLOSSARY_RU.json) | Единый словарь терминов, имён и правил сохранения тегов. |

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
