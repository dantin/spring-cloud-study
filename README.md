# spring-cloud-study

Spring cloud学习记录

### 服务模块

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

#### 机器及环境

| 主机名 | IP            | 作用          |  允许远程访问 |
| ----- | ------------- | ------------- | ----------- |
| node0 | 172.16.15.142 | consul server | 是          |
| node1 | 172.16.15.143 | consul client | 否          |
| node2 | 172.16.15.144 | consul client | 是          |

#### 各节点启停命令

node0(172.16.15.142)

    /opt/consul/consul agent -data-dir /tmp/node0 -node=node0 -bind=172.16.15.142 -datacenter=dc1 -ui -client=172.16.15.142 -server -bootstrap-expect 1
    /opt/consul/consul members -rpc-addr=172.16.15.142:8400

node1(172.16.15.143)

    /opt/consul/consul agent -data-dir /tmp/node1 -node=node1 -bind=172.16.15.143 -datacenter=dc1 -ui
    /opt/consul/consul join 172.16.15.142
    /opt/consul/consul members -rpc-addr=172.16.15.142:8400
    
node2(172.16.15.144)本机

    /opt/consul/consul agent -data-dir /tmp/node2 -node=node2 -bind=172.16.15.144 -datacenter=dc1 -ui -client=172.16.15.144
    /opt/consul/consul join -rpc-addr=172.16.15.144:8400 172.16.15.142
    /opt/consul/consul members -rpc-addr=172.16.15.142:8400
