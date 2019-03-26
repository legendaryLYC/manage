package com.xiongantaoli.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.mapper.ProductAttachmentMapper;
import com.xiongantaoli.background.service.OperateAttachmentService;


@Service("operateattachment")
public class OperateAttachmentServiceImpl implements OperateAttachmentService{

	@Autowired 
	ProductAttachmentMapper sysattachmentmapper;
	
	@Override
	public List<ProductAttachment> selectattachment() {
		List<ProductAttachment> attachmentlist=null;
		try {
			attachmentlist=sysattachmentmapper.selectallattachment();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return attachmentlist;
	}

	@Override
	public List<ProductAttachment> selectattachment(String name) {
		List<ProductAttachment> attachmentlist=null;
		try {
			attachmentlist=sysattachmentmapper.selectattachment(name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return attachmentlist;
	}

	@Override
	public boolean deletebyid(int id) {
		boolean flag=false;
		try {
		flag=sysattachmentmapper.deletebyid(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertattachment(int code, String name) {
		boolean flag=false;
		try {
		flag=sysattachmentmapper.insertattachment(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updatebyid(ProductAttachment attachment) {
		boolean flag=false;
		try {
		flag=sysattachmentmapper.updatebyid(attachment);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ProductAttachment selectByPrimaryKey(Long id) {
		ProductAttachment attachment=sysattachmentmapper.selectByPrimaryKey(id);
		return attachment;
	}

	@Override
	public ProductAttachment selectattachmentbyname(String name,int code) {
		ProductAttachment attachment=sysattachmentmapper.selectattachmentbyname(name,code);
		return attachment;
	}
}
