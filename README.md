Itt az év vége, és az iskolák szeretnének bizonyos információkat megkapni a diákok
eredményeivel kapcsolatban. Téged bíztak meg ezeknek az információknak a kikérésével.
A feladatod részletei a következők:

# Adatbázis

Az adatbázisban egyetlen tábla van `student_scores` néven, az alábbi oszlopokkal:

- `city` varchar
- `student_name` varchar
- `score` int

Egy diáknak több eredménye is lehet, ilyenkor az több rekordban lesz rögzítve. Például:

|      city     | student_name | score |
|---------------|--------------|------:|
| Washington    | Anne Small   | 40    |
| New York City | John Doe     | 20    |
| Boston        | Clark Kent   | 20    |
| Texas         | Mark Rover   | 5     |
| Washington    | Anne Small   | 25    |

*FONTOS:* Nem kell adatbázist elindítani vagy létrehozni. Az adatbázist a teszt esetek automatikusan létrehozzák ls feltöltik adatokkal, nektek csak kapcsolódni kell hozzá a megadott adatok alapján (DB_URL, USERNAME, PASSWORD) és lekérdezni belőle a szükséges adatokat

# Java alkalmazás

Implementáld a `StudentScores` osztályt, ami konstruktor paraméterben megkapja az adatbázis eléréshez szükséges adatokat (url, user, password). Az osztályban hozd létre az alábbi metódusokat:

- `getCityWithHighestScore()`: adja vissza annak a városnak a nevét, ahol a diákok összegzett eredménye a legnagyobb
- `getMostActiveStudent()`: adja vissza annak a diáknak a nevét, akihez a legtöbb eredmény tartozik (nem feltétlenül a legnagyobb eredmény)

Ha senkinek sincs eredménye, vagy hiba történik, akkor adjon vissza mindkét metódus üres `String`-et.
Ha döntetlen az állás, akkor azt a várost vagy diákot kell visszaadni, amelyik előbb áll az ábécében.

A versenyzők ideiglenes tárolásához használhatod az előre készített `City` osztályt.