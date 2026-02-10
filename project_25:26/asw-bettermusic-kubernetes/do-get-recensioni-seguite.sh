#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# trova tutte le recensioni seguite degli utenti noti 

for UTENTE in Alice Bob Carlo Dario Elisa Francesca; do 
	echo "# le recensioni seguite da $UTENTE" 
	echo $(curl -s ${SERVICE_INGRESS_HOST}/recensioni-seguite/recensioniseguite/$UTENTE --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
	echo 
done 
