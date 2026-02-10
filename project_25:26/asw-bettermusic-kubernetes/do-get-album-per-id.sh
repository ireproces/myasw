#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# trova un album dato l'id 

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: id"
	exit 1 
fi

# se un id contiene spazi deve essere racchiuso tra virgolette 
ID=$(echo $1 | sed -e "s/ /%20/g") 

echo "# l'album con id $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/album/album/$ID --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 