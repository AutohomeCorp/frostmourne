#!/bin/bash

docker rmi local/frostmourne:0.6
docker build -t local/frostmourne:0.6 -f Dockerfile .
