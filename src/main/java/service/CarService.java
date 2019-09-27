package service;

import DAO.CarDao;
import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.Collections;
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
// Проверка - есть ли авто в базе
    public Car checkCarInStock(String brand, String model, String licensePlate) {
        return new CarDao(sessionFactory.openSession()).checkCarInStockDAO(brand, model, licensePlate);
    }

    public boolean addNewCar(String brand, String model, String licensePlate, long price) {

        Car newCar = new Car(brand, model, licensePlate, price);
//    если уже есть авто на складе
// добавить проверку меньше 10 бренд
        if (checkCarInStock(brand, model, licensePlate) != newCar) {// а так ли сравнение??????
            if (new CarDao(sessionFactory.openSession()).checkBrandQuantityDAO(brand)) {
                new CarDao(sessionFactory.openSession()).addNewCarDAO(newCar);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    public void sellCar(String brand, String model, String licensePlate) {

        Car soldCar = new Car(brand, model, licensePlate);
//найти авто в базе?????????????????????????
        // удалить авто из базы
        //добавить авто в отчёт!!!!!!!!!!!!!!!!!!!!!!!!!!! ЦЕНА + кол-во!!!
        if (checkCarInStock(brand, model, licensePlate) == soldCar) {// а так ли сравнение??????

            new CarDao(sessionFactory.openSession()).deleteSoldCarDAO(soldCar);
            new DailyReportDao(sessionFactory.openSession()).updateTodayDailyReportDAO(soldCar);
        } else {
        }
    }

}




