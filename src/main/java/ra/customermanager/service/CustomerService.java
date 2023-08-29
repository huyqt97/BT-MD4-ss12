package ra.customermanager.service;

import ra.customermanager.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer> {
 private List<Customer> customers;
 public CustomerService(){
     customers = new ArrayList<>();
 }


    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void save(Customer customer) {
if(findbyId(customer.getId())==null){
    customers.add(customer);
}else{
    customers.set(customers.indexOf(findbyId(customer.getId())),customer);
}
    }

    @Override
    public Customer findbyId(Integer integer) {
       for(Customer c: customers){
           if(c.getId()==integer){
               return c;
           }
       }
       return null;
    }

    @Override
    public void deleteById(Integer integer) {
customers.remove(findbyId(integer));
    }
    public int getNewId(){
        int maxId=0;
        for(Customer customer : customers){
            if(customer.getId()>maxId){
                maxId=customer.getId();
            }}
        return maxId+1;
    }
}