#!/bin/bash

curl --header "Content-Type: application/json" --request POST --data "$1" http://localhost:8080/HelloWorld/service/register
