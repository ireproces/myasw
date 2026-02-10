#!/bin/bash

# trova tutti gli album di un artista
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: artista"
	exit 1 
fi

# se un artista contiene spazi deve essere racchiuso tra virgolette 
ARTISTA=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutti gli album dell'artista $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/album/cercaalbum/artista/$ARTISTA --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 
