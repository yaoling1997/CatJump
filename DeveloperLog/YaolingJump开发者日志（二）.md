&emsp;&emsp;熟悉了一点LGame里的套路，可以正式开工了。

&emsp;&emsp;增加了一个信息栏，显示得分、硬币数、生命值和当前关卡（仿照了超级玛丽的布局）。

&emsp;&emsp;准备瑶玲的各种动画（静止、奔跑、跳跃、趴下、休息和死亡等）。

&emsp;&emsp;设计了第一关的关卡。

&emsp;&emsp;增加了敌人与敌人的碰撞（这玩意还得自己实现，分情况讨论一下）。

&emsp;&emsp;增加了通往下一关的传送门（第二关还没有实现）。

&emsp;&emsp;下面介绍一下具体玩法吧。（以下截图来自于卡的要死的Genymotion模拟器）

&emsp;&emsp;一开始瑶玲会被空降到一个位置，迎面走来一只萌萌哒板栗仔。
<img src="https://i.loli.net/2018/02/19/5a8ab9bf37d90.png" alt="1.png" title="1.png" />

&emsp;&emsp;如果瑶玲要和板栗仔亲密接触的话，就会被它弄死。
<img src="https://i.loli.net/2018/02/19/5a8ab9bf7d752.png" alt="2.png" title="2.png" />

&emsp;&emsp;瑶玲也可以选择把它踩扁并加分。
<img src="https://i.loli.net/2018/02/19/5a8ab9bf89984.png" alt="3.png" title="3.png" />

&emsp;&emsp;吃到硬币也可以加分，收集100个硬币会自动兑换成一条命（据说超级玛丽也是这样设计的）。
<img src="https://i.loli.net/2018/02/19/5a8ab9bf747e9.png" alt="4.png" title="4.png" />

&emsp;&emsp;这个是问号砖。
<img src="https://i.loli.net/2018/02/19/5a8ab9bec9083.png" alt="5.png" title="5.png" />

&emsp;&emsp;顶到会触发一些事件，比如说出现一个金币。
<img src="https://i.loli.net/2018/02/19/5a8aba7d0e108.png" alt="6.png" title="6.png" />

&emsp;&emsp;或者一个板栗仔啥的。
<img src="https://i.loli.net/2018/02/19/5a8aba63171d1.png" alt="7.png" title="7.png" />

&emsp;&emsp;这个是霹雳龟（不知道取什么名字好-_-|）。
<img src="https://i.loli.net/2018/02/19/5a8aba632a8a3.png" alt="8.png" title="8.png" />

&emsp;&emsp;踩它它就会缩进壳里进行防御。
<img src="https://i.loli.net/2018/02/19/5a8aba63353ac.png" alt="9.png" title="9.png" />

&emsp;&emsp;瑶玲可以一脚把它踢飞，并干掉沿途的一些怪物，不过这玩意撞到墙会反弹的。
<img src="https://i.loli.net/2018/02/19/5a8aba6333b4b.png" alt="10.png" title="10.png" />

&emsp;&emsp;设计的是瑶玲跳跃时最多比3个格子高一点，可以撞到第4个格子，有些地方需要借助踩怪物从而跳得更高，比如这里。
<img src="https://i.loli.net/2018/02/19/5a8aba648ea3f.png" alt="12.png" title="12.png" />

&emsp;&emsp;右上角的那个通向下一关的传送门。
<img src="https://i.loli.net/2018/02/19/5a8abe95b6f34.png" alt="17.png" title="17.png" />

&emsp;&emsp;写到这里，一些问题不得不停下脚步思考一下：这个游戏的背景故事是什么？瑶玲为什么要在小黑屋里与板栗仔和霹雳龟做斗争？游戏主人公的主要矛盾和最终目的究竟是什么？

&emsp;&emsp;所有问题归结在一起就是这个游戏的剧情是什么？

&emsp;&emsp;我有以下初步构想：
####1、按照原剧的剧情来设计，瑶玲需要收集散落在人间的圆梦书才能回到天国...

&emsp;&emsp;但是作为一个把这部优秀的动画片从头到尾看了好几遍的我来说，这个设计不免有点缺乏新意，有老调重弹之嫌。

####2、仿照超级玛丽的剧情（大反派库巴把玛丽的公主女友抓走了），剧中的男主角江贤被邪恶势力抓走了，瑶玲需要去救出江贤...

&emsp;&emsp;江贤就是一个普通的小男孩啊，邪恶势力抓他作甚？

####3、瑶玲想念天国的小伙伴，想回家，但是必须进入到邪恶的城堡经过重重考验拿到罕见的宝物才行...

&emsp;&emsp;想念天国的伙伴照照镜子不就行了，而且这个剧情不够刺激。

####4、大反派黑狐狸解除封印，卷土重来，攻陷了天国，抓走了所有其它狐狸。瑶玲作为唯一幸存的天国狐狸踏上了封印黑狐狸，解救族人的征途...
&emsp;&emsp;有没有《雷神3》的即视感？这个剧情很中二，我喜欢！

&emsp;&emsp;那就这么定了！

&emsp;&emsp;最后祝大家新年快乐，狗年大吉！

<img src="https://i.loli.net/2018/02/19/5a8ace8d40775.png" alt="yaoling_dog.png" title="yaoling_dog.png" style="zoom:50%"/>
