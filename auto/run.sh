#!/bin/bash
set -euo pipefail

cd "$(dirname "$0")/.."

docker run --rm -u gradle:jdk19-jammy -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle clean build distZip

docker-compose build --no-cache
docker-compose run --service-ports service