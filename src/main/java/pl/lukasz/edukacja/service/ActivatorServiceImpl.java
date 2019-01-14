package pl.lukasz.edukacja.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukasz.edukacja.dao.ActivatorDAO;
import pl.lukasz.edukacja.domain.Activator;

@Service
@Transactional
public class ActivatorServiceImpl implements ActivatorService{

	@Autowired
	ActivatorDAO activatorDAO;
	
	@Override
	@Transactional
	public void addActivator(Activator activator){
		activatorDAO.addActivator(activator);
	}

	@Override
	@Transactional
	public Activator getActivator(int userId) {
		return activatorDAO.getActivator(userId);
	}

	@Override
	@Transactional
	public void deleteActivator(int userId) {
		activatorDAO.deleteActivator(userId);
	}
	

}
