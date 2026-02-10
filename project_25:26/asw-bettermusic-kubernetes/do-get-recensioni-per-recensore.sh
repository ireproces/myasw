#!/bin/bash

# trova tutte le recensioni di un certo recensore  
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: recensore"
	exit 1 
fi

# se un recensore contiene spazi deve essere racchiuso tra virgolette 
RECENSORE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le recensioni del recensore $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/recensioni/cercarecensioni/recensore/$RECENSORE --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 
