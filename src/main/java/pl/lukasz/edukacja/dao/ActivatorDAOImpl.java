package pl.lukasz.edukacja.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.lukasz.edukacja.domain.Activator;
import pl.lukasz.edukacja.domain.Pesel;
import pl.lukasz.edukacja.domain.User;

@Repository
public class ActivatorDAOImpl implements ActivatorDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addActivator(Activator activator){
		sessionFactory.getCurrentSession().save(activator);
	}

	@Override
	public Activator getActivator(int userId){
		List<Activator> activators = new ArrayList<Activator>();
		activators = sessionFactory.getCurrentSession().createQuery("from Activator where user_id=?").setParameter(0, userId).list();
		
		if(activators.size() > 0){
			return activators.get(0);
		}
		else return null;
	}

	@Override
	public void deleteActivator(int userId) {
		Activator activator = getActivator(userId);
		sessionFactory.getCurrentSession().delete(activator);
	}
}
