#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# aggiorna i db 

echo Updating BETTERMUSIC databases 

source do-update-albums.sh 
source do-update-recensioni.sh
source do-update-connessioni.sh
 
