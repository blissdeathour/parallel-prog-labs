#!/bin/bash

hadoop fs -rm -r output
hadoop lab2.AirportApp flightData.csv output
rm -rf parallel-prog-labs/output/*
hadoop fs -copyToLocal output
