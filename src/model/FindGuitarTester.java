package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FindGuitarTester {

	public static void main(String[] args) {
		// Set up Rick's inventory
		Inventory inventory = new Inventory();
		initializeInventory(inventory);

		Map properties = new HashMap();
		properties.put("builder", Builder.GIBSON);
		properties.put("backWood", Wood.MAPLE);
		GuitarSpec whatBryanLikes = new GuitarSpec(properties);

		List matchingGuitars = inventory.search(whatBryanLikes);
		if (!matchingGuitars.isEmpty()) {
			System.out.println("Bryan, you might like these Guitars:");
			for (Iterator i = matchingGuitars.iterator(); i.hasNext();) {
				Guitar Guitar = (Guitar) i.next();
				GuitarSpec spec = Guitar.getSpec();
				System.out.println("We have a " + spec.getProperty("GuitarType") + " with the following properties:");
				for (Iterator j = spec.getProperties().keySet().iterator(); j.hasNext();) {
					String propertyName = (String) j.next();
					if (propertyName.equals("GuitarType"))
						continue;
					System.out.println("    " + propertyName + ": " + spec.getProperty(propertyName));
				}
				System.out.println("  You can have this " + spec.getProperty("GuitarType") + " for $"
						+ Guitar.getPrice() + "\n---");
			}
		} else {
			System.out.println("Sorry, Bryan, we have nothing for you.");
		}
	}

	private static void initializeInventory(Inventory inventory) {
		Map properties = new HashMap();
		Guitar guitar=null;
		properties.put("builder", Builder.COLLINGS);
		properties.put("model", "CJ");
		properties.put("type", Type.ACOUSTIC);
		properties.put("numStrings", 6);
		properties.put("topWood", Wood.INDIAN_ROSEWOOD);
		properties.put("backWood", Wood.SITKA);
		guitar=new Guitar("11277", 3999.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);

		properties.put("builder", Builder.MARTIN);
		properties.put("model", "D-18");
		properties.put("topWood", Wood.MAHOGANY);
		properties.put("backWood", Wood.ADIRONDACK);
		guitar=new Guitar("122784", 5495.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);

		properties.put("builder", Builder.FENDER);
		properties.put("model", "Stratocastor");
		properties.put("type", Type.ELECTRIC);
		properties.put("topWood", Wood.ALDER);
		properties.put("backWood", Wood.ALDER);
		guitar=new Guitar("V95693", 1499.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
		guitar=new Guitar("V9512", 1549.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
		
		properties.put("builder", Builder.GIBSON);
		properties.put("model", "Les Paul");
		properties.put("topWood", Wood.MAPLE);
		properties.put("backWood", Wood.MAPLE);
		guitar=new Guitar("70108276", 2295.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
		
		properties.put("model", "SG '61 Reissue");
		properties.put("topWood", Wood.MAHOGANY);
		properties.put("backWood", Wood.MAHOGANY);
		guitar=new Guitar("82765501", 1890.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
		
		properties.put("type", Type.ACOUSTIC);
		properties.put("model", "F-5G");
		properties.put("backWood", Wood.MAPLE);
		properties.put("topWood", Wood.MAPLE);
		properties.remove("numStrings");
		guitar=new Guitar("9019920", 5495.99, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
		
		properties.put("model", "RB-3 Wreath");
		properties.remove("topWood");
		properties.put("numStrings", 5);
		guitar=new Guitar("8900231", 2945.95, new GuitarSpec(properties));
		inventory.addGuitar(guitar);
	}
}
