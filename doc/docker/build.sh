#!/bin/bash

docker rmi local/frostmourne:0.9
docker build -t local/frostmourne:0.9 -f Dockerfile .
