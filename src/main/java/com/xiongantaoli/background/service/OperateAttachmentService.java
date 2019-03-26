package com.xiongantaoli.background.service;

import java.util.List;

import com.xiongantaoli.background.entity.ProductAttachment;


public interface OperateAttachmentService {
	
	public List<ProductAttachment> selectattachment();
	
	public List<ProductAttachment> selectattachment(String name);

	public boolean insertattachment(int code, String name);

	public boolean deletebyid(int id);

	public boolean updatebyid(ProductAttachment attachment);

	public ProductAttachment selectByPrimaryKey(Long id);

	public ProductAttachment selectattachmentbyname(String name,int code);
	
	
}
