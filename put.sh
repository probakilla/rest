#!/bin/bash
curl -H 'Content-Type: application/json' -X PUT \
-d "$2" \
http://localhost:8080/HelloWorld/service/user/$1
