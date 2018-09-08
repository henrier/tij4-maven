package typeinfo;
// Using a dynamic proxy to create a Null Object.

import java.lang.reflect.*;
import java.util.*;

import net.mindview.util.*;

class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {       //用Robot的具体子类构造代理处理器
        nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Null, Robot {              //用内部类定义被代理对象。
        public String name() {                                 //被代理对象的name方法返回的是代理的成员变量。这是使用内部类定义被代理对象比较有意思的地方。
            return nullName;
        }

        public String model() {
            return nullName;
        }

        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);                   //对代理中的所有方法的调用，代理都将直接委托给被代理对象（NRobot）处理。
    }
}

public class NullRobot {                                       //创建动态代理对象的宿主环境
    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[]{Null.class, Robot.class},
                new NullRobotProxyHandler(type));     //这里体现了动态代理相比于静态代理的优势：不需要为每一个具体类型生成代理类。
    }

    public static void main(String[] args) {
        Robot[] bots = {
                new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class)
        };
        for (Robot bot : bots)
            Robot.Test.test(bot);
    }
} /* Output:
Robot name: SnowBee
Robot model: SnowBot Series 11
SnowBee can shovel snow
SnowBee shoveling snow
SnowBee can chip ice
SnowBee chipping ice
SnowBee can clear the roof
SnowBee clearing roof
[Null Robot]
Robot name: SnowRemovalRobot NullRobot
Robot model: SnowRemovalRobot NullRobot
*///:~
