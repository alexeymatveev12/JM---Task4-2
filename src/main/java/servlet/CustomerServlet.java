package servlet;

import com.google.gson.Gson;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars()); //getAllCars())??? метод был без С на конце
        resp.getWriter().println("println");
        resp.getWriter().println(json);

//        resp.getWriter().println("write"); // удалить?
//        resp.getWriter().write(json);      // удалить?
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        // покупатель покупает авто - посылает данные
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
 //       CarService.getInstance().sellCar(); // метод купить авто
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
