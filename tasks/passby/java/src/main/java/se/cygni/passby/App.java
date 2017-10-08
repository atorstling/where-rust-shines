package se.cygni.passby;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Dog aDog = new Dog("Max");
    System.out.println("The dog's name is " + aDog.getName());
    foo(aDog);
    System.out.println("After calling foo the dog's name is: " + aDog.getName());

    bar(aDog);
    System.out.println("After calling bar the dog's name is: " + aDog.getName()); 
  }

  public static void foo(Dog d) {
    d = new Dog("Fifi");
  }

  public static void bar(Dog d) {
    d.setName("Fifi");
  }

  private static class Dog {
    String name;

    public Dog(String name) {
      this.name = name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }
  }
}
