#!/bin/bash

docker rmi local/frostmourne:0.7.1
docker build -t local/frostmourne:0.7.1 -f Dockerfile .
