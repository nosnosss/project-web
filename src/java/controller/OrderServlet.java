package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import service.OrderService;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Cải thiện xử lý null

        if (userId == null) {
            // Nếu userId không tồn tại, chuyển hướng đến trang đăng nhập hoặc thông báo lỗi
            response.sendRedirect("login.jsp");
            return;
        }

        switch (action) {
            case "viewOrders":
                List<Order> orders = orderService.getOrdersByUserId(userId);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
                break;
            case "filterOrders":
                try {
                    Date fromDate = Date.valueOf(request.getParameter("fromDate"));
                    Date toDate = Date.valueOf(request.getParameter("toDate"));
                    List<Order> filteredOrders = orderService.getOrdersByDateRange(userId, fromDate, toDate);
                    request.setAttribute("orders", filteredOrders);
                    request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
                } catch (IllegalArgumentException e) {
                    // Xử lý lỗi khi ngày không hợp lệ
                    request.setAttribute("error", "Ngày không hợp lệ");
                    request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
                }
                break;
            case "cancelOrder":
                try {
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    boolean canceled = orderService.cancelOrder(orderId);
                    request.setAttribute("cancelSuccess", canceled);
                } catch (NumberFormatException e) {
                    // Xử lý lỗi khi ID đơn hàng không hợp lệ
                    request.setAttribute("error", "ID đơn hàng không hợp lệ");
                }
                response.sendRedirect("OrderServlet?action=viewOrders");
                break;
            case "reorder":
                try {
                    int reorderId = Integer.parseInt(request.getParameter("orderId"));
                    boolean reordered = orderService.reorder(reorderId);
                    request.setAttribute("reorderSuccess", reordered);
                } catch (NumberFormatException e) {
                    // Xử lý lỗi khi ID đơn hàng không hợp lệ
                    request.setAttribute("error", "ID đơn hàng không hợp lệ");
                }
                response.sendRedirect("OrderServlet?action=viewOrders");
                break;
            default:
                // Nếu action không hợp lệ, có thể chuyển hướng đến trang lỗi hoặc thông báo lỗi
                response.sendRedirect("error.jsp");
                break;
        }
    }
}
