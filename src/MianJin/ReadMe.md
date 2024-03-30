# <span id="java">Java</span>
## Java Base
### 内存分区

### 垃圾收集器

### java内存模型

### java 进程间的通讯方式
使用ProcessBuilder启动子进程.
使用Socket进行网络通信。
使用Java RMI（远程方法调用）。
使用JMS（Java Message Service）。
使用Files或MappedByteBuffer进行文件映射。
使用System.in, System.out, System.err进行父子进程间通信。
使用中间件，消息队列，数据库等。


## <span id="juc">JUC</span>
### Pool 技术
池话设计应该不是一个新名词。我们常见的如java线程池、jdbc连接池、redis连接池等就是这类设计的代表实现。这种设计会初始预设资源，解决的问题就是抵消
每次获取资源的消耗，如创建线程的开销，获取远程连接的开销等。就好比你去食堂打饭，打饭的大妈会先把饭盛好几份放那里，你来了就直接拿着饭盒加菜即可，
不用再临时又盛饭又打菜，效率就高了。除了初始化资源，池化设计还包括如下这些特征：池子的初始值、池子的活跃值、池子的最大值等，这些特征可以直接映射到java线
程池和数据库连接池的成员属性中。

#### [java 线程池](#java)
Java线程池是多线程编程中一项重要的工具，它能够有效地管理和调度线程，提高程序的并发性能。线程池的扩容机制是线程池的关键特性之一，
它允许根据工作负载的变化动态地增加或减少线程数量。

##### 线程池主要参数
线程池的主要参数：  
corePoolSize（核心线程数）：线程池中一直存活的线程数量，即使它们处于空闲状态也不会被销毁。  
maximumPoolSize（最大线程数）：线程池中允许的最大线程数量，包括空闲状态的线程和正在执行任务的线程。  
workQueue（工作队列）：存放等待执行的任务的队列。当任务提交到线程池时，会先放入工作队列。  
keepAliveTime（线程空闲时间）：空闲线程的最大存活时间，超过这个时间空闲线程将被销毁，仅当线程数量超过核心线程数时生效。  
RejectedExecutionHandler（任务拒绝处理器）：当任务无法被执行时的处理策略。

##### [线程池拒绝策略][拒绝策略]
拒绝策略都是主线程去执行的。  
内置的四种拒绝策略：  
1)[CallerRunPolicy（调用者运行策略)](#reject1)  
2)[AbortPolicy（中止策略)](#reject2)  
3)[DiscardPolicy（丢弃策略）](#reject3)  
4)[DiscardOldestPolicy（弃老策略）](#reject4)

**拒绝策略顶层接口**
```java
public interface RejectedExecutionHandler {
    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
```
1）<span id="reject1">CallerRunPolicy（调用者运行策略)</span>
功能：当触发拒绝策略时，只要线程池没有关闭，就由提交任务的当前线程处理。  

使用场景：一般在不允许失败的、对性能要求不高、并发量较小的场景下使用，因为线程池一般情况下不会关闭，也就是提交的任务一定会被运行，但是由于是调用者
线程自己执行的，在线程池满载的情况下多次提交任务时，就会阻塞后续任务执行，性能和效率自然就慢了。
```java

public static class CallerRunsPolicy implements RejectedExecutionHandler {
    public CallerRunsPolicy() { }
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (!e.isShutdown()) {
            r.run();
        }
    }
}
```

2）<span id="reject2"> AbortPolicy（中止策略）</span>
功能：当触发拒绝策略时，直接抛出拒绝执行的异常，中止策略的意思也就是打断当前执行流程  
使用场景：这个就没有特殊的场景了，但是一点要正确处理抛出的异常。ThreadPoolExecutor中默认的策略就是AbortPolicy，
ExecutorService接口的系列ThreadPoolExecutor因为都没有显示的设置拒绝策略，所以默认的都是这个。但是请注意，ExecutorService中的线程池实例
队列都是无界的，也就是说把内存撑爆了都不会触发拒绝策略。当自己自定义线程池实例时，使用这个策略一定要处理好触发策略时抛的异常，因为他会打断当前的执行流程。
```java
public static class AbortPolicy implements RejectedExecutionHandler {
        public AbortPolicy() { }
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                                                 " rejected from " +
                                                 e.toString());
        }
    }
```

3)<span id="reject3"> DiscardPolicy（丢弃策略）</span>  
功能：直接静悄悄的丢弃这个任务，不触发任何动作
使用场景：如果你提交的任务无关紧要，你就可以使用它 。因为它就是个空实现，会悄无声息的吞噬你的的任务。所以这个策略基本上不用了
```java
public static class DiscardPolicy implements RejectedExecutionHandler {
        public DiscardPolicy() { }
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        }
    }
```

3)<span id="reject4"> DiscardOldestPolicy（弃老策略）</span>  
功能：如果线程池未关闭，就弹出队列头部的元素，然后尝试执行
使用场景：这个策略还是会丢弃任务，丢弃时也是毫无声息，但是特点是丢弃的是老的未执行的任务，而且是待执行优先级较高的任务。基于这个特性，我能想到的场
景就是，发布消息，和修改消息，当消息发布出去后，还未执行，此时更新的消息又来了，这个时候未执行的消息的版本比现在提交的消息版本要低就可以被丢弃了。
因为队列中还有可能存在消息版本更低的消息会排队执行，所以在真正处理消息的时候一定要做好消息的版本比较.

```java
public static class DiscardOldestPolicy implements RejectedExecutionHandler {
    public DiscardOldestPolicy() { }
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (!e.isShutdown()) {
            e.getQueue().poll();
            e.execute(r);
        }
    }
}
```

#### <a href="#label">ForkJoinPool</a>
ForkJoinPool 是 JDK7 引入的，由 Doug Lea 编写的高性能线程池。核心思想是将大的任务拆分成多个小任务（即fork），
然后在将多个小任务处理汇总到一个结果上（即join），非常像MapReduce处理原理。同时，它提供基本的线程池功能，
支持设置最大并发线程数，支持任务排队，支持线程池停止，支持线程池使用情况监控，也是AbstractExecutorService的子类，
主要引入了“工作窃取”机制，在多CPU计算机上处理性能更佳。其广泛用在java8的stream中。

ForkJoinPool 并不是为了替代 ThreadPoolExecutor 而出现的，而是作为一种它的补充。在处理 CPU 密集型任务的时候，
它的性能比 ThreadPoolExecutor 更好，而如果你是 I/O 密集型任务的时候，除非配置 ManagedBlocker 一起使用，否则不建议使用它。


分治法的基本思想是一个规模为N的问题分解为K个规模较小的子问题，这些子问题的相互独立且与原问题的性质相同，求出子问题的解之后，将这些解合并，
就可以得到原有问题的解

![ForkJoin任务模型图.png](ForkJoin任务模型图.png)

##### 工作窃取
工作窃取是指当某个线程的任务队列中没有可执行任务的时候，从其他线程的任务队列中窃取任务来执行，以充分利用工作线程的计算能力，
减少线程由于获取不到任务而造成的空闲浪费。

### 虚拟线程
![VirtualThreadPrincipalDiagram.png](VirtualThreadPrincipalDiagram.png)
**什么是 Java 虚拟线程？**
Java 虚拟线程是指一种在 Java 虚拟机（JVM）中实现的轻量级线程，也被称为用户线程。  
和传统操作系统线程相比，Java 虚拟线程的创建和销毁速度更快，开销更小，可以大量创建，更适合轻量级任务场景。

虚拟线程在 Java 中的实现方式是通过协作式线程调度（Cooperative Thread Scheduling）来完成的。  
这种调度方式是将线程的执行权从（运行中的）线程主动交给另一个处于等待状态的线程，从而达到多个线程并发执行的目的。

Java 虚拟线程是由 Java 虚拟机自己管理的，而不是操作系统。  
JVM 每隔一段时间就会主动触发一次线程调度，将执行权交给其他线程，以便让等待执行的线程得到运行机会。

**应用场景**  
Java 虚拟线程广泛应用于轻量级任务场景，例如协议处理、IO 处理、计算密集型任务、GUI 事件监听等。其优势是可以针对大量的小型任务，
采用小的线程池或线程缓存来提高程序的效率和性能。

1.短时间的任务
Java 虚拟线程适用于短时间的任务，如网络通信、IO 操作等，这些任务通常很快就能够完成，不占用太多计算资源，但需要频繁切换线程。
在这种情况下，虚拟线程的轻量级特性可以有效地减少上下文切换的开销，提升程序的性能。

2.并行计算
Java 虚拟线程也可以用于并行计算，例如多线程计算中的 MapReduce 并行计算框架。在这种应用场景下，虚拟线程可以协同工作，对数据进行分布式处理，从而提高性能和效率。

3.GUI 事件监听
在 GUI 应用程序中，虚拟线程可以用于监听用户事件，如鼠标点击、键盘输入等。在这种应用场景下，虚拟线程通常不需要很高的计算性能，但需要能够及时响应用户的操作。虚拟线程因为轻量级，因此能够快速地相应用户的操作，并将处理结果返回给 GUI 应用程序。

**虚拟线程的优化**  
在虚拟线程应用的过程中，还需要关注线程的安全和优化问题，常见优化方式有以下几种：
1 线程池
线程池可以用来缓存线程资源，避免了线程的频繁创建和销毁，以及系统资源的浪费。在使用线程池时，需要保证线程的数量不会超出资源的限制，
否则会导致程序崩溃或者运行缓慢。

2 同步机制
多线程之间的访问和操作需要进行同步处理。在 Java 中，可以使用 synchronized 关键字来实现同步操作，避免出现线程竞争的问题。

3 线程间通信
线程间协同工作需要进行线程间通信，以便将工作结果返回给其他线程。Java 中提供了 wait/notify/notifyAll 等机制来进行线程间通信，
以便协同工作的线程能够及时响应和处理。





## Java面经
**1.《Java核心技术 卷I》静态内部类这一小节提到，“与常规内部类不同，静态内部类可以有静态字段和方法”。也就是常规内部类是不能有静态字段和方法的，为什么？**  
静态字段和方法在类加载的时候就会存在于内存中，但是外部类加载的时候并不会加载常规内部类。常规内部类只有在外部类实例化之后才加载，
而静态字段或方法是在类加载之后才存在的。如果在外部类还没有实例化的时候调用非静态内部类的静态字段或方法，内部类还没加载是不能创建静态字段或方法的。

因此，如果想在内部类中定义静态字段和方法，内部类本身也必须被声明为是静态的。
另外书中还注释了一句话：“在接口中声明的内部类自动是static和public。”。因为接口不能被实例化，可以将接口看做是一个抽象类，其中的方法没有具体实现
，因此不能通过实例化一个接口来加载内部类、创建内部类的对象。

**2.synchronized和volatile的区别**  
olatile和synchronized都是用于多线程编程的关键字，但它们在实现线程同步和保证数据可见性方面有所不同。以下是详细介绍：  

volatile关键字主要用于确保变量在多个线程之间可见，它通过内存屏障和禁止处理器重排序来保证这一点，volatile变量每次被线程读取时，
都会从主内存中重新加载最新值，而不会使用寄存器或工作内存中的缓存。volatile不保证原子性，
即它不能防止多个线程同时修改一个volatile变量的不同部分。  

synchronized关键字则用于更广泛的同步，它可以修饰方法、代码块或整个类。当一个线程执行synchronized代码时，它会获取一个锁，
这会阻止其他线程访问相同的代码块，直到锁被释放。synchronized不仅保证可见性，还保证原子性，即它确保代码块中的所有操作都是原子的，
不会被线程调度器重新排序。

**3、线程的生命周期**  
![ThreadLifeCycle.png](ThreadLifeCycle.png)



**4、threadLocal可能造成的问题以及解决方案**


# Kubernate

# Nginx

# lightSpeed
jenkins docker udeploy
teckton harness


# Redis

# linux

# Spark

# Hbase

# Zookeeper

# kafka
## kafka面经
**1.Kafka高效读盘写盘**
Kafka 的消息是保存或缓存在磁盘上的，一般认为在磁盘上读写数据是会降低性能的，因为寻址会比较消耗时间，但是实际上，Kafka 的特性之一就是高吞吐率。Kafka 之所以能这么快，无非是：顺序写磁盘、大量使用内存页 、零拷贝技术的使用、批量发送、数据压缩。  

为了高效的写盘，Kafka使用了两种策略。  
(1) 追加写提高效率：Kafka是将partition的数据写在磁盘的(消息日志)，不过Kafka只允许追加写入(底层磁盘原理：顺序访问而不是随机访问，没有寻址时间，加快写入速度)，避免缓慢的随机 I/O 操作。  
(2) 攒一波一次写/批量写：Kafka也不是partition一有数据就立马将数据写到磁盘上，它会先缓存一部分，等到足够多数据量或等待一定的时间再批量写入(flush)。  

前面讲解到了生产者往topic里丢数据是存在partition上的，而partition持久化到磁盘是IO顺序访问的，并且是先写缓存，隔一段时间或者数据量足够大的时候才
批量写入磁盘的，即写盘的时候通过“顺序IO+批量写”达到最快写盘。  

在读的时候也很有讲究，通过零拷贝技术加快读盘速度，每个消费者通过offset记录自己的当前读取位置。  

零拷贝：正常的读磁盘数据是需要将内核态数据拷贝到用户态的，而Kafka 通过调用sendfile()直接从内核空间（DMA Direct Memery Access直接内存通过）到内核空间（Socket的），少做了一步拷贝的操作。  
![kafaZeroCopy](kafaZeroCopy.png)

**2.如何有序的消费不同分区的消息**
1).kafka topic只设置一个partition分区。
2).producer将消息发送到指定的分区。
3).局部消费顺序：多个partition局部有序
生产时：producer在把消息发送到partition的时候，当key非空时，用key的hash值对partition个数取模，决定要把消息发送到哪个partition上，
可以指定一样的key，这样这个key的消息在单个partition内是有序的。  
注：当key为空时，消息随机发送到各个分区。规则再各个版本会有不同，有的是采用轮询方式，有的随机，有的是一定时间内发送给固定的partition，隔一段时间后随机换一个。  
消费时：在一个消费者组中的多个消费者消费一个topic时，一个消费这组中只能有一个消费者消费该消息，topic下的每个分区只属于组中的一个消费者，因此是有序的。 

**2.如何有序的消费不同分区的消息**


# Mysql
## 架构原理图
![img.png](Mysql架构原理.png)

# Spring
### [1. Bean的循环依赖问题][循环依赖]
当两个或更多个Bean之间相互依赖时，就会出现Spring循环依赖的问题。这意味着，每个Bean都需要其他Bean才能被创建，而其他Bean又需要该Bean才能被创建。
这种情况下，Spring IoC容器会抛出一个异常，告诉你存在循环依赖。
![循环依赖示意图.png](循环依赖示意图.png)
```java
@Component
public class C {
   @Autowired
   private D d;
}
@Component
public class D {
   @Autowired
   private C c;
}
```

解决循环依赖的方案：
Spring 中利用三级缓存解决循环依赖的根本就是为了避免引用对象的循环创建:

>singletonObjects：用于存放单例bean实例的缓存，即作用域为singleton的bean。当创建完一个bean后，Spring会将其放入singletonObjects缓存中。
>在后续获取该bean时，Spring会直接从该缓存中获取，不再需要重新创建。  

>earlySingletonObjects：用于存放创建中的bean实例的缓存。在创建一个bean的过程中，如果需要引用到该bean，则会先从earlySingletonObjects缓存
> 中获取。如果该缓存中不存在该bean的实例，则会通过实例化工厂创建一个新的bean实例。  

>singletonFactories：用于存放创建中的bean实例的工厂缓存。在创建一个bean的过程中，如果需要引用到该bean，则会先从singletonFactories缓存中获
> 取。如果该缓存中不存在该bean的工厂实例，则会通过实例化工厂创建一个新的bean工厂实例。在后续获取该bean时，Spring会从该缓存中获取bean工厂实例，
> 并通过该工厂创建新的bean实例。

在以上三个缓存中，earlySingletonObjects缓存和singletonFactories缓存的作用是相同的，即存放创建中的bean实例。
它们的区别在于earlySingletonObjects缓存存放的是已经创建但还没有完全初始化的bean实例，而singletonFactories缓存存放的是bean实例工厂。

```java
public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry {
    // 一级缓存，保存 beanName 和 实例化、初始化好的完整 bean 对象
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    // 二级缓存，保存 beanName 和 未初始化的 bean 对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);  
    // 三级缓存，保存 beanName 和 lambda 表达式 () -> getEarlyBeanReference(beanName, mbd, bean), 保存bean的创建工厂
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
}
```
当我们获取一个bean时，Spring首先会从singletonObjects缓存中查找是否存在该bean的实例。如果存在，则直接返回该实例；否则，Spring会检查该bean
是否在earlySingletonObjects缓存中。如果在，Spring会等待该bean完全初始化后再返回该bean实例；否则，Spring会检查singletonFactories缓存中
是否存在该bean实例的工厂。如果存在，Spring会通过该工厂创建一个新的bean实例，并将其放入earlySingletonObjects缓存中，等待其完全初始化后再放
入singletonObjects缓存中。


## SpringMVC


## Spring Boot



## Spring Cloud

## Spring面经
**1.@Autowired和@Resource的区别**  
@Autowired 和 @Resource 都可以用来装配 bean，但它们在默认行为、使用方式、支持的参数以及编译器提示方面存在差异。12345
@Autowired 是 Spring 框架提供的注解，默认按类型装配，如果需要按名称装配，可以结合 @Qualifier 注解使用。  
它还可以设置 `required` 属性为 `false` 以允许 null 值。
@Resource 是 J2EE 平台提供的注解，默认按名称装配，如果没有指定 name 属性，它会根据字段名或属性名进行装配。如果两者都找不到，会按类型装配，但这种情况下会报错。



# 算法

# 系统设计

## 系统设计面经
**1.高并发、高性能设计方案**





[拒绝策略]:https://developer.aliyun.com/article/1382341?spm=a2c6h.14164896.0.0.3e4947c5jIzSlb&scm=20140722.S_community@@%E6%96%87%E7%AB%A0@@1382341._.ID_1382341-RL_%E7%BA%BF%E7%A8%8B%E6%B1%A0%E6%8B%92%E7%BB%9D%E7%AD%96%E7%95%A5-LOC_search~UND~community~UND~item-OR_ser-V_3-P0_2
[循环依赖]:https://developer.aliyun.com/article/1436029?spm=a2c6h.14164896.0.0.5f8447c5ADJHwB&scm=20140722.S_community@@%E6%96%87%E7%AB%A0@@1436029._.ID_1436029-RL_Spring%20%E4%B8%89%E7%BA%A7%E7%BC%93%E5%AD%98%E5%8E%9F%E7%90%86-LOC_search~UND~community~UND~item-OR_ser-V_3-P0_4
[kafa高效读写]:https://blog.csdn.net/qq_36963950/article/details/120817232  