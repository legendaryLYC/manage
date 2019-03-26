package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.*;
import com.xiongantaoli.background.mapper.*;
import com.xiongantaoli.background.service.GetDictionaryListService;

/**
 * 该service用于获取字典表list
 * @author 孟晓冬
 */
@Service
public class GetDictionaryListServiceImpl implements GetDictionaryListService {

	@Autowired
	private ProductStateMapper productStateMapper;
	@Autowired
	private ProductBrandMapper productBrandMapper;
	@Autowired
	private DicMethodMapper dicMethodMapper;
	@Autowired
	private ProductModelMapper productModelMapper;
	@Autowired
	private ProductColorMapper productColorMapper;
	@Autowired
	private ProductGuaranteeMapper productGuaranteeMapper;
	@Autowired
	private ProductApperanceMapper productApperanceMapper;
	@Autowired
	private ProductAttachmentMapper productAttachmentMapper;
	@Autowired
	private ProductRepairMapper productRepairMapper; //维修类型数据操作Mapper
	@Autowired
	private MaterielMapper materielMapper; //物料类型操作Mapper
	
	@Override
	public List<ProductState> getProductStates() {
		return productStateMapper.selectAll();
	}
	@Override
	public List<ProductBrand> getProductBrands() {
		return productBrandMapper.selectAll();
	}
	@Override
	public List<ProductDicMethod> getDicMethods() {
		return dicMethodMapper.selectAll();
	}
	@Override
	public List<ProductModel> getProductModels() {
		return productModelMapper.selectAll();
	}
	@Override
	public List<ProductColor> getProductColors() {
		return productColorMapper.selectAll();
	}
	@Override
	public List<ProductGuarantee> getProductGuarantees() {
		return productGuaranteeMapper.selectAll();
	}
	@Override
	public List<ProductApperance> getProductApperances() {
		return productApperanceMapper.selectAll();
	}
	@Override
	public List<ProductAttachment> getProductAttachments() {
		return productAttachmentMapper.selectAll();
	}
	@Override
	public List<ProductRepair> getProductRepair() {
		// TODO Auto-generated method stub
		return productRepairMapper.selectAll();
	}
	@Override
	public List<Materiel> getMateriels() {
		// TODO Auto-generated method stub
		return materielMapper.select("");
	}
	
}
