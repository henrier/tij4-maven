package generics;
// {CompileTimeError} (Won't compile)

interface Payable<T> {}

class Employee implements Payable<Employee> {}
//cc//class Hourly extends Employee
//cc//  implements Payable<Hourly> {} ///:~
