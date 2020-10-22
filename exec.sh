#!/bin/bash

mvn package
hadoop fs -rm -r output
hadoop lab2.AirportApp flightData.csv output
