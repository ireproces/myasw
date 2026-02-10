#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# esegue un insieme di interrogazioni di esempio 

echo "# tutti gli album"  
echo $(curl -s http://${SERVICE_INGRESS_HOST}/album/album --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# l'album 1"  
echo $(curl -s http://${SERVICE_INGRESS_HOST}/album/album/1 --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# l'album The Wall dei Pink Floyd"  
echo $(curl -s "http://${SERVICE_INGRESS_HOST}/album/cercaalbum?titolo=The%20Wall&artista=Pink%20Floyd" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutti gli album dei Pink Floyd"  
echo $(curl -s "http://${SERVICE_INGRESS_HOST}/album/cercaalbum/artista/Pink%20Floyd" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutti gli album di genere Progressive Rock"  
echo $(curl -s "http://${SERVICE_INGRESS_HOST}/album/cercaalbum/genere/Progressive%20Rock" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutte le recensioni"  
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni/recensioni --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# la recensione 1" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni/recensioni/1 --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutte le recensioni del recensore Woody" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni/cercarecensioni/recensore/Woody --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutte le recensioni dell'album 1" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni/cercarecensioni/album/1 --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# tutte le connessioni" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/connessioni/connessioni --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le connessioni di Alice" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/connessioni/connessioni/Alice --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le connessioni di Bob" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/connessioni/connessioni/Bob --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le connessioni di Carlo" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/connessioni/connessioni/Carlo --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le recensioni seguite da Alice" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Alice --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le recensioni seguite da Bob" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Bob --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# le recensioni seguite da Carlo" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Carlo --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq .
echo 

echo "# conta le recensioni seguite da Alice" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Alice --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq length 
echo 

echo "# conta le recensioni seguite da Bob" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Bob --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq length 
echo 

echo "# conta le recensioni seguite da Carlo" 
echo $(curl -s http://${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/Carlo --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT}) | jq length 
echo 
