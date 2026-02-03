# EEG Data Chat Server

> **Sugerowana nowa nazwa projektu:** `EEG-Data-Chat-Server` lub `MultiClient-Chat-EEG-System`

## 📋 O Projekcie

Ten projekt został stworzony jako **zadanie programistyczne mające na celu rozwinięcie umiejętności programowania** w zakresie:
- Tworzenia aplikacji klient-serwer w Javie
- Programowania wielowątkowego
- Pracy z bazami danych SQLite
- Obsługi socketów sieciowych
- Przetwarzania danych z plików CSV

Jest to system komunikacji klient-serwer z możliwością przechowywania danych EEG (elektroencefalografia) w bazie danych SQLite.

## 🎯 Funkcjonalność

Projekt składa się z kilku głównych komponentów:

### 1. **Serwer Chat** (`Server`)
- Nasłuchuje na porcie 8020
- Obsługuje wielu klientów jednocześnie (multi-threaded)
- Umożliwia broadcast wiadomości do wszystkich klientów
- Obsługuje prywatne wiadomości między klientami
- Zarządza połączeniami klientów (dodawanie/usuwanie)

### 2. **Klient Chat** (`Client`)
- Łączy się z serwerem na localhost:8020
- Działa w dwóch wątkach:
  - Wątek odczytu: odbiera wiadomości z serwera
  - Wątek zapisu: wysyła wiadomości do serwera
- Umożliwia zakończenie połączenia komendą "bye"

### 3. **Kreator Bazy Danych** (`DataBaseCreator`)
- Tworzy bazę danych SQLite do przechowywania danych EEG
- Struktura tabeli `user_eeg`:
  - `id` - klucz główny (auto increment)
  - `username` - nazwa użytkownika
  - `electrode_number` - numer elektrody
  - `image` - obraz danych
- Funkcje do tworzenia i usuwania bazy danych

### 4. **Dane CSV**
- Plik `test01.csv` zawiera przykładowe dane numeryczne (prawdopodobnie pomiary EEG z różnych elektrod)
- Zawiera dwa zestawy danych pomiarowych

## 🚀 Uruchomienie

### Wymagania
- Java 22 lub nowsza
- Maven
- SQLite (dla funkcjonalności bazy danych)

### Uruchomienie Serwera
```bash
mvn compile
mvn exec:java -Dexec.mainClass="pl.G0bi74.Server.ServerMain"
```

### Uruchomienie Klienta
```bash
mvn compile
mvn exec:java -Dexec.mainClass="pl.G0bi74.Client.ClientMain"
```

### Tworzenie Bazy Danych
```bash
mvn compile
mvn exec:java -Dexec.mainClass="pl.G0bi74.DataBaseCreator.Creator"
```

**Uwaga:** Przed uruchomieniem kreatora bazy danych, zmień ścieżkę w pliku `Creator.java` (linia 13) na odpowiednią dla Twojego systemu.

## 📁 Struktura Projektu

```
RewiewProject1/
├── src/
│   └── main/
│       └── java/
│           └── pl/
│               └── G0bi74/
│                   ├── Main.java                    # Główna klasa (obecnie pusta)
│                   ├── Client/
│                   │   ├── ClientMain.java         # Punkt wejścia klienta
│                   │   ├── Client.java             # Logika klienta
│                   │   └── ServerHandler.java      # Obsługa komunikacji z serwerem
│                   ├── Server/
│                   │   ├── ServerMain.java         # Punkt wejścia serwera
│                   │   ├── Server.java             # Logika serwera
│                   │   └── ClientHandler.java      # Obsługa połączeń klientów
│                   └── DataBaseCreator/
│                       └── Creator.java            # Kreator bazy danych
├── test01.csv                                      # Przykładowe dane EEG
├── pom.xml                                         # Konfiguracja Maven
└── README.md                                       # Ten plik

```

## 🛠️ Technologie

- **Język:** Java 22
- **Build Tool:** Maven
- **Baza Danych:** SQLite (JDBC)
- **Networking:** Java Socket API
- **Wielowątkowość:** Java Threads

## 📝 Szczegóły Implementacji

### Komunikacja Klient-Serwer
- Wykorzystuje `ServerSocket` i `Socket` do komunikacji TCP/IP
- Port domyślny: 8020
- Każde połączenie klienta jest obsługiwane w osobnym wątku

### Zarządzanie Klientami
- Serwer przechowuje mapę aktywnych klientów (`HashMap<String, ClientHandler>`)
- Możliwość sprawdzenia, czy klient o danej nazwie jest już połączony
- Funkcje broadcast i prywatnych wiadomości

### Przechowywanie Danych
- Baza danych SQLite do przechowywania danych EEG użytkowników
- JDBC do komunikacji z bazą danych
- Automatyczne tworzenie tabeli jeśli nie istnieje

## 🎓 Cele Edukacyjne Projektu

Projekt został zrealizowany w celu nauki i praktyki:
1. **Programowania sieciowego** - implementacja komunikacji klient-serwer
2. **Wielowątkowości** - obsługa wielu klientów jednocześnie
3. **Baz danych** - integracja SQLite z aplikacją Java
4. **Struktury projektów Maven** - organizacja kodu i zarządzanie zależnościami
5. **Best practices** - pisanie czystego, modularnego kodu

## 📌 Uwagi

- Klasa `Main.java` jest obecnie pusta i może służyć jako punkt wejścia dla przyszłej rozbudowy
- W pliku `Server.java` znajduje się zakomentowany kod - prawdopodobnie wcześniejsze iteracje implementacji
- Ścieżka do bazy danych w `Creator.java` jest zakodowana na stałe i wymaga dostosowania

## 🔮 Możliwe Rozszerzenia

- Dodanie GUI (Swing lub JavaFX)
- Implementacja faktycznej obsługi danych EEG z pliku CSV
- Uwierzytelnianie użytkowników
- Szyfrowanie komunikacji
- Logi serwera
- Testy jednostkowe

## 👤 Autor

**G0bi74**

---

*Projekt stworzony w celach edukacyjnych jako ćwiczenie programistyczne.*
