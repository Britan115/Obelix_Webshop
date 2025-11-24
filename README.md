# Obelix & Co. Webshop 🪨

Gradle subprojects sind bereits in place.

## Voraussetzungen

- JDK 21 oder höher
- Gradle (wird über Gradle Wrapper bereitgestellt)

## Projekt starten

### Projekt bauen

```powershell
.\gradlew.bat clean build -x test
```

### Webshop starten

```powershell
.\gradlew.bat :obelix-webshop:bootRun
```

Die Anwendung läuft dann auf http://localhost:8080

## API-Endpunkte

### Webshop (Port 8080)
- GET /api
- PUT /api/basket/offer
- DELETE /api/basket
- POST /api/basket/buy/{menhirId}
- GET /actuator/health
- GET /actuator/prometheus

### Quarry (Port 8081)
- GET /api/menhirs
- GET /api/menhirs/{menhirId}
- DELETE /api/quarry/{menhirId}
- GET /actuator/health
- GET /actuator/prometheus

## Projektstruktur

- obelix-common
- obelix-quarry-api
- obelix-quarry-impl
- obelix-webshop