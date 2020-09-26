#!/bin/sh


#change the directory to current working directory
cd "$(dirname "$0")"

java -jar ./eureka.server-0.0.1-SNAPSHOT.jar
