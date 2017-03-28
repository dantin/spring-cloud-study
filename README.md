# spring-cloud-study

Spring cloud学习记录

### 机器及环境

| IP |
| --  |
| 172.16.15.142 |
| 172.16.15.143 |
| 172.16.15.144 |

编译

    mvn -DskipTests package
    ./gradlew build
    
端口打洞

    ssh -p2567 -L 28761:172.16.15.142:8761 cmcc-dev
    
Eureka服务

    java -jar microservice-discovery-eureka/target/microservice-discovery-eureka-1.0-SNAPSHOT.jar

Provider服务

    java -jar microservice-provider-user/target/microservice-provider-user-1.0-SNAPSHOT.jar
    java -jar -Dserver.port=8001 microservice-provider-user/target/microservice-provider-user-1.0-SNAPSHOT.jar
    
### Consul集群搭建

node0(10.12.21.142)

    ./consul agent -data-dir /tmp/node0 -node=node0 -bind=10.12.21.142 -datacenter=dc1 -ui -client=10.12.21.142 -server -bootstrap-expect 1
    ./consul members -rpc-addr=10.12.21.142:8400

node1(10.12.21.145)

    ./consul agent -data-dir /tmp/node1 -node=node1 -bind=10.12.21.145 -datacenter=dc1 -ui
    ./consul join 10.12.21.142
    ./consul members -rpc-addr=10.12.21.142:8400
    
node2(10.12.18.26)本机

    ./consul agent -data-dir /tmp/node2 -node=node2 -bind=10.12.18.26 -datacenter=dc1 -ui -client=10.12.18.26
    ./consul join -rpc-addr=10.12.18.26:8400 10.12.21.142
    ./consul members -rpc-addr=10.12.21.142:8400
