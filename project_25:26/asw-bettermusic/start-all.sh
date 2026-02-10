#!/bin/bash

echo Starting BetterMusic services

docker compose up -d --build --force-recreate

echo BetterMusic services started

