package model;

public class Factory {

	public InterfaceI getDependency() {
		return new ClasseB();
	}
}
