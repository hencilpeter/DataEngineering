#!/bin/sh
#java -jar ~/hencil-data/project/1_simple-project/simple_probject_bins/trade.booking.svc-0.0.1-SNAPSHOT.jar

#change the directory to current working directory
cd "$(dirname "$0")"

java -jar ./trade.listener.svc-0.0.1-SNAPSHOT.jar
