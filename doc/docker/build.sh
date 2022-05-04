#!/bin/bash

docker rmi local/frostmourne:0.6.2
docker build -t local/frostmourne:0.6.2 -f Dockerfile .
