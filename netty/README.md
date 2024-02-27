# 什么是Netty
## 前置要求
掌握了java OOP、java多线程编程、 java io编程、java网络编程， 常用的设计模式（比如观察着模式，命令模式，职责链模式）、
常用的数据结构（比如， 链表）

## netty是什么
1）JBOSS提供的一个java开源框架  
2）是一个异步的、基于事件驱动的网络应用框架，用于快速开发高性能、高可靠性的网络io程序   
3）主要针对在tcp协议下，面向clients端的高并发应用，或者p2p场景下的大量数据持续传输的应用  
4）Netty本质是一个NIO框架，适用于服务器通讯相关的多种应用场景

## netty 应用场景
1)互联网行业：分布式系统中，各个节点之间需要远程服务调用，高性能的rpc框架必不可少，netty作为异步高性能的通信框架，往往作为基础通信组件被这些rpc框架调用  
2）典型应用：阿里分布式服务框架Dubbo的rpc框架使用Dubbo协议进行节点间的通信，Dubbo协议默认使用netty作为基础通信组件，用于实现各个进程节点间的通信  
3）Netty作为高性能的基础通信组件，提供了tcp/udp和http协议栈，方便定制和开发私有协议栈，账号登录服务器  
4）游戏服务器和地图服务器之间也会有所应用  
5）经典的hadoop的高性能通信和序列化组件（avro实现数据文件共享）的rpc框架，默认采用netty作为跨界点通信  

## 什么是IO模型
### java io模型
1）I/O模型简单理解：就是用什么样的通道进行数据的发送和接收，很大程度上决定了程序通信的性能
2）java总共支持三种网络编程I/O模式： BIO、NIO、AIO  
3）BIO： 同步并阻塞（传统阻塞性），服务器实现模式为一个连接一个线程，即客户端有链接请求时服务器端就需要启动一个线程进行处理，
如果链接不做任何事情会造成不必要的线程开销。  
4）NIO：同步非阻塞，服务器实现模式为一个线程处理多个请求（连接），即客户端发送的连接请求都会注册到多路复用器（selector）上，多路复用器轮询到连接有
I/O请求就进行处理。  
5）AIO：异步非阻塞，AIO引入异步通道的概念，采用reactor模式，简化了程序编写，有效的请求才启动线程，他的特点是先由操作系统完成后才通知
服务器端程序启动线程去处理，一般适用于连接数目较多且连接时间长的应用。

### 各个io模型适用的场景分析
1）BIO方式适用于连接数目小且固定的架构，种种方式对服务器资源要求比较高，并发局限于应用中，jdk1.4以前的唯一选择，但程序简单易理解。
2）NIO方式适用于连接数目多且连接比较短的架构，比如聊天服务器，弹幕系统，服务器间通讯等，编程比较复杂，jdk1.4开始支持。
3）AIO方式适用于连接数目较多且连接时间长的架构，比如相册分类器，充分调用os参与并发操作，编程比较复杂，jdk7开始支持。

### Java Bio基本介绍
传统的Java io编程，相关的类和接口在java.io中
#### bio 问题分析
1）每个请求都需要创建独立的线程，与对应的客户端进行数据read，业务处理，数据write  
2）当并发数较大是，需要创建大量的线程来处理链接，系统资源占用较大  
3）连接建立后，如果当前线程暂时没有数据可读，则线程就阻塞在read操作上，造成线程资源浪费 

### Java Nio基本介绍
1）java nio全称java non-blocking io，从jdk1.4开始，java提供了一系列改进的输入/输出的新特性
，被统称为NIO，是同步非阻塞  
2）NIO的类被放在java.nio包以及其子包下，并对java.io包下的很多类进行了改写。  
3）NIO有三大核心部分： Channel， Buffer， Selector  
4）NIO是面向缓冲区， 或者面向块编程的。数据读取到一个它稍后会处理的缓冲区，需要时可在缓冲区中前后移动，
这就增加了处理过程的灵活性，使用它可以提供非阻塞式的高伸缩性网络。  
5）java nio的非阻塞模式，使得一个线程从某通道发送请求或者读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取
，而不是保持线程阻塞，所以直至数据变得可以读取之前，该线程可以做其他事情，非阻塞写也是如此，一个线程请求写入数据到某个通道，但不需要等待他 
   完全写入，这个线程同时可以去做别的事情  
6）通俗理解：NIO可以做到用一个线程来处理多个操作的。假设10000个请求过来，根据实际情况，可以分配50或者100个线程来处理。不像之前的阻塞io
那样，非得分配10000个  
7）HTTP2.0 使用多路复用技术，做到同一个链接并发处理多个请求，而且并发请求的数量比HTTP1.1大了好几个数量级  

### 和BIO比较
1）bio是以流的方式处理数据，而nio以块的方式处理数据，块io比流io效率高很多  
2）bio是阻塞的，nio是非阻塞的  
3）bio基于字节流和字符流进行操作，而nio基于channel和buffer进行操作，数据总是从通道读取到缓冲区中，或者
从缓冲区写入通道。selector用于监听多个通道的事件（比如：连接请求、数据到达等），因此使用单个线程就可以监听多个客户端
通道

### NIO的三大核心组件原理示意图
![NIO三大组件原理图](NIO三大组件原理图.png)  
* 每个channel都会对应一个buffer
* selector对应一个线程，一个线程对应多个channel的连接
* 该图反映了三个channel注册到该selector程序
* 程序切换到那个channel是由事件决定的， event是一个很重要的概念
* selector会根据不同的事件，在各个通道上切换
* buffer就是一个内存块，底层是有一个数组
* 数据的读取写入是通过buffer，这个和bio有不同， bio中要么是输入流，或者是输出流。但是nio的buffer可以读也可以写，需要flip方法进行切换。
* channel是双向的，可以返回底层操作系统的情况，比如linux，底层的操作系统通道就是双向的  


```mermaid
flowchart TB
S(ServerSocketChannel 类型\n ServerSocketChannelImpl) --- T([Thread])
SL(Selector 选择器)
RW[Read/Write]
T---SL
SL---CH[通道channel SocketChannel <SocketChannelImpl>]---RW[Read/Write]---B[Buffer]---C[Client]
SL---CH1[通道channel SocketChannel <SocketChannelImpl>]---RW1[Read/Write]---B1[Buffer]---C1[Client]
SL---CH2[通道channel SocketChannel <SocketChannelImpl>]---RW2[Read/Write]---B2[Buffer]---C2[Client]
S---ETC[...]
%%style S fill:#F0F0F0,stroke:#333,stroke-width:2px;
```

#### NIO的Buffer
缓冲区（buffer）: 缓冲区本质上是一个可以读写数据的内存块，可以理解成一个容器对象（含数组），该对象提供了一组方法，可以更轻松的
使用内存块，缓冲区内置了一些机制，能够跟踪和记录缓冲区的状态变化情况。Channel提供从文件、网络读取数据的渠道，但是读取或写入数据都必须
经过Buffer：  
NIO程序 <--data--> 缓冲区 <--channel--> 文件

##### Buffer类及其子类
1）在NIO中，Buffer是一个顶层父类，它是一个抽象类。常用子类（ByteBuffer，ShortBuffr， CharBuffer，IntBuffer等）  
2）Buffer类定义了所有的缓冲区都具有四个属性来提供关于其所包含的数据元素的信息：capacity，limit，position， mark  
3）ByteBuffer：最常用的子类。

#### NIO的Channel
1）BIO中的stream是单向的，例如FileInputStream对象只能进行读取数据的操作，而NIO中的通道（Channel）是双向的，可以读操作，也可以写操作。  
2）Channel在NIO中是一个接口。   
3）常用的Channel类有：FileChannel、DatagramChannel、ServerSocketChannel和SocketChannel，  
4）FileChannel用于文件数据读写，DatagramChannel用于UDP的数据读写，ServerSocketChannel和SocketChannel用于TCP数据读写。  

#### 关于Buffer和Channel的注意事项和细节
1）ByteBuffer支持类型化的put和get， put放入什么数据类型，get就应该使用相应的数据类型取出，否则可能有BufferUnderflowException异常。
2）可以将一个普通buffer转成只读buffer
3）NIO还提供了MappedByteBuffer，可以让文件直接在内存（堆外的内存）中进行修改，而如何同步到文件由NIO来完成
4）NIO还支持通过多个Buffer（即Buffer数组）完成读写操，即Scattering和Gatering


#### Selector介绍和原理
1）Java的NIO，用非阻塞的IO方式。可以用一个线程处理多个的客户端连接，就会使用到Selector（选择器）  
2）Selector能够检测多个注册的通道上是否有事件发生（注意：多个channel以事件的方式可以注册到同一个Selector），
如果有事件发生，便获取事件然后针对每个时间进行相应的处理，这样就可以只用一个单线程去管理多个通道，也就是管理多个连接和请求。  
3）只有在连接真正有读写时间发生时，才会进行读写，就大大的减少了系统开销，并且不必为每个连接都创建一个线程，不用去维护多个线程。  
4）避免多线程之间上下文切换导致的开销。  

##### 特点
1) Netty的IO线程NioEventLoop聚合了Selector（选择器，也叫多路复用器），可以同时并发处理成百上千个客户端连接。    
2）当线程从某客户端Socket通道进行读写数据时，若没有数据可用时，该线程可以执行其他的任务。  
3）线程通常将非阻塞IO的空闲时间用于在其他通道上执行IO操作，所以单独的线程可以管理多个输入和输出通道。  
4）由于读写操作都是非阻塞的，这就可以充分提升IO线程的运行效率，避免由于频繁I/O阻塞导致的现场挂起。  
5）一个I/O线程可以并发处理N个客户端连接和读写操作，这从根本上解决了传统同步阻塞I/O一连接一线程模型，架构的性能、弹性伸缩能力
   和可靠性都得到了提升。

```mermaid
flowchart TB
%% Nonblocking I/O
S(Socket)---RW(Read/Write)---SL(Selector 选择器)---T(Thread)
S1(Socket)---RW1(Read/Write)---SL
S2(Socket)---RW2(Read/Write)---SL
%%style S fill:#F0F0F0,stroke:#333,stroke-width:2px;
```

##### Selector 类相关方法
Selector类的相关方法：
Selector是一个抽象类，常用方法和说明如下：  
public abstract class Selector implements Closeable{  
&nbsp;&nbsp;&nbsp;&nbsp;public static Selector open();//得到一个选择器对象  
&nbsp;&nbsp;&nbsp;&nbsp;public int select(long timeout);//监控所有注册通道，当其中有i/o操作可以进行时，将对应的SelectionKey加入到
内部集合中并返回，参数用来设置超时时间。  
&nbsp;&nbsp;&nbsp;&nbsp;public Set<SelectionKey> selectedKeys() //从内部集合中得到所有的SelectionKey   
}

##### 注意事项
1）NIO中的ServerSocketChannel 功能类似SeverSocket， SocketChannel功能类似Socket。  
2）selector相关方法说明：  
selector.select()//阻塞  
selector.select(1000)//阻塞1000毫秒，在1000毫秒后返回  
selector.wakeup()// 唤醒selector
selector.selectNow()// 不阻塞，立马返回

##### NIO非阻塞网络编程原理分析图

```mermaid
flowchart TB
%% Nonblocking I/O 原理图

S(Selector 实例)
SK(SelectionKey)---|注册|R(通信管道 )---C(Client)
SK---|注册|R1(通信管道 )---C1(Client)
SK --> S
T(Thread)---S
Server(服务器\n ServerSocketChannel\n 1.监听端口\n  2.获得和客户端连接的通道SocketChannel\n   3.每个客户端都会生成对应的通道SocketChannel)
Server --->|注册|S  
%%style S fill:#F0F0F0,stroke:#333,stroke-width:2px;
```

1）当客户端连接时，会通过ServerSocketChannel得到SocketChannel  
2）将SocketChannel注册到selector上，register(Selector sel, int ops), 一个selector上可以注册多个
SocketChannel  
3）注册后返回一个SelectionKey和该Selector关联（集合）  
4）Selector进行监听， select方法，返回有事件发生的通道个数。  
5）进一步得到各个SelectionKey（有事件发生）  
6）通过SelectionKey反向获取SocketChannel，方法channel()  
7）可以通过得到的Channel， 完成业务处理  



#### NIO 群聊系统实现
实例要求：
1）编写一个NIO群聊系统，实现服务器端和客户端之间的数据简单通讯（非阻塞）
2）实现多人群聊
3）服务器端可以检测用户上线，离线，并实现消息转发功能
4）客户端：通过channel可以无阻塞发送消息给其他所有用户，同时可以接受其他用户发送的消息（由服务器转发得到）
5）目的：进一步理解NIO非阻塞网络编程。

[GroupChatServer](./src/main/java/com/netty/demo/nio/groupchat/GroupChatServer.java)
[GroupChatClient](./src/main/java/com/netty/demo/nio/groupchat/GroupChatClient.java)

### NIO与零拷贝
零拷贝：零拷贝是指计算机执行IO操作时，CPU不需要将数据从内核空间拷贝到用户空间（不需要从KB拷贝到UB），
从而可以减少上下文切换以及CPU的拷贝时间。它是一种I/O操作优化技术。  

a.零拷贝，是从操作系统的角度来说的, 就是内核缓冲区之间没有数据是重复的（只有kernel buffer有一份数据）。  
b.零拷贝不仅带来更少的数据复制，还能带来其他的性能优势，例如更少的上下文切换，更少的CPU缓存伪共享以及无CPU校验和计算。  

1）零拷贝是网络编程的关键，很多性能优化都离不开  
2）在java程序中，常用的零拷贝有mmap（内存映射）和senFile。  
3）NIO中的零拷贝  

#### 传统IO数据读写
**概念：**
Hard Drive（HD）:  
DMA copy(DMA): Direct memory access  直接内存拷贝（不使用CPU）。首先从硬盘拷贝到内核缓冲  
kernel buffer(KB):  
CPU copy(CPU):  
user buffer(UB):  
socket buffer(SB):  
protocol engine(PE):  
1）传统IO的一段代码
```java


File file = new File("test.txt");
RandomAccessFile raf = new RandomAccessFile(file, "rw");

byte[] arr = new byte[(int)file.length()];
raf.read(arr);

Socket socket = new ServerSocket(8080).accept();
socket.getOutputStream().write(arr);
```
![传统IO数据传输示意](traditionalIoDataTransfer.png)
```mermaid
flowchart LR
HD(Hard Dive)-->|DMA copy|KB(kernel buffer)-->|CPU copy|UB(user buffer)-->|CPU copy|SB(socket buffer)-->|DMA copy|PE(protocol engine)
```

#### mmap优化
1）mmap通过内存映射，将文件映射到内存缓冲区，同时用户空间可以共享内核空间的数据。这样，在进行网络传输时，就可以减少
内核空间到用户空间的拷贝次数。

```mermaid
flowchart LR
HD(Hard Dive)-->|DMA copy|KB(kernel buffer)-->|shared|UB(user buffer)
KB<-->|CPU copy|SB(socket buffer)-->|DMA copy|PE(protocol engine)
```

#### sendFile优化
1）linux2.1 版本提供了sendFile函数：数据根本不需要经过用户态，直接从内核缓冲区进入到Socket Buffer，同时
由于和用户态完全无关，就减少了一次上下文切换。
```mermaid
flowchart LR
HD(Hard Dive)-->|DMA copy|KB(kernel buffer)
KB<-->|CPU copy|SB(socket buffer)-->|DMA copy|PE(protocol engine)
```
2）linux 2.4 版本中，做了一些修改，避免了从内核缓冲区拷贝到Socket buffer的操作，直接拷贝到协议栈，从而再一次减少了数据拷贝,
这里实际上还是存在kernel buffer到socket buffer 的CPU 拷贝，拷贝的信息很少，比如length，offset，消耗低，可以忽略。
```mermaid
flowchart LR
HD(Hard Dive)-->|DMA copy|KB(kernel buffer)-->|copy desc|SB(socket buffer)-->|DMA copy|PE(protocol engine)
KB-->|DMA copy|PE(protocol engine)
```

#### mmap 和senFile的区别
1）mmap适合小数据读写，sendFile适合大文件的传输    
2）mmap需要四次上下文切换，3次数据拷贝；sendFile需要3次上下文切换，最少2次数据拷贝。  
3）senFile可以利用DMA方式，减少CPU拷贝，mmap不能（必须从内核拷贝到缓冲区）  

#### NIO零拷贝案例
```java


```

```mermaid
flowchart TB
%% Nonblocking I/O 原理图  
S(Selector 实例)
SK(SelectionKey)---|注册|R(通信管道 )---C(Client)
SK---|注册|R1(通信管道 )---C1(Client)
SK --> S
T(Thread)---S
Server(服务器\n ServerSocketChannel\n 1.监听端口\n  2.获得和客户端连接的通道SocketChannel\n   3.每个客户端都会生成对应的通道SocketChannel)
Server --->|注册|S  
%%style S fill:#F0F0F0,stroke:#333,stroke-width:2px;
```

### Java AIO基本介绍
1）JDK 7引入了Asynchronous I/O， 即AIO。在进行I/O编程中常用到两种模式： Reactor和Proactor。Java NIO 就是Reactor，当有事件触发时，服务端得到通知进行相应的处理。
2）AIO即NIO2.0，叫做异步不阻塞IO。 AIO引入异步通道概念，采用了Proactor模式，简化了程序编写，有效的请求才启动线程，他的特点是先由操作系统完成后才通知
服务端程序启动线程去处理，一般适用于连接数较多且连接时间较长的应用。
3）目前AIO还没有广泛应用，Netty也是基于NIO，而不是AIO。

## BIO NIO AIO对比表
| |BIO|NIO|AIO|
|---|---|---|---|
|IO模型|同步阻塞|同步非阻塞（多路复用）|异步非阻塞|
|编程难度|简单|复杂|复杂|
|可靠性|差|好|好|
|吞吐量|低|高|高|


# Netty概述
## 原生NIO存在的问题
1）NIO的类库和API复杂，使用麻烦。
2）需要具备其他额外技能：要熟悉java多线程编程，因为NIO编程涉及到Reactor模式。
3）开发工作量和难度都非常大：例如客户端面临断连重连、网络闪断、半包读写、失败缓存、网络拥塞和异常流的处理等等。
4）JDK NIO的bug：例如臭名昭著的Epoll Bug，他会导致Selector空轮询，最终导致CPU 100%。直到JDK 1.7 版本依旧存在，没有得到根本解决。

## Netty的优点
Netty对JDk自带的NIO的API进行了封装，解决了上述问题：
1）设计优雅：适用于各种传输类型的统一API阻塞和非阻塞Socket；基于灵活且可扩展的事件模型，可以清晰的分离关注点；高度可定制的线程模型-单线程，一个或多个线程池。
2）使用方便：详细记录的Javadoc，没有其他依赖项
3）高性能、吞吐量更高：延迟更低减少资源消耗；最小化不必要的内存复制。
4）安全：完整的SSL/TLS和StartTLS支持。
5）社区活跃、不断更新：社区活跃、版本迭代周期短，发现bug可以被及时修复，同时更多的新功能会被加入。





<table>
  <tr>
    <th>标题1</th>
    <th>标题2</th>
  </tr>
  <tr>
    <td>内容1</td>
    <td>内容2</td>
  </tr>
</table>

```java 
# 这是一个代码示例 
    // fileChannel 本地文件写数据
    public static void main(String[] args) throws IOException {
        String str = "Hello, World!";

        // 创建一个输出流-》channel
        FileOutputStream fileOutputStream = new FileOutputStream(new File("netty/src/main/java/com/netty/demo/nio/channel/file01.txt"));

        // 通过fileOutputStream 获取一个fileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str放到byteBuffer
        byteBuffer.put(str.getBytes());
        //将byteBuffer进行flip
        byteBuffer.flip();
        // 将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

```

[FileChannel01](./src/main/java/com/netty/demo/nio/channel/FileChannel01.java)













