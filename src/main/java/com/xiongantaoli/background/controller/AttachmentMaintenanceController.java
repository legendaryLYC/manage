package com.xiongantaoli.background.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.ProductAttachment;
import com.xiongantaoli.background.service.OperateAttachmentService;

/**
 * 附件维护类
 * 
 * @author 李永成
 *
 */

@Controller
@RequestMapping("/attachment") 	
public class AttachmentMaintenanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttachmentMaintenanceController.class);
	
	@Autowired
	private OperateAttachmentService operateattachment; //操作附件的Service类
	
	//添加附件的信息
	@RequestMapping(value = "/addattachment")
	public String addRoleBefore(Model model) {
		return "maintenance-operate/add-attachment";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertattachment")
	public String insertattachment(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "name", required = false, defaultValue="") String name){
		int code1 = Integer.parseInt(code);
		try {
			ProductAttachment attachment=operateattachment.selectattachmentbyname(name,code1);
			if(attachment!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag=operateattachment.insertattachment(code1,name);
				if(flag==true) {
					return "1"; //插入成功
				}
			}
		}
			
		catch (Exception e) {
			return "0"; // 根据两个字段查出了两条数据。
		}
		return "0";
	}
	
	//通过附件名查询附件
	@RequestMapping("/selectattachment")
	public String selectattachment(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue="") String name) {
		List<ProductAttachment> attachmentlist=null;
		if (name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(name != null && !"".equals(name.trim())) {
			model.addAttribute("name", name.trim());
			name=name.trim();
			attachmentlist = operateattachment.selectattachment(name);
		}
		else {
			attachmentlist = operateattachment.selectattachment();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<ProductAttachment> pageInfo = new PageInfo<ProductAttachment>(attachmentlist);
		model.addAttribute("pageInfo", pageInfo);
		return "maintenance-operate/show-attachment";
	}
	
	//根据附件名删除字典中对应的附件
	@RequestMapping("/deleteattachment/{id}")
	@ResponseBody
	public String deleteattachment(@ModelAttribute ProductAttachment attachment){
		logger.debug("调用attachment/deleteattachment");
		String result="0";
		boolean flag=false;
		try {
			
			flag=operateattachment.deletebyid(attachment.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	
	//删除批量选中的附件
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				operateattachment.deletebyid(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//根据附件名更改附件的名称和编号信息
	@RequestMapping(value = "/updatebyid/{id}")
	public String updatebyidBefore(Model model, @ModelAttribute(value = "attachment") ProductAttachment attachment,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		attachment = operateattachment.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("attachment", attachment);
		return "maintenance-operate/editor-attachment";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updatebyid(Model model, @ModelAttribute(value = "attachment") ProductAttachment attachment,HttpServletRequest request) {
		logger.debug("调用attachment/updatebyid");
		int id=attachment.getId().intValue();
		ProductAttachment result=new ProductAttachment();
		try {
			operateattachment.deletebyid(id);
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(attachment.getId());
			result.setName(request.getParameter("name1"));
			ProductAttachment attachment2=operateattachment.selectattachmentbyname(attachment.getName(),attachment.getCode());
			if(attachment2==null) {
				operateattachment.updatebyid(attachment);
				return "1";
			}
			else {
				operateattachment.updatebyid(result); //此处由于业务需求执行的是insert操作
				return "2";
			}
		}
		catch (Exception e) {
			operateattachment.updatebyid(result);
			return "2";
		}
	}
}
