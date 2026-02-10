#!/bin/bash

# trova tutti gli album di un genere
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: genere"
	exit 1 
fi

# se un genere contiene spazi deve essere racchiuso tra virgolette 
GENERE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutti gli album del genere $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/album/cercaalbum/genere/$GENERE --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 
