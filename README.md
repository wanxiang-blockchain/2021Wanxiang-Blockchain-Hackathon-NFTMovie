# 链影NFT代码

> contract

所用合约

> front-end

前端代码，基于vue和qtumjs

> movie-nft-storage-main

NFT后端存储代码

> movie-ntf-backen-main

NFT后端代码

后端主要存储一些缓存信息，包括电影的基本信息，海报，资源详情等等，这些 信息相对比区块链上的关键信息是次要的，可以恢复的。只要链上信息不被篡改，后 端存储的信息可以被快速恢复。后端采用基于 Springboot 的微服务架构，包括两个小型的服务，分别是为前端提供基础逻辑信息的 Service Server，以及储存非关键缓 存资源的 Storage Server。
