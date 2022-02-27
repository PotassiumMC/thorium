#!/bin/bash

README_PATH="${BASH_SOURCE%/*}/../README.md"
MOJIRA_ISSUE_REGEX="\\[MC-[0-9]*\\]"
MOJIRA_ENDPOINT="https://bugs.mojang.com/"
ISSUE_DELAY=0.25
declare -i unfixed=0
declare -i fixed=0
declare -i total=0

echo "Checking for fixed issues..."

for issue in $(cat $README_PATH | grep -o $MOJIRA_ISSUE_REGEX)
do
  issue_id=$(echo $issue | sed 's:^.\(.*\).$:\1:')
  mojira_response=$(curl -sX GET "${MOJIRA_ENDPOINT}rest/api/2/issue/${issue_id}?fields=status,fixVersions")
  issue_status=$(echo $mojira_response | jq -r '.fields.status.statusCategory.key')

  if [ $issue_status = "done" ]
  then
    resolved_in=$(echo $mojira_response | jq -r '.fields.fixVersions [0].name')
    echo "https://bugs.mojang.com/browse/${issue_id} has been resolved in ${resolved_in}!"
    fixed=$fixed+1
  else
    unfixed=$unfixed+1
  fi

  total=$total+1

  sleep $ISSUE_DELAY
done

echo "Done! Checked ${total} issues (${fixed} fixed, ${unfixed} unfixed)."
