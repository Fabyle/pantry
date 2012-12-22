package model;

public class ClasseA {
	public static void main(String[] args) {
	      InterfaceI b = new Factory().getDependency();
	      b.someMethod();
	   }

}
