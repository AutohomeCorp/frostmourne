#!/bin/bash

docker rmi local/frostmourne:0.8
docker build -t local/frostmourne:0.8 -f Dockerfile .
