package typeinfo;
import java.util.*;

//子类重写父类的toString方法，多态。
//在多态的过程中，子类的行为得到保留，但是子类的类型信息丢失了。
abstract class Shape {
  void draw() { System.out.println(this + ".draw()"); }  //打印this的过程会调用头String方法。
  abstract public String toString();
}

class Circle extends Shape {
  public String toString() { return "Circle"; }
  public void roll() {
    System.out.println("I am rolling");
  }
}

class Square extends Shape {
  public String toString() { return "Square"; }
}

class Triangle extends Shape {
  public String toString() { return "Triangle"; }
}

class Ellipse {
  public void say() {
    System.out.println("I am can not rolling");
  }
}

public class Shapes {
  public static void main(String[] args){
    List<Shape> shapeList = Arrays.asList(
      new Circle(), new Square(), new Triangle()
    );
    for(Shape shape : shapeList) {
      shape.draw();                  //编译时无法知道shape的具体类型。在运行时，依然无法知道shape的具体类型，但是由于动态绑定，shape会具有具体子类型的行为。
      //shape.roll();                //多态导致编译时类型信息丢失，无法直接调用shape.roll()方法。
      if(shape.getClass().isAssignableFrom(Circle.class)) {     //可以通过RTTI，在运行时获取类的具体信息。要使用RTTI，首先要在运行时获取当前的Class对象的引用。
        ((Circle)shape).roll();
      }
      Class clazz = shape.getClass();
      if(clazz.getSimpleName().equals("Circle")) {
        System.out.println(clazz.getMethods());
      }
    }

    try {
      Class clazz2 = Class.forName("typeinfo.Ellipse");                  //相比于Circle等类，在编译时，Ellipse类可以不存在。
      Ellipse ellipse = (Ellipse) clazz2.newInstance();
      ellipse.say();
    }catch (ClassNotFoundException e) {
      System.out.println("Ellipse类文件不存在，请将类文件放置相关目录");
    }catch (InstantiationException e2) {
      System.out.println("初始化对象失败。");
    }catch (IllegalAccessException e3) {
      System.out.println("当前类型为Class，无法对其实例化。");
    }



  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
