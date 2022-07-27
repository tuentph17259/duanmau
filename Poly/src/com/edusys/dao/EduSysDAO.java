/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import java.util.List;
import javax.swing.text.html.parser.Entity;

/**
 *
 * @author admin
 */
public abstract class EduSysDAO<EntityType, KeyType> {
    public abstract void insert(EntityType entity);
    public abstract void update(EntityType entity);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectBYId(KeyType id);
    abstract protected List<EntityType> selectBySql(String sql,Object...args);
}
