/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessObjects;

import domain.Product;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import utils.MultiMap;

/**
 *
 * @author Ryan Campion 2343075
 */
public class ProductCollectionsDAO implements ProductDAO {

    //Creating collections
    private static final Collection<Product> productsTS = new TreeSet<>();
    private static final Collection<String> categoriesTS = new TreeSet<>();

    //Collection for using id to map a student, used for getById method
    private static final Map<Integer, Product> productsIdTM = new TreeMap<>();

    //Collection for using major to map a list of students, used for getByMajor method
    private static final MultiMap<String, Product> productsCategoryMM = new MultiMap<>();

    @Override
    public void save(Product aProduct) {
        categoriesTS.add(aProduct.getCategory());
        productsTS.add(aProduct);
        productsIdTM.put(aProduct.getId(), aProduct);
        productsCategoryMM.put(aProduct.getCategory(), aProduct);
    }

    @Override
    public void delete(Product aProduct) {
        productsTS.remove(aProduct);
        productsIdTM.remove(aProduct.getId());
        productsCategoryMM.remove(aProduct.getCategory(), aProduct);
    }

    @Override
    public Collection<Product> getAll() {
        return productsTS;
    }

    @Override
    public Collection<String> getCategories() {
        return categoriesTS;
    }

    @Override
    public Product getById(Integer id) {
        return productsIdTM.get(id);
    }

    @Override
    public Collection<Product> getByCategory(String category) {
        return productsCategoryMM.get(category);
    }

}
