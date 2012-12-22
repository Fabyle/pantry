package javafxProperties.highlevel;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class Bill {

	// Define the property
	private DoubleProperty amountDue = new SimpleDoubleProperty();

	// Define a getter for the property's value
	public final double getAmountDue() {
		return amountDue.get();
	}

	// Define a setter for the property's value
	public final void setAmountDue(double value) {
		amountDue.set(value);
	}

	// Define a getter for the property itself
	public DoubleProperty amountDueProperty() {
		return amountDue;
	}

}

public class Main4 {

	public static void main(String[] args) {

		Bill bill1 = new Bill();
		Bill bill2 = new Bill();
		Bill bill3 = new Bill();

		NumberBinding total = Bindings.add(
				bill1.amountDueProperty().add(bill2.amountDueProperty()),
				bill3.amountDueProperty());

		// the difference between the two is that ObservableValue wraps a value
		// and fires its changes to any registered ChangeListener, whereas
		// Observable (which does not wrap a value) fires its changes to any
		// registered InvalidationListener.

		total.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable o) {
				System.out.println("The binding is now invalid.");
			}
		});

		// First call makes the binding invalid
		bill1.setAmountDue(200.00);

		// The binding is now invalid
		bill2.setAmountDue(100.00);
		bill3.setAmountDue(75.00);

		// Make the binding valid...
		System.out.println(total.getValue());

		// Make invalid...
		bill3.setAmountDue(150.00);

		// Make valid...
		System.out.println(total.getValue());
	}
}