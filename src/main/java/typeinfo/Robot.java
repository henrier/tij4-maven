package typeinfo;

import java.util.*;

import net.mindview.util.*;

/**
 * @see SnowRemovalRobot
 */
public interface Robot {
    String name();

    String model();

    List<Operation> operations();

    class Test {                                     //使用嵌套类来提供测试用例。没有满足maven约定。
        public static void test(Robot r) {
            if (r instanceof Null)
                System.out.println("[Null Robot]");
            System.out.println("Robot name: " + r.name());
            System.out.println("Robot model: " + r.model());
            for (Operation operation : r.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
        }
    }
} ///:~
