package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderItem;
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
        Object userIdObj = request.getSession().getAttribute("userId");
        
        if (userIdObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = Integer.parseInt(userIdObj.toString());

        try {
            switch (action) {
                case "viewOrders":
                    List<Order> orders = orderService.getOrdersByUserId(userId);
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("orders.jsp").forward(request, response);
                    break;
                case "filterOrders":
                    Date fromDate = Date.valueOf(request.getParameter("fromDate"));
                    Date toDate = Date.valueOf(request.getParameter("toDate"));
                    List<Order> filteredOrders = orderService.getOrdersByDateRange(userId, fromDate, toDate);
                    request.setAttribute("orders", filteredOrders);
                    request.getRequestDispatcher("orders.jsp").forward(request, response);
                    break;
                case "cancelOrder":
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    boolean canceled = orderService.cancelOrder(orderId);
                    if (canceled) {
                        response.sendRedirect("OrderServlet?action=viewOrders&message=cancelSuccess");
                    } else {
                        response.sendRedirect("OrderServlet?action=viewOrders&message=cancelFailed");
                    }
                    break;
                case "reorder":
                    int reorderId = Integer.parseInt(request.getParameter("orderId"));
                    boolean reordered = orderService.reorder(reorderId);
                    if (reordered) {
                        response.sendRedirect("OrderServlet?action=viewOrders&message=reorderSuccess");
                    } else {
                        response.sendRedirect("OrderServlet?action=viewOrders&message=reorderFailed");
                    }
                    break;
                case "viewOrderDetails":
                    int detailOrderId = Integer.parseInt(request.getParameter("orderId"));
                    List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(detailOrderId);
                    request.setAttribute("orderItems", orderItems);
                    request.getRequestDispatcher("orderDetails.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
