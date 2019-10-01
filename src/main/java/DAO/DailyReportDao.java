package DAO;

import model.Car;
import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.DailyReportService;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    //получить все отчёты
    public List<DailyReport> getAllDailyReportsDAO() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

//получить отчёт за сегодня !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public DailyReport getTodayDailyReportDAO() {
    Transaction transaction = session.beginTransaction();
 //   DailyReport dailyReport = session.createQuery("FROM DailyReport");

   //запрос последний объект?????!!!!!!!!!!!!!!!!!!
    //RE-FACTOR!!!!!!!!!!!!!!!!!!!!!!! - сначала сделал через LIST
 //   List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
 //   DailyReport todayDailyReport = dailyReports.get(dailyReports.size()-1);// берём последний из списка

    Query query = session.createQuery("FROM DailyReport order by id DESC");
    query.setMaxResults(1);
    DailyReport todayDailyReport = (DailyReport) query.uniqueResult();

    transaction.commit();
    session.close();
    return todayDailyReport;

}

    //Удалить все отчёты
    public void deleteAllDailyReportsDAO() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }

    //добавить отчёт за сегодня !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // новый пустой!! репорт по новому дню
    public void addTodayDailyReportDAO(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }


    //добавляем данные по проданной машине в отчёт - а вот не надо было добавлять!!!!!
    public void updateTodayDailyReportDAO(Car soldCar) {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();

        if (dailyReports.size() < 1) {
            session.save(new DailyReport(soldCar.getPrice(), 1L));

        } else {
            DailyReport todayDailyReport = dailyReports.get(dailyReports.size() - 1);// берём последний из списка

            long updateEarnings = todayDailyReport.getEarnings() + soldCar.getPrice();
            long updateSoldCars = todayDailyReport.getSoldCars() + 1;

            todayDailyReport.setEarnings(updateEarnings);
            todayDailyReport.setSoldCars(updateSoldCars);

            session.update(todayDailyReport);
        }

        transaction.commit();
        session.close();
    }


}
