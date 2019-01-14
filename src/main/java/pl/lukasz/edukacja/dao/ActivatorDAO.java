package pl.lukasz.edukacja.dao;

import pl.lukasz.edukacja.domain.Activator;

public interface ActivatorDAO {
	public void addActivator(Activator activator);
	public Activator getActivator(int userId);
	public void deleteActivator(int userId);
}
