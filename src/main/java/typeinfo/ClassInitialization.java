package typeinfo;
import java.util.*;

class Initable {
  static final int staticFinal = 47;
  //static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
  static final int staticFinal2 = Initable2.staticNonFinal+1;
  static {
    System.out.println("Initializing Initable");
  }
}

class Initable2 {
  static int staticNonFinal = 147;
  static {
    System.out.println("Initializing Initable2");
  }
}

class Initable3 {
  static int staticNonFinal = 74;
  static {
    System.out.println("Initializing Initable3");
  }
}

/**
 * 为了使用类而做的准备工作实际包含三个步骤：
 * 1.加载。
 *   这是由类的加载器执行的。该步骤将查找字节码（通常在classpath所指定的路径中查找，但这并非是必须的），并从这些字节码中创建一个Class对象。（而非类的对象）
 * 2.链接。
 *   在链接阶段将验证类中的字节码，为静态域分配存储空间（构造器也隐式的是静态的）。并且如果必须（存在外部引用）的话，将解析这个类创建的对其他类的引用(对其他类执行该3步)。
 * 3.初始化。
 *   如果该类具有超类，则对其进行初始化。执行静态初始化器和静态初始化块。
 */
public class ClassInitialization {
  static {
    System.out.println("Initializing ClassInitialization");
  }
  public static Random rand = new Random(47);
  public static void main(String[] args) throws Exception {
    Class initable = Initable.class;
    System.out.println("After creating Initable ref");
    // Does not trigger initialization:
    System.out.println(Initable.staticFinal);          //1通过类字面常量获取Class对象引用，不会执行第三步。访问final静态变量，不会执行第三步。
    // Does trigger initialization:
    System.out.println(Initable.staticFinal2);         //2通过类字面常量获取Class对象引用。相比于1，由于静态变量依赖了别的类（不是一个编译期常量），所以会执行三步。
    // Does trigger initialization:
    System.out.println(Initable2.staticNonFinal);      //3未通过类字面常量获取过Class对象的引用。访问非final的静态变量，会执行三步。
    Class initable3 = Class.forName("typeinfo.Initable3");  //通过forName方法加载类，也相当于对类的访问，会直接导致对象的实例化，即会执行三步。
    System.out.println("After creating Initable3 ref");
    System.out.println(Initable3.staticNonFinal);
  }
} /* Output:
After creating Initable ref
47
Initializing Initable
258
Initializing Initable2
147
Initializing Initable3
After creating Initable3 ref
74
*///:~
