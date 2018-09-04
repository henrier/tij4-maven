//: typeinfo/toys/GenericToyTest.java
// Testing class Class.
package typeinfo.toys;

public class GenericToyTest {
  public static void main(String[] args) throws Exception {
    Class<FancyToy> ftClass = FancyToy.class;
    // Produces exact type:
    FancyToy fancyToy = ftClass.newInstance();
    Class<? super FancyToy> up = ftClass.getSuperclass();    //按理说，虚拟机是能够知道父类类型是Toy的，但实际上却不行，只能是某个类。
    // This won't compile:
    // Class<Toy> up2 = ftClass.getSuperclass();
    // Only produces Object:
    Object obj = up.newInstance();                           //由于Class对象的引用中的泛型信息只告诉虚拟机是某个类，所以实例化出来的是Object对象。
  }
} ///:~
