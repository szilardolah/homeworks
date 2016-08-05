package com.szilardolah.jpa.main;


import com.szilardolah.jpa.bean.person.Address;
import com.szilardolah.jpa.bean.person.Person;
import com.szilardolah.jpa.bean.vehicle.Car;
import com.szilardolah.jpa.enums.GearShift;
import com.szilardolah.jpa.enums.Sex;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class Main {
    
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    
    public void initEntityManager() {
        emf  = Persistence.createEntityManagerFactory("homeworkJPAU");
        em = emf.createEntityManager();
    }
    
    public List<Person> initPersonList() {
        List<Person> persons = new ArrayList<>();

        Person person = new Person("Szilard Olah", Sex.MALE, new Date(763772400000L));
        Collection<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Ferenc Mora", "Veszprem", "Veszprem", "8200", "Hungary"));
        addresses.add(new Address("Alba", "Miskolc", "BAZ", "3531", "Hungary"));
        person.setAddresses(addresses);

        persons.add(person);

        person = new Person("Bernadett Tuskes", Sex.FEMALE, new Date(833772400000L));
        addresses.add(new Address("Tatorjan", "Veszprem", "Veszprem", "8200", "Hungary"));
        addresses.add(new Address("Futo", "Debrecen", "Hajdu-Bihar", "4033", "Hungary"));
        person.setAddresses(addresses);

        persons.add(person);

        return persons;    
    }
    
    public List<Car> initCarList() {
        List<Car> cars = new ArrayList<>();

        Car car = new Car(GearShift.AUTO, new Date(), 3, Color.yellow);
        cars.add(car);

        car = new Car(GearShift.MANUAL, new Date(), 4, Color.BLACK);
        cars.add(car);

        return cars;    
    }
    
    public void persistList(List<Person> persons, List<Car> cars) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        persons.stream().forEach(em::persist);
        cars.stream().forEach(em::persist);
        et.commit();
    }
    
    public void testQuery(String name, Class<?> clazz) {
        List<?> test = em.createNamedQuery(name, clazz).getResultList();
        for (int i=0; i<test.size(); i++) {
            LOG.log(Level.INFO, test.get(i).toString());
        }
    }
    
    public void testQuery(String name, Class<?> clazz, Integer top) {
        List<?> test = em.createNamedQuery(name, clazz).setMaxResults(top).getResultList();
        for (int i=0; i<test.size(); i++) {
            LOG.log(Level.INFO, test.get(i).toString());
        }
    }
    
    public void testQuery(String name, Class<?> clazz, String parameterName, Object parameter) {
        List<?> test = em.createNamedQuery(name, clazz)
                 .setParameter(parameterName, parameter)
                 .getResultList();
        for (int i=0; i<test.size(); i++) {
            LOG.log(Level.INFO, test.get(i).toString());
         }
    }
    
    public void finalizeDBOperations() {
        em.close();
        emf.close();
    }

    public static void main(String[] args) {
        Main m = new Main(); 
        m.initEntityManager();        
        List<Person> persons = m.initPersonList();
        List<Car> cars = m.initCarList();
        m.persistList(persons, cars);
        
        m.testQuery("Person.ListPersons", Person.class);      
        m.testQuery("Person.listMales", Person.class);
        m.testQuery("Person.listFemales", Person.class);
        m.testQuery("Person.getPerson", Person.class, "paramName", "Bernadett Tuskes");
        m.testQuery("Car.listCarsByGearShift", Car.class, "paramGearShift", GearShift.MANUAL);
        m.testQuery("Car.getLatestCar", Car.class, 1);
               
        m.finalizeDBOperations();        
    }
}
