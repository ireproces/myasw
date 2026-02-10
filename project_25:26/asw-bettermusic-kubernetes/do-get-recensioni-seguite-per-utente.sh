#!/bin/bash

# trova tutte le recensioni seguite da un certo utente 
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: utente"
	exit 1 
fi

# se l'utente contiene spazi deve essere racchiuso tra virgolette 
UTENTE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# le recensioni seguite da $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/$UTENTE --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 
