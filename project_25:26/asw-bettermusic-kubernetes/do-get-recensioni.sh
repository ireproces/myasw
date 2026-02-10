#!/bin/bash

# trova tutte le recensioni 
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

echo "# trova tutte le recensioni" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/recensioni/recensioni --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 

