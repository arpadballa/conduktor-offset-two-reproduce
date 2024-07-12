#!/bin/bash

schemas=(
  "input-topic-value" "/avro/input.avsc"
  "output-topic-value" "/avro/output.avsc"
)

ls -l /avro

for ((i = 0; i < ${#schemas[@]}; i = i + 2)); do
  subject=${schemas[$i]}
  file=${schemas[$i + 1]}
  echo "File ${file} to subject ${subject}"

  jq '. | {schema: tojson}' ${file} |
    curl -X POST http://kafka-schema-registry:8081/subjects/${subject}/versions \
      -H "Content-Type:application/json" \
      -d @- \
      -w "\n"
done
