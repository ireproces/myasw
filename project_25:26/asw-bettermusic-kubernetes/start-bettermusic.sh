#!/bin/bash
echo Starting Bettermusic
./kafka/kubernetes/deploy-kafka.sh
cd "$(dirname "$0")"
kubectl apply -f asw-bettermusic-deployment.yaml