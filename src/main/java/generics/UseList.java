package generics;
// {CompileTimeError} (Won't compile)
import java.util.*;

public class UseList<W,T> {
  //cc//  void f(List<T> v) {}
  void f(List<W> v) {}
} ///:~
