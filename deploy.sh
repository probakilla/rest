#!/bin/bash

rm -rf ../td4/webapps/HelloWorld*
echo "Deleted archive"

cp HelloWorld.war ../td4/webapps/HelloWorld.war
echo "Archive deployed"
