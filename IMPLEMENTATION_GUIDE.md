# Implementierungs-Guide

## Aufgabenstellung erfüllt

Dieses Projekt implementiert einen Obelix Webshop mit separatem Quarry Microservice entsprechend den Anforderungen der M321 Aufgabenstellung.

### Umgesetzte Funktionen:

- ✅ Codesplit in vier Subprojekte
- ✅ Quarry Microservice ausgelagert
- ✅ Spring HTTP Proxy Client
- ✅ Distributed Tracing (Zipkin)
- ✅ Prometheus/Grafana Metriken
- ✅ Backwards compatibility der API Endpoints

### Start-Anleitung:

1. Monitoring Stack: `docker-compose up -d`
2. Quarry Service: `.\gradlew.bat :obelix-quarry-impl:bootRun`
3. Webshop: `.\gradlew.bat :obelix-webshop:bootRun`
