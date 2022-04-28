配置好项目后debug，会提示source code does not match the bytecode，这时候我们需要重新编译一下代码。
解决：
1. 在script目录执行：`/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/bin/javac -J-Xms1024m -J-Xmx1024m -sourcepath /Users/chunrun/IdeaProjects/xinghe-study-jdk/src/jdk -cp /Users/chunrun/IdeaProjects/xinghe-study-jdk/script/rt.jar:/Users/chunrun/IdeaProjects/xinghe-study-jdk/script/tools.jar -d /Users/chunrun/IdeaProjects/xinghe-study-jdk/classes -g @filelist.txt  >> log.txt 2>&1`
2. 切换到classes目录，执行：`jar cf0 rt_debug.jar * `
3. 把生成的re_debug.jar复制到jdk指定的目录中：`sudo cp rt_debug.jar /Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/jre/lib/endorsed`

然后就可以了。

参考：https://www.cnblogs.com/vitoboy/p/15683218.html
原文中的脚本运行不成功，手动吧。。。