#!/bin/bash

# trova tutti gli album
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

echo "# trova tutti gli album" 
echo $(curl -s ${SERVICE_INGRESS_HOST}/album/album --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo