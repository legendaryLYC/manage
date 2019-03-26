package com.xiongantaoli.background.mapper;

import java.util.List;

import com.xiongantaoli.background.entity.Materiel;

public interface MaterielMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByCode(Integer code);

    int insert(Materiel record);

    int insertSelective(Materiel record);

    Materiel selectByPrimaryKey(int code);
    
    List<Materiel> select(String name);
    
    List<Materiel> selectByName(String name);
    
    Materiel selectLast();
    
    Long getCount();

    int updateByPrimaryKeySelective(Materiel record);

    int updateByPrimaryKey(Materiel record);
    
    int updateByCode(Materiel record);

}