## Quick Start

由于frostmourne-monitor正常启动需要依赖xxl-job和frostmourne-spi的部署，还依赖短网址服务，邮箱服务等等；为了达到本地调试，快速启动的目的，
你可以设置frostmourne-monitor的如下两个配置：

```
xxl.job.mock=true
frostmourne.spi.mock=true
```

这两个配置的作用是，在这些依赖服务还没就绪的时候，启动这两个配置，会使用代码里的mock功能，替换真正的服务调用，达到隔离的目的，以此来简化启动。配置了
这两个参数后，你就可以先启动frostmourne-monitor， 再用VS Code打开frostmourne-vue

```bash
# install dependency
npm install

# 建议不要直接使用 cnpm 安装以来，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# develop
npm run dev
```

