# zookeeper 应用场景
* 分布式锁
* 分布式注册中心
* 分布式配置中心
--------------------------
# zookeeper 安装
* 解压软件包
* 进入目录创建 data 和 log 文件夹
* 改名 zoo_sample.cfg 
* 编辑配置文件 设置数据和日志 文件目录
--------------------------
#zookeeper 相关特性
* 高效  ZooKeeper 将数据保存在内存中，这也就保证了 高吞吐量和低延迟
* 高性能 在读多与写的程序中，性能尤其高效，因为进行写，会同步到所有集群中，会有延时
* 可靠 集群中大部分服务可用 则zk就有效
* 顺序 从同一客户端发起的事务请求，最终将会严格地按照顺序被应用到 ZooKeeper 中去
* 简洁 api简单 通过少数api即可进行完整的操作zookeeper
--------------------------
#zookeeper 数据类型
* 临时节点
* 持久化节点
* 临时有序节点
* 持久有序节点
--------------------------
# zookeeper ACL 控制
