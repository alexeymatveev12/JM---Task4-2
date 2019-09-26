package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }
//получить данные всех авто
public List<Car> getAllCars() {
    return new CarDao(sessionFactory.openSession()).
            getAllCarsDAO();
}
// удалить все авто
    public void deleteAllCars() {
        new CarDao(sessionFactory.openSession()).
                deleteAllCarsDAO();
    }
/*
    public boolean addNewCar(Car car) {
        if (
                new CarDao(sessionFactory.openSession()).addNewCarDAO(car)) { // добавить проверку меньше 10 бренд
            return true;
        } else{
            return false;
        }
    }
*/
    public void sellCar(Car car) {

//добавить авто в отчёт!!!!!!!!!!!!!!!!!!!!!!!!!!! ЦЕНА + кол-во!!!
        // удалить авто из базы


    }


}
