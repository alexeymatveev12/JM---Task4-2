package service;

import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

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
       DailyReport dailyReport = new DailyReport();
     //создать отчёт с нулевыми кол-во авто и стоимостьпродажи
        new DailyReportDao(sessionFactory.openSession()).addTodayDailyReportDAO(dailyReport);
    }

    public void updateLastDailyReport(Car car) {
        //создать отчёт с нулевыми кол-во авто и стоимостьпродажи
           new DailyReportDao(sessionFactory.openSession()).updateTodayDailyReportDAO(car);
    }



}
