#!/bin/bash

# 设置日期范围
start_date="2024-01-01"
end_date="2024-02-01"

start_date_sec=$(date -d "$start_date" +%s)
end_date_sec=$(date -d "$end_date" +%s)

branches=$(git branch -r)

for branch in $branches; do
    branch_date=$(git for-each-ref --format='%(committerdate:short)' refs/$branch)
    branch_date_sec=$(date -d "$branch_date" +%s)
    if (( branch_date_sec >= start_date_sec && branch_date_sec <= end_date_sec )); then
        echo "Deleting branch $branch"
        git push origin --delete $branch
    fi
done