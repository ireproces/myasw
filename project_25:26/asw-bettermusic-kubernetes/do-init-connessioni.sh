#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io
# inizializza il db delle connessioni 

# Alice segue solo artisti 
# Bob segue solo recensori 
# Carlo segue solo generi 
# Dario segue sia artisti che recensori 
# Elisa segue sia recensori che generi 
# Francesca segue sia artisti che generi 

# connessioni con artisti 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"seguito\": \"Pink Floyd\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"seguito\": \"The Beatles\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"The Beatles\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"Michael Jackson\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Francesca\", \"seguito\": \"Genesis\", \"ruolo\": \"ARTISTA\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

# connessioni con recensori 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"Woody\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"OndaRock\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"OndaRock\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Woody\", \"ruolo\": \"RECENSORE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

# connessioni con generi 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Carlo\", \"seguito\": \"Rock\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Carlo\", \"seguito\": \"Pop\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Pop\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Jazz\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Francesca\", \"seguito\": \"Progressive Rock\", \"ruolo\": \"GENERE\"}" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 
