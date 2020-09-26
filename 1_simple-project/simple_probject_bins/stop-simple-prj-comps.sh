#!/bin/sh

echo "About to Kill Project Components...."

echo "Killing ticket publisher service...."
kill -9 $(jps|grep ticket.publisher.svc|cut -d ' ' -f 1)
sleep 1

echo "Killing trade process service...."
kill -9 $(jps|grep trade.process.svc|cut -d ' ' -f 1)
sleep 1

echo "Killing trade listener service...."
kill -9 $(jps|grep trade.listener.svc|cut -d ' ' -f 1)
sleep 1

echo "Killing trade booking service...."
kill -9 $(jps|grep trade.booking.svc|cut -d ' ' -f 1)
sleep 1

echo "Killing eureka naming service...."
kill -9 $(jps|grep eureka|cut -d ' ' -f 1)
sleep 1

echo "Killing kafka...."
kill -9 $(jps|grep Kafka|cut -d ' ' -f 1)
sleep 1

echo "Killing zookeeper ...."
kill -9 $(jps|grep QuorumPeerMain|cut -d ' ' -f 1)
sleep 1

echo "All  services are kiilled...Good Bye..."