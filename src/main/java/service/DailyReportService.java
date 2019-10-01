package service;

import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;
import java.util.function.ObjLongConsumer;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private long soldCarCounter = 0L;
    private long earningsCounter = 0L;

    //увеличение счётчиков после продажи
    void updateCounterAfterSale(long price) {
        soldCarCounter = soldCarCounter + 1;
        earningsCounter = earningsCounter + price;
    }

    //обнуление дневных счётчиков
    private void resetCounterAfterNewDay() {
        soldCarCounter = 0L;
        earningsCounter = 0L;
    }



    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }
//получить все отчёты
    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReportsDAO(); // переименовал в REPORT-S, а надо?
    }

//получить отчёт за сегодня. может быть нулевым
    public DailyReport getLastReport() {//getLastReport
        return new DailyReportDao(sessionFactory.openSession()).getTodayDailyReportDAO();
    }

    //удалить все отчёты
    public void deleteAllReports() {
         new DailyReportDao(sessionFactory.openSession()).deleteAllDailyReportsDAO();
    }


    //закрыть старый отчёт - создать новый
    public void createNewDailyReport() {
       DailyReport dailyReport = new DailyReport(earningsCounter, soldCarCounter);
     //создать отчёт с нулевыми кол-во авто и стоимостьпродажи
        new DailyReportDao(sessionFactory.openSession()).addTodayDailyReportDAO(dailyReport);
        resetCounterAfterNewDay();
    }

    public void updateLastDailyReport(Car car) {
        //создать отчёт с нулевыми кол-во авто и стоимостьпродажи
        //проверка на отсутствие отчётов

           new DailyReportDao(sessionFactory.openSession()).updateTodayDailyReportDAO(car);
    }



}
