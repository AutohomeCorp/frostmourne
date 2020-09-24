#!/bin/bash

docker rmi local/frostmourne:0.4
docker build -t local/frostmourne:0.4 -f Dockerfile .
