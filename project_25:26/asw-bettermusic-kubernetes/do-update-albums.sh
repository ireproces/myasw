#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# aggiunge alcuni album

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Born to Run\", \"artista\": \"Bruce Springsteen\", 
           \"generi\": [ \"Rock\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Sgt. Pepper's Lonely Hearts Club Band\", \"artista\": \"The Beatles\", 
           \"generi\": [ \"Rock\", \"Pop\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

