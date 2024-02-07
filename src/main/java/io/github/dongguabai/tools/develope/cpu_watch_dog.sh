#!/bin/bash
if [[ $# -ne 1 ]]; then
  echo "Usage: $0 <j_pid>"
  exit 1
fi

j_pid=$1
threshold=90

while true; do
    cpu_info=$(ps -p "$j_pid" -o %cpu=)
    cpu_usage=$(sed 's/%//g' <<< "$cpu_info")
    current_time=$(date +'%Y-%m-%d %H:%M:%S')
    echo "[-----$current_time] Current CPU Usage: $cpu_usage%"
    if [[ $(echo "$cpu_usage > $threshold" | bc -q) -eq 1 ]]; then
      thread_id=$(top -b -n 1 -H -p $j_pid | grep -E "^\s*[0-9]+" | sort -k9 -r | head -n 1 | awk '{print $1}')
      echo "[-----$current_time] Detected! PID: $j_pid ; Thread ID: $thread_id"
      jstack_output=$(jstack -l $j_pid)
      jstack_filename="jstack_$(date +'%Y%m%d%H%M%S').txt"
      echo "$jstack_output" > "$jstack_filename"
      echo "[$current_time] jstack_filename: $jstack_filename"
    fi
  sleep 10
done

