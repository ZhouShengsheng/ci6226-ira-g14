#!/bin/sh
# Desc: Search engine deployment script.
# Author: Zhou Shengsheng

# configurations
SSH_SERVER=shengsheng@155.69.149.197
APP_NAME=search-engine
JAR_FILE=target/g14-${APP_NAME}.jar
REMOTE_DIR=/home/shengsheng/Workspaces/IR/projects/${APP_NAME}
SERVER_SH=server.sh

# build
mvn clean package -DskipTests

# create remote directory
ssh ${SSH_SERVER} "mkdir -p ${REMOTE_DIR}"

# copy server.sh to remote server
scp ${SERVER_SH} ${SSH_SERVER}:${REMOTE_DIR}

# copy jar file to remote server
scp ${JAR_FILE} ${SSH_SERVER}:${REMOTE_DIR}

# restart search engine
ssh ${SSH_SERVER} "${REMOTE_DIR}/${SERVER_SH} 2>/dev/null"
