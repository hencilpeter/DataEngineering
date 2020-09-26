#!/bin/sh

#change the directory to current working directory
cd "$(dirname "$0")"

java -jar ./trade.process.svc-0.0.1-SNAPSHOT.jar
