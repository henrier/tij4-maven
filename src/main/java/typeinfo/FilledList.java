package typeinfo;
import java.util.*;

class CountedInteger {
  private static long counter;
  private final long id = counter++;
  public String toString() { return Long.toString(id); }
}

public class FilledList<T> {
  private Class<T> type;
  public FilledList(Class<T> type) { this.type = type; }	 //这个例子想表达，泛型的加入，可以限定入参必须是T类型的Class对象。
  public List<T> create(int nElements) {
    List<T> result = new ArrayList<T>();
    try {
      for(int i = 0; i < nElements; i++)
        result.add(type.newInstance());                      //type作为一个纸箱Class对象的引用，由于泛型，它有了Class对象的类型信息，所以实例化出来的不再是Object类型的。
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static void main(String[] args) {
    FilledList<CountedInteger> fl =
      new FilledList<CountedInteger>(CountedInteger.class);
    System.out.println(fl.create(15));
  }
} /* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
*///:~
