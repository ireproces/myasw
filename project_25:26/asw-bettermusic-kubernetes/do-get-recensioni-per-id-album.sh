#!/bin/bash

# trova tutte le recensioni relative a un certo album  
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: id-album"
	exit 1 
fi

# se l'id album contiene spazi deve essere racchiuso tra virgolette 
IDALBUM=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le recensioni per l'album $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/recensioni/cercarecensioni/album/$IDALBUM --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 