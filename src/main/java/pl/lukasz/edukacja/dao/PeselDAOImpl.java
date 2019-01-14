package pl.lukasz.edukacja.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.lukasz.edukacja.domain.Pesel;

@Repository
public class PeselDAOImpl implements PeselDAO{
	@Autowired
    SessionFactory sessionFactory;
	
    public void addPesel(Pesel pesel) {
        sessionFactory.getCurrentSession().save(pesel);
    }
 
    public List<Pesel> listPesel() {
 
        return sessionFactory.getCurrentSession().createQuery("from Pesel order by id").list();
    }
 
    public void removePesel(int id) {
        Pesel pesel = (Pesel) sessionFactory.getCurrentSession().load(
                Pesel.class, id);
        if (null != pesel) {
            sessionFactory.getCurrentSession().delete(pesel);
        }
 
    }
    public Pesel getPesel(int id) {
    	
    	return (Pesel)sessionFactory.getCurrentSession().get(Pesel.class, id);
    }
    
  public void editPesel(Pesel pesel) {
    	
    	sessionFactory.getCurrentSession().update(pesel);
    }
}
