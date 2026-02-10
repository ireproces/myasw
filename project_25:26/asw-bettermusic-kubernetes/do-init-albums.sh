#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# inizializza il db degli album

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"The Dark Side of the Moon\", \"artista\": \"Pink Floyd\", 
           \"generi\": [ \"Rock\", \"Progressive Rock\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"The Wall\", \"artista\": \"Pink Floyd\", 
           \"generi\": [ \"Progressive Rock\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Selling England by the Pound\", \"artista\": \"Genesis\", 
           \"generi\": [ \"Progressive Rock\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Abbey Road\", \"artista\": \"The Beatles\", 
           \"generi\": [ \"Rock\", \"Pop\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Thriller\", \"artista\": \"Michael Jackson\", 
           \"generi\": [ \"Pop\", \"Contemporary R&B\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 

curl -X POST "http://${SERVICE_INGRESS_HOST}/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Kind of Blue\", \"artista\": \"Miles Davis\", 
           \"generi\": [ \"Jazz\" ] }" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}
echo 
