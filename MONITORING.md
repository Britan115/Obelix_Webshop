# Monitoring

Prometheus und Grafana Monitoring für Obelix Webshop.

## Docker Compose

```powershell
docker-compose up -d
```

- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin/admin)

## Endpoints

- Quarry Metrics: http://localhost:8081/actuator/prometheus
- Webshop Metrics: http://localhost:8080/actuator/prometheus
