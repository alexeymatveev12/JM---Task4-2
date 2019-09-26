package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //   super.doPost(req, resp);
        // покупатель покупает авто - посылает данные
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        long price = Long.parseLong(req.getParameter("price"));

        //создать авто и добавить в базу
        Car newCar = new Car(brand, model, licensePlate, price);
/*        if (CarService.getInstance().addNewCar(newCar)){ // метод добавить  авто на склад

        resp.setStatus(HttpServletResponse.SC_OK);}
        else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    */
    }
}
