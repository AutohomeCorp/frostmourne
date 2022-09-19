#!/bin/bash

docker rmi local/frostmourne:1.0
docker build -t local/frostmourne:1.0 -f Dockerfile .
