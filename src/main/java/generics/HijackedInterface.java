package generics;
// {CompileTimeError} (Won't compile)

//cc//class Cat extends ComparablePet implements Comparable<Cat>{
  // Error: Comparable cannot be inherited with
  // different arguments: <Cat> and <Pet>
//cc//  public int compareTo(Cat arg) { return 0; }
//cc//} ///:~
