package dao;

import java.util.List;

import model.Guitar;

public interface IGuitar {
	public List<Guitar> getAllGuitars();

	void addGuitar(Guitar guitar);

	void deleteGuitar(String serialNumber);

}
