# Streaming Chatbot mit Java und Spring Boot

Dieses Projekt implementiert einen Streaming-Chatbot mit Java, Spring Boot und WebSockets, um eine Echtzeit-Kommunikation zwischen dem Benutzer und einer KI zu ermöglichen. Das Frontend verwendet HTML, JavaScript und Tailwind CSS für die Benutzeroberfläche.

## Voraussetzungen

- Java 17 (oder höher)
- Spring Boot
- WebSocket-Abhängigkeit
- Maven oder Gradle (Build-Management-Tool)
- Node.js und npm (für Tailwind CSS)

## Projektübersicht

Das Projekt verwendet Spring Boot, um eine WebSocket-Verbindung für Echtzeit-Kommunikation zu implementieren. Der Benutzer kann eine Nachricht an den Chatbot senden, und die KI verarbeitet diese Nachricht und sendet eine Antwort zurück. Die Kommunikation erfolgt live über WebSockets, wodurch die Interaktion nahtlos wirkt.

### Wichtige Komponenten

1. **Spring Boot Setup**: Das Projekt wird mit Spring Initializr erstellt und verwendet Spring Boot, Spring Web und WebSocket-Abhängigkeiten.
2. **WebSocket-Handler**: Die Klasse `ChatWebSocketHandler` wird verwendet, um Textnachrichten über WebSockets zu empfangen und zu senden.
3. **Frontend**: Das Frontend besteht aus HTML, JavaScript und Tailwind CSS und ermöglicht die Benutzerinteraktion sowie die Anzeige von KI-Antworten.
4. **KI-Integration mit Ollama**: Das Projekt enthält eine Anbindung an die Ollama-API, die die KI-Antworten generiert.

## Einrichtung

### 1. Projekt Setup

- Gehe zu [Spring Initializr](https://start.spring.io/).
- Wähle **Maven** als Build-Tool und **Java** als Sprache.
- Füge folgende Abhängigkeiten hinzu:
  - Spring Web
  - WebSocket
  - Spring Boot DevTools (optional für Entwicklung)

### 2. Tailwind CSS installieren

Um Tailwind CSS für die Gestaltung zu verwenden:

- Stelle sicher, dass **Node.js** und **npm** installiert sind.
- Navigiere zum Verzeichnis des Frontends und installiere Tailwind CSS:
  ```sh
  npm install -D tailwindcss
  npx tailwindcss init
  ```
- Konfiguriere die Datei `tailwind.config.js`, um den Pfad zu deinen HTML-Dateien hinzuzufügen.

### 3. Spring Boot Anwendung starten

- Erstelle eine Main-Klasse namens `StreamingChatApplication` und starte die Anwendung.
- Konfiguriere WebSockets in der Klasse `WebSocketConfig` und registriere deinen `ChatWebSocketHandler`.

### 4. Frontend entwickeln

- Die HTML-Datei enthält die Benutzeroberfläche, um Nachrichten einzugeben und an den WebSocket-Server zu senden.
- Das JavaScript sorgt dafür, dass die Nachrichten zwischen Client und Server ausgetauscht werden und Benutzer- sowie KI-Nachrichten richtig im Chatfenster angezeigt werden.

### 5. Ollama KI-Integration

- Die Klasse `OllamaService` stellt eine Verbindung zur Ollama-API her, um Benutzeranfragen zu verarbeiten und die KI-Antworten zurückzugeben.

## Verwendung

1. Starte die Spring Boot-Anwendung.
2. Öffne die HTML-Datei im Browser.
3. Gib eine Nachricht in das Eingabefeld ein und drücke **Senden** oder die **Enter-Taste**, um die Nachricht zu senden.
4. Die KI-Antwort wird in Echtzeit angezeigt.

## Features

- **WebSocket-Kommunikation**: Senden und Empfangen von Nachrichten in Echtzeit.
- **Tailwind CSS Styling**: Modernes und responsives Design für die Benutzeroberfläche.
- **KI-Integration**: Echtzeit-Antworten durch Ollama KI-API.
- **Avatar**: Ein Avatar für die KI

## Ordnerstruktur

- **src/main/java**: Enthält die Java-Quellcodes, einschließlich WebSocket-Handler und Ollama-Service.
- **src/main/resources/static**: Enthält statische Ressourcen wie HTML, CSS und Bilder (z.B. Avatare und Ladeanimationen).
- **src/main/resources/templates**: HTML-Dateien für das Frontend.

## Anpassungen

- Die Nachricht des Benutzers wird rechts und die Antwort der KI links im Chatfenster angezeigt.
- Ein Lade-Spinner wird während der Bearbeitung der Anfrage angezeigt, um eine visuelle Rückmeldung zu geben.

## Installation und Start

1. **Repository klonen**: Klone das Repository in dein lokales System.
2. **Abhängigkeiten installieren**: Stelle sicher, dass alle Abhängigkeiten (Java, npm) installiert sind.
3. **Spring Boot-Anwendung starten**: Starte die Anwendung über deine IDE oder per Kommandozeile:
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Frontend öffnen**: Öffne die HTML-Datei in deinem Browser, um den Chatbot zu verwenden.

## Bekannte Probleme

- **HTML-Tags in WebSocket-Nachrichten**: Die Darstellung von HTML-Tags in WebSocket-Nachrichten könnte problematisch sein. Es wurde sichergestellt, dass alle Nachrichten korrekt angezeigt werden.

## Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Weitere Informationen findest du in der `LICENSE` Datei.

