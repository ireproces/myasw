#!/bin/bash
echo "Status pods di asw"
kubectl get pods -n asw
echo "Status pods di kafka"
kubectl get pods -n kafka

echo "Status servizi"
kubectl get services --all-namespaces