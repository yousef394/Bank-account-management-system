package com.bank_account_management_system.Repository;

import java.util.ArrayList;

public interface CRUD<T ,K> {

    public boolean add(T object);

    public ArrayList<T> getAll();

    public T find(K key);

    public boolean delete(K key);

    public boolean update(T key) ;

}
