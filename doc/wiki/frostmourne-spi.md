## 为什么设计frostmourne-spi

把开源的项目落地到本地，免不了要做一些适配定制工作，最典型的就是组织结构信息的适配。对于frostmourne监控来说还有各种消息发送方式
的实现适配，短链接服务替换。在做了适配定制之后，本地部署想要跟着主版本升级就会成为一个难题。  

我将需要定制的功能都通过REST接口的方式归到frostmourne-spi中，frostmourne-spi的设计就是为了简化这个问题。如何简化的呢？  

* 相对主体项目frostmourne-monitor, frostmourne-spi代码量很少

相比将定制实现的代码放在主体项目中，放在代码量很少的frostmourne-spi中，代码管理会简单很多。

* 相对主体项目frostmourne-monitor, frostmourne-spi维护量很小，基本协议不会变

frostmourne-monitor作为主体功能项目，代码变更肯定是非常频繁的，但是frostmourne-spi接口协议基本不变，代码也基本很少变更。
这样你就可以实现frostmourne-monitor跟着主版本升级，而frostmourne-spi自己维护一个，不用跟着主版本升级，会java的使用者自己
照着spi项目自己实现一套也是非常简单的事情。  

基于以上两点原因设计的frostmourne-spi模块，应该可以大大减少项目跟着主版本维护升级这方面的困难。