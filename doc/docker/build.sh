#!/bin/bash

docker rmi local/frostmourne:0.2
docker build -t local/frostmourne:0.2 -f Dockerfile .
