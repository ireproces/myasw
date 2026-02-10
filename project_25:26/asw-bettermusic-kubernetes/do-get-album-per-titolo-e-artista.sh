#!/bin/bash
SERVICE_HOST=kube-2
INGRESS_PORT=80
SERVICE_INGRESS_HOST=bettermusic.asw.io

# trova un album dato titolo e artista 

if [ $# -eq 0 ]
  then
    echo "Mancano i parametri: titolo artista"
	exit 1 
fi

# se titolo o artista contiene spazi deve essere racchiuso tra virgolette 
TITOLO=$(echo $1 | sed -e "s/ /%20/g") 
ARTISTA=$(echo $2 | sed -e "s/ /%20/g") 

echo "# l'album con titolo $1 e artista $2" 
echo $(curl -s "${SERVICE_INGRESS_HOST}/album/cercaalbum?titolo=$TITOLO&artista=$ARTISTA" --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT})
echo 
