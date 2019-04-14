#!/bin/bash

if [ $# -eq 0 ]
then
  flag="--fen"
else
  flag=$*
fi

lein run $flag
