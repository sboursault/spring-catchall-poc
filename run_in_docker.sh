#!/usr/bin/env bash

# building the jar could be in staged docker,
# but it would not reuse the local gradle repository.

# stop bash on error
set -e

USAGE="$(basename "$0") [-h] [-m <message>] [SERVICE...] -- program to launch the asylum environment.
where:
    SERVICE... Are the docker services to restart.
    -h           Shows this help."

while getopts ":hm:" opt; do
  case $opt in
    h)
      echo "$USAGE"
      exit 0
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
  esac
done

shift $((OPTIND-1)) # shift all processed options away from parameters (keep only operands)
SERVICES=$*

if [[ " $@ " =~ "-api" ]] || [[ " $@ " =~ "front-office" ]] || [ $# = 0 ]; then
  echo ""
  echo "===[ building app ]==="
  echo ""
  (set -x; ./gradlew clean build) # use a subshell and print command (https://stackoverflow.com/a/31392037)
fi

if [ $# = 0 ]; then
  echo ""
  echo "===[ up dockers ]==="
  echo ""
  (set -x; docker-compose down && docker-compose up -d --build)
else
  echo ""
  echo "===[ restart dockers ]==="
  echo ""
  (set -x; docker-compose restart $SERVICES)
fi

