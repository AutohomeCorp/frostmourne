#!/bin/bash

docker rmi local/frostmourne:0.7
docker build -t local/frostmourne:0.7 -f Dockerfile .
