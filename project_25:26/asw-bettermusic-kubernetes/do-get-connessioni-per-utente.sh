#!/bin/bash

# trova tutte le connessioni per un certo utente  
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: utente"
	exit 1 
fi

# se un utente contiene spazi deve essere racchiuso tra virgolette 
UTENTE=$(echo $1 | sed -e "s/ /%20/g") 


echo "# tutte le connessioni per l'utente $1" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/connessioni/connessioni/$UTENTE --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 