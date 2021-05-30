#!/bin/bash

docker rmi local/frostmourne:0.5
docker build -t local/frostmourne:0.5 -f Dockerfile .
