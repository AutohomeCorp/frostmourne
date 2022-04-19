#!/bin/bash

docker rmi local/frostmourne:0.6.1
docker build -t local/frostmourne:0.6.1 -f Dockerfile .
