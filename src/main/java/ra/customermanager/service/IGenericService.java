package ra.customermanager.service;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> findAll();
    void save(T t);
    T findbyId(E e);
    void deleteById(E e);


}
