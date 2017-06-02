#!/bin/bash

ls teachers > Teachers.txt
echo "$(sed 's/.txt//' <<< "$(cat Teachers.txt)")" > Teachers.txt
echo "$(sed -e 's/\([^[:blank:]]\)\([[:upper:]]\)/\1 \2/g' -e 's/\([^[:blank:]]\)\([[:upper:]]\)/\1 \2/g' Teachers.txt)" > Teachers.txt
