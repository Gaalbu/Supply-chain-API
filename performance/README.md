# Teste de carga local

O cenário consulta um pacote existente com cinco usuários virtuais por 30 segundos. Ele serve para comparação local e não representa capacidade de produção.

```bash
TRACKING_CODE=PKG001 VUS=5 DURATION=30s k6 run package-tracking.js
```

Registre versão do k6, hardware, cenário, total de requests, p50, p95, p99 e taxa de erro junto do resultado. O benchmark não foi executado nesta rodada porque k6 não estava instalado no ambiente de auditoria.
