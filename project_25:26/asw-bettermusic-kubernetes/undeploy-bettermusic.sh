#!/bin/bash

echo Halting BetterMusic

kubectl delete namespace kafka

kubectl delete -f asw-bettermusic-deployment.yaml
