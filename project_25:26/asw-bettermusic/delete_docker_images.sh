#!/bin/bash

echo Deleting all BetterMusic Docker images

docker image rm bettermusic-album
docker image rm bettermusic-api-gateway
docker image rm bettermusic-recensioni-seguite
docker image rm bettermusic-connessioni
docker image rm bettermusic-recensioni
docker image rm bitnamilegacy/kafka:4.0.0-debian-12-r10
docker image rm hashicorp/consul
docker image rm postgres:16