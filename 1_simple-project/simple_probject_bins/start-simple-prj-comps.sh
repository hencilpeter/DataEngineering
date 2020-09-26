#!/bin/sh

echo "Starting project components....."

echo "1. Starting Zookeeper. Script : 1-start-zookeeper.sh"
open -a "Terminal"  ./1-start-zookeeper.sh
sleep 1

echo "2. Starting Kafka. Script : 2-start-kafka.sh"
open -a "Terminal"  ./2-start-kafka.sh
sleep 2

echo "3. Starting Eureka Naming Server. Script : 3-start-eureka.sh"
open -a "Terminal"  ./3-start-eureka.sh
sleep 4

echo "4. Starting Trade Booking Service. Script : 4-start-trade-booking-svc.sh"
open -a "Terminal"  ./4-start-trade-booking-svc.sh
sleep 4

echo "5. Starting Trade Listener Service. Script :5-start-trade-listener-svc.sh"
open -a "Terminal" ./5-start-trade-listener-svc.sh
sleep 4

echo "6. Starting Trade Process Service. Script :6-start-trade-process-svc.sh"
open -a "Terminal" ./6-start-trade-process-svc.sh
sleep 4

echo "7. Starting Ticket Publisher Service. Script :7-start-ticket-publisher-svc.sh"
open -a "Terminal" ./7-start-ticket-publisher-svc.sh
sleep 4


sleep 2

echo "STARTED ALL THE SERVICES!!!!!!!"


