package pl.lukasz.edukacja.service;

import pl.lukasz.edukacja.domain.Activator;

public interface ActivatorService {
	public void addActivator(Activator activator);
	public Activator getActivator(int userId);
	public void deleteActivator(int userId);
}
