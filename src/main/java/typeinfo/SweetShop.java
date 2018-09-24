package typeinfo;
// Examination of the way the class loader works.
import static net.mindview.util.Print.*;

class Candy {
  static { print("Loading Candy"); }
}

class Gum {
  static { print("Loading Gum"); }
}

class Cookie {
  static { print("Loading Cookie"); }
}

public class SweetShop {
  public static void main(String[] args) {	
    print("inside main");
    new Candy();                                         //编译时检查，Candy必须是已存在的类。
    print("After creating Candy");
    try {
      Class.forName("typeinfo.Gum");                     //直到运行时才能知道Gum类是否真的存在，绕过了编译时检查。
    } catch(ClassNotFoundException e) {                  //forName方法会抛出一个非运行时异常（受检查的异常。）很有意思，在运行时抛出一个非运行时异常。运行时异常是不受检查的异常。
      print("Couldn't find Gum");
    }
    print("After Class.forName(\"Gum\")");
    new Cookie();
    print("After creating Cookie");
  }
} /* Output:
inside main
Loading Candy
After creating Candy
Loading Gum
After Class.forName("Gum")
Loading Cookie
After creating Cookie
*///:~
