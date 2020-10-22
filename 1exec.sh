#!/bin/bash

mvn package
hadoop lab2.AirportApp flightData.csv airportData.csv output
