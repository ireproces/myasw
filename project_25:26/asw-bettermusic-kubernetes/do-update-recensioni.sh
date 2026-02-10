#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# aggiunge alcune recensioni 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"Born to Run\", \"artistaAlbum\": \"Bruce Springsteen\", 
	       \"sunto\": \"Un capolavoro!\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Sgt. Pepper's Lonely Hearts Club Band\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"Con questo album, la musica pop cambia definitivamente aspetto e contenuti\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"RollingStone\", \"titoloAlbum\": \"Kind of Blue\", \"artistaAlbum\": \"Miles Davis\", 
	       \"sunto\": \"Uno degli album più importanti, influenti e popolari del Jazz\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 