&emsp;&emsp;LGame用起来真是各种蛋疼，插背景都可以显示不出来。在屏幕结束后释放资源，重载该屏幕时再setbackground也不行，直接用Lpaper当background更不行，会把tilemap上的东西全部挡住。

&emsp;&emsp;不过好在魔高一尺道高一丈，我直接让一个ActionObject作为背景，效果如下：
<img src="https://i.loli.net/2018/03/07/5a9f3547da6a8.jpg" alt="1.jpg" title="1.jpg" />
&emsp;&emsp;背景丑是丑了点，不过总比纯黑色的好看。

&emsp;&emsp;然后操作手柄放在左侧，跳跃和左右移动按键在一起，貌似给玩家的游戏体验不好。然而LGame自带的操作手柄就是长成这个样子的。于是我用LPaper仿制了一个。像这样：
<img src="https://i.loli.net/2018/03/07/5a9f67f2c60e2.jpg" alt="1.jpg" title="1.jpg" />
&emsp;&emsp;然而事情并没有我想象的那么简单，LPaper是有bug的。一直用手按着这个LPaper，它的downClick方法有时是正常的，按着不松就会一直调用，然而有时候连续调用几次就不会调用了。它的upClick方法也有问题，一根手指按着一个LPaper，再用另一根手指点一下其它地方，再松开原来那根手指，upClick方法就不会被调用了。说白了就是左右方向键和跳跃键不能同时按着，玩起来贼别扭。如果上天再给我一次选择的机会，我绝对会用cocos（应该会比LGame好吧，虽然没用过）。

&emsp;&emsp;没办法，又得另起灶台，我费了好大劲用android原生组件给它实现了，现在玩起来顺畅多了。
<img src="https://i.loli.net/2018/03/07/5a9fe20b38010.gif" alt="2_clip.gif" title="2_clip.gif" />
