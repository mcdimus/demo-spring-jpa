#!/usr/bin/env bash

name="springjpa-psql"

docker rm -vf ${name} || true
docker run --name ${name} \
    -p 5500:5432 \
    -e POSTGRES_DB=springjpa \
    -e POSTGRES_PASSWORD=mysecretpassword \
    -d postgres