package DAO;

import model.Car;

import model.DailyReport;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    /*
    Покупатели могут запросить список имеющихся  машин по url `/customer`GET запросом
      и купить с помощью POST запроса на тот же url, передав параметры марки машины, названия и госномера.

     */

    // 1 посмотреть все авто из базы
    // 2 добавить авто в базу
    //3 удалить купленное авто из базы
    //4 удалить все авто из базы

    public List<Car> getAllCarsDAO() {
        Transaction transaction = session.beginTransaction();
        List<Car> allCars = (List<Car>) session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return allCars;
    }

      public void addNewCarDAO(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }
    public void deleteSoldCarDAO(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public void deleteAllCarsDAO() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();


    }

    public int checkBrandQuantityDAO(String brand) {

        Criteria criteria = session.createCriteria(Car.class);

         //   int car = session.createQuery("FROM car").list().size();
        //   int carQuantity = car.size();

        int brandQuantity = criteria.add(Restrictions.eq("brand", brand)).list().size();// что сделать?

        session.close();
        return brandQuantity;
    }

    public Car checkCarInStockDAO(String brand, String model, String licensePlate) {

        Car carInStock   = (Car) session.createCriteria(Car.class)
        .add(Restrictions.eq("brand", brand))
        .add(Restrictions.eq("model", model))
        .add(Restrictions.eq("licensePlate", licensePlate)).uniqueResult();


        session.close();
        return carInStock;



//добавить авто в отчёт!!!!!!!!!!!!!!!!!!!!!!!!!!! ЦЕНА + кол-во!!!

        // удалить авто из базы
        //найти авто в базе?????????????????????????
        //       new CarDao(sessionFactory.openSession(soldCar)).
        //               deleteSoldCarDAO(car);

/*
        Session session = HibernateUtil.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);
        cr.select(root);

        Query<Item> query = session.createQuery(cr);
        List<Item> results = query.getResultList();

        4.3.10.Final
        5.4.5.Final
 */
    }


}
