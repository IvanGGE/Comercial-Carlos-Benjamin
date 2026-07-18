package com.comercialcarlosbenjamin.modelo.dao;

import java.util.List;

/* 
Interface: especifica que metodos debe estar presentes
T : Se usa para definir un tipo genérico que se reemplazará cuando se instancie la clase o método.
*/

public interface CRUD_Interface<T> {
    void crear(T obj);
    T getById(Integer id);
    List<T>  listar(String valor);
    void actualizar(T obj);
    void eliminar(Integer id);
}
