package DAO;

import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    transaction.commit();
    session.close();
  //  return dailyReport;
    return null;
}

    //Удалить все отчёты
    public void deleteAllDailyReportsDAO() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM DailyReport");
        transaction.commit();
        session.close();
    }

    //добавить отчёт за сегодня !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void addTodayDailyReportDAO(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }


}
