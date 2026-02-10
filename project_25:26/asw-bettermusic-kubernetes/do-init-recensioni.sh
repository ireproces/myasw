#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# inizializza il db delle recensioni 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"The Dark Side of the Moon\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Il lato buio dell'animo umano\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"The Wall\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Le ossessioni di Pink\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"Abbey Road\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"Una collezione di canzoni superbe\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"The Dark Side of the Moon\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Leggendario!\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Abbey Road\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"L'album dei Beatles più amato\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Thriller\", \"artistaAlbum\": \"Michael Jackson\", 
	       \"sunto\": \"The King of Pop!\", \"testo\": \"Bla bla bla...\" }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 