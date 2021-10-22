# movie-nft-backen

>> movie-nft 项目服务端代码

## 配置文件
- 请尽量将配置写入 application.yaml 中方便区分环境和部署测试。
- 8080 端口暂时开放给 jenkins 使用，后端服务端口暂时为 8081 ，8082。

## 开发
- 开发时请新建 dev 分支,分支命名为 dev_{dev_name}。
- 请保持 main 分支 log 为直线，有利于清晰查看更改。使用rebase合并代码。
- test 分支为测试分支，方便部署到服务器端测试，可以随意。test 的端口配置为 8081，容器映射为 8082。

## 构建部署
支持 Jenkins 自动化一键部署     
>> TODO : 后期可能会增加 github webhook 监听main分支进行提交触发部署
- jenkins 登陆 http://82.157.177.72:8080/  账号: movie_ntf_backen 密码: Qtum2021#
- movie-ntf-backen 项目默认拉取 main 分支的代码，构建部署即可，默认端口8081，接口文档: http://82.157.177.72:8081/swagger-ui.html
- movie-ntf-backen-test 项目默认拉取test分支的代码，构建部署即可，默认端口8082 接口文档 http://82.157.177.72:8082/swagger-ui.html

****由于 Github 网不好会经常导致构建失败(凌晨网比较好🐶)****
