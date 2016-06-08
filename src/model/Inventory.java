package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dao.IGuitar;
import dao.dataAccess;

public class Inventory {

	private List<Guitar> inventory;

	public Inventory() {
		inventory = new LinkedList<Guitar>();
	}

	public void addGuitar(Guitar guitar) {
		inventory.add(guitar);
	}

	public Guitar get(String serialNumber) {
		for (Iterator<Guitar> i = inventory.iterator(); i.hasNext();) {
			Guitar Guitar = (Guitar) i.next();
			if (Guitar.getSerialNumber().equals(serialNumber)) {
				return Guitar;
			}
		}
		return null;
	}

	public List<Guitar> search(GuitarSpec searchSpec) {
		List<Guitar> matchingGuitars = new LinkedList<Guitar>();
		for (Iterator<Guitar> i = inventory.iterator(); i.hasNext();) {
			Guitar Guitar = (Guitar) i.next();
			if (Guitar.getSpec().matches(searchSpec))
				matchingGuitars.add(Guitar);
		}
		return matchingGuitars;
	}
	
	public void initialize(){
		IGuitar ig = dataAccess.createGuitarDao();
		List<Guitar> allGuitars = ig.getAllGuitars();
		for (Iterator<Guitar> i = allGuitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			addGuitar(guitar);
		}
	}
}
