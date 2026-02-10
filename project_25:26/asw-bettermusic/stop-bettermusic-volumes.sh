#!/bin/bash

echo Halting BetterMusic services and volumes

docker compose down -v

echo BetterMusic services halted
