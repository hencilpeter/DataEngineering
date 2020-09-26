#!/bin/bash
#This script can be used to create dynamic trades.
#This script accepts one parameter for number of trades. 
echo "first parameter : "$0
echo "second parameter : "$1

TRADECOUNT=$1
echo "-"$1"-"
if [ $1 == ""] ; then
   echo "No parameters passed. Default 10 trades will be created."
   TRADECOUNT=10
fi

COUNT=1

while true; do
echo "Creating Trade : "$COUNT
curl -X POST http://192.168.0.103:8081/trade-data
sleep 1 #sleep 1 second
COUNT=$[$COUNT+1]

if ( [ $COUNT -gt $TRADECOUNT ] );then
echo "Created Trades Count : "$TRADECOUNT
break
fi

done


echo "Script Exiting....Good Bye..."