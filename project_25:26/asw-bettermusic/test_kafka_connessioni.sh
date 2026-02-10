#!/bin/bash
set -euo pipefail

echo "[1/4] POST connessione via API Gateway"
CONN_JSON=$(curl -s -X POST http://localhost:8080/connessioni/connessioni \
  -H 'Content-Type: application/json' \
  -d '{"utente":"Alessandro","seguito":"Pink Floyd","ruolo":"ARTISTA"}')
echo "$CONN_JSON"

CONN_ID=$(echo "$CONN_JSON" | jq -r '.id')
if [ -z "${CONN_ID:-}" ] || [ "$CONN_ID" = "null" ]; then
  echo "Errore: ID connessione non ottenuto"
  exit 1
fi

echo "[2/4] Attesa propagazione evento Kafka"
sleep 3

echo "[3/4] Verifica presenza su servizio Recensioni Seguite"
# PROVA 1: Rimuoviamo il raddoppio del path se il primo dava 404
curl -s "http://localhost:8080/recensioni-seguite/recensioniseguite/Alessandro" | jq

echo "[4/4] DELETE connessione e verifica rimozione"
curl -s -X DELETE http://localhost:8080/connessioni/connessioni \
  -H 'Content-Type: application/json' \
  -d '{"utente":"Alessandro","seguito":"Pink Floyd","ruolo":"ARTISTA"}'

echo "Attesa rimozione Kafka..."
sleep 3

echo "Verifica finale (lista vuota):"
curl -s "http://localhost:8080/recensioni-seguite/recensioniseguite/Alessandro" | jq

echo "OK: test completato"