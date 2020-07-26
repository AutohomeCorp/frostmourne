#!/bin/bash

docker rmi local/frostmourne:0.3
docker build -t local/frostmourne:0.3 -f Dockerfile .
