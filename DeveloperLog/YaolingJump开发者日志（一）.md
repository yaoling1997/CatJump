&emsp;&emsp;写完PokeCats之后意犹未尽，还想做一个更加有趣的游戏，比如说像超级玛丽那样。

&emsp;&emsp;游戏的主角就选“瑶玲”了，这是我小时候最喜欢的动画片《瑶玲啊瑶玲》的女主角。我一直想玩和瑶玲相关的游戏，不过真是少之又少，找到的那几个又感觉玩起来没什么意思。不如自己动手，丰衣足食，啦啦啦~

&emsp;&emsp;但是直接用android原生组件的话貌似有点儿麻烦，网上一搜，没想到还可以用别人写的android游戏引擎啊，真有意思。

&emsp;&emsp;像我这样的小白当然要用免费的游戏引擎啦，正好有一款开源的android游戏引擎[LGame](https://github.com/cping/LGame)(不止支持android，还可以很方便地移植到PC)。

&emsp;&emsp;虽然这引擎一年没更新了，但是应该还能凑合着用吧。最蛋疼的是这玩意没有文档，不过有一个叫做Examples的文件夹里有大量游戏实例，所以只能通过这些实例来玩这个东西了。

&emsp;&emsp;我用的LGame是0.5版本的，正好它也有个类似超级玛丽游戏的代码实例，我就照着它的用了。

&emsp;&emsp;要实现android版的也不难，只需要创建一个继承Loon的activity，然后往这个activity里丢一个screen就行了。

<img src="https://i.loli.net/2018/02/16/5a866f079e279.png" alt="1.png" title="1.png" />
<img src="https://i.loli.net/2018/02/16/5a866f079fc59.png" alt="2.png" title="2.png" />

&emsp;&emsp;把游戏主角的图片换成了瑶玲，现在长成这样：
<img src="https://i.loli.net/2018/02/16/5a866af99a00b.jpg" alt="1.jpg" title="1.jpg" style="zoom:60%"/>

&emsp;&emsp;其它功能以后慢慢实现吧，反正现在也不太会玩这个LGame。

