#!/bin/bash
java -Dfile.encoding=UTF-8 -classpath ./bin:./lib/jackson-annotations-2.2.3.jar:./lib/jackson-core-2.2.3.jar:./lib/jackson-databind-2.2.3.jar com.nextcoin.ticker.TickerMonitor &

