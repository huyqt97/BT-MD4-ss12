package ra.customermanager.controller;

import ra.customermanager.model.Customer;
import ra.customermanager.service.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {
 protected CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        customerService.save(new Customer(1,"Nguyen Van A","a@gmail.com","Ha Noi"));
        customerService.save(new Customer(2,"Nguyen Van B","b@gmail.com","Lao Cai"));
        customerService.save(new Customer(3,"Nguyen Van C","c@gmail.com","Ha Noi"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if(action!=null){
        switch(action){
            case "GETALL":
                break;
            case "DELETE":
                int idDel = Integer.parseInt(request.getParameter("id"));
                customerService.deleteById(idDel);
                break;
            case "EDIT":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("customer",customerService.findbyId(idEdit));
                    request.getRequestDispatcher("/WEB-INF/editCustomer.jsp").forward(request,response);

                break;
            case "ADD":
                request.getRequestDispatcher("/WEB-INF/newCustomer.jsp").forward(request,response);
                break;
            case "SEARCH":
                String searchName = request.getParameter("search");
                String sort = request.getParameter("sort");
                String By = request.getParameter("By");
                List<Customer> listSearch = searchAndSort(searchName, sort, By);
                request.setAttribute("searchName", searchName);
                request.setAttribute("sort", sort);
                request.setAttribute("By", By);
                showListCustomers(listSearch, request, response);
                break;

        }
        showListCustomers(customerService.findAll(),request, response);
    }
    }
    protected List<Customer> searchAndSort(String name, String sort, String by){
        List<Customer> listSearch = new ArrayList<>();
                for (Customer c: customerService.findAll()
                ) {
                    if(c.getName().toLowerCase().contains(name.toLowerCase())){
                        listSearch.add(c);
                    }
                }
      // sap xep
        switch(sort){
            case "name":
                if(by.equalsIgnoreCase("ASC")){
                    listSearch.sort(Comparator.comparing(Customer::getName));
                }else {
                    listSearch.sort(Comparator.comparing(Customer::getName).reversed());
                }
                break;
            case "email":
                if(by.equalsIgnoreCase("ASC")){
                    listSearch.sort(Comparator.comparing(Customer::getEmail));
                }else {
                    listSearch.sort(Comparator.comparing(Customer::getEmail).reversed());
                }
                break;
                case "address":
                    if(by.equalsIgnoreCase("ASC")){
                        listSearch.sort(Comparator.comparing(Customer::getAddress));
                    }else {
                        listSearch.sort(Comparator.comparing(Customer::getAddress).reversed());
                    }
                break;
        }
                return listSearch;
    }
  protected void showListCustomers(List<Customer> list,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("customers",list);
        request.getRequestDispatcher("/WEB-INF/listCustomer.jsp").forward(request,response);
  }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String action = request.getParameter("action");
       if(action!= null){
           switch (action){
               case "UPDATE":
                   int id = Integer.parseInt(request.getParameter("id"));
                   String name = request.getParameter("name");
                   String email = request.getParameter("email");
                   String address = request.getParameter("address");
               customerService.save(new Customer(id,name,email,address));
                   break;
               case "ADD":
                   int idNew = customerService.getNewId();
                   String newname = request.getParameter("name");
                   String newemail = request.getParameter("email");
                   String newaddress = request.getParameter("address");

                   customerService.save(new Customer(idNew,newname,newemail,newaddress));
                   break;
           }
           showListCustomers(customerService.findAll(),request, response);}
    }


}