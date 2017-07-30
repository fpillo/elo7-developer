#!/bin/bash

curl='/usr/bin/curl'
elastic_url='http://localhost:9200'
index='/search'
args='-X PUT -H "Accept: application/json"'

echo "Setup elastic enviroment..."

echo "Creating index..."
response="$($curl --silent --write-out 'HTTPSTATUS:%{http_code}' $args $elastic_url$index)"
status=$(echo $response | tr -d '\n' | sed -e 's/.*HTTPSTATUS://')
echo $status

if [ $status -eq 200 ]
then

echo "adding mapping..."
mapping_url='/_mapping/movies'
request_body=$(< <(cat <<EOF
{
  "properties": {
    "title": {
      "type": "text",
	  "analyzer": "english"
    },
	"genres": {
      "type": "text",
	  "analyzer": "english"
    },
	"grade": {
	  "type": "float"
	}
  }
}
EOF
))

args='-i -X PUT -H "Accept: application/json" -H "Content-Type: application/json"'
result="$($curl  $args -d "$request_body" $elastic_url$index$mapping_url)"
echo $result

else
echo “index already exists...”
fi