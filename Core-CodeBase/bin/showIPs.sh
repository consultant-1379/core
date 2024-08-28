LOCALIPADDRESS=`hostname | nslookup | grep -i Address | grep -v '#' | awk -F: '{print $2}' | tr -d ' '`;
ifconfig -a | grep -i "inet " | awk '{print $2}' | awk -F: '{print $2}' | sort -ut. -k1,1 -k2,2n -k3,3n -k4,4n | grep -v "127.0.0.1" | grep -v "^$LOCALIPADDRESS$" > dat/ipListIPv4.txt
ifconfig -a | grep -i "inet6 " | awk '{print $3}' | sort -ut: | grep -v "::1/128"> dat/ipListIPv6.txt
