package generics;
// {CompileTimeError} (Won't compile)

public class Erased<T> {
  private final int SIZE = 100;
  public static void f(Object arg) {
    //cc//if(arg instanceof T) {}          // Error
    //cc//T var = new T();                 // Error
    //cc//T[] array = new T[SIZE];         // Error
    //cc//T[] array = (T)new Object[SIZE]; // Unchecked warning
  }
} ///:~
