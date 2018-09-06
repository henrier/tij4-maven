package typeinfo;
// Looking for particular methods in a dynamic proxy.

import java.lang.reflect.*;

import static net.mindview.util.Print.*;

/**
 * 代理(的调用处理器)
 * 相比于SimpleProxyDemo展示的代理模式，动态代理里面的代理不需要实现被代理类的接口。
 */
class MethodSelector implements InvocationHandler {
    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    /**
     * 该方法中可以添加代理的行为。
     * 通过对method进行判断，可以对被代理对象的指定方法设置特定的代理行为。
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting"))
            print("Proxy detected the interesting method");
        return method.invoke(proxied, args);
    }
}

/**
 * 接口
 */
interface SomeMethods {
    void boring1();

    void boring2();

    void interesting(String arg);

    void boring3();
}

/**
 * 被代理对象
 */
class Implementation implements SomeMethods {
    public void boring1() {
        print("boring1");
    }

    public void boring2() {
        print("boring2");
    }

    public void interesting(String arg) {
        print("interesting " + arg);
    }

    public void boring3() {
        print("boring3");
    }
}

class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(        //创建动态代理
                SomeMethods.class.getClassLoader(),
                new Class[]{SomeMethods.class},
                new MethodSelector(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
} /* Output:
boring1
boring2
Proxy detected the interesting method
interesting bonobo
boring3
*///:~
