#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# aggiunge alcune connessioni 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"seguito\": \"Bruce Springsteen\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"RollingStone\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Carlo\", \"seguito\": \"Jazz\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"RollingStone\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

# elimina alcune connessioni 

curl -X DELETE "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"OndaRock\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 