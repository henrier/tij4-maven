package exceptions;
// Programmatic access to stack trace information.

/**
 * 在zhangc方法中创建了异常对象，该异常对象立刻持有了异常栈信息（其实就是对zhangc方法的调用栈）。
 * 无论该异常对象在何处被捕获，其异常信息都不会发生改变。
 * 除非调用Throwable的fillInStackTrace()方法来改变异常栈的信息。
 * @see Rethrowing
 */
public class WhoCalled {
    static void zhangc() throws Exception{
        throw new Exception();
    }

    static void f() {
        // Generate an exception to fill in the stack trace
        try {
            zhangc();
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }

    static void g() {
        f();
    }

    static void h() {
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("--------------------------------");
        g();
        System.out.println("--------------------------------");
        h();
    }
} /* Output:
f
main
--------------------------------
f
g
main
--------------------------------
f
g
h
main
*///:~
