package com.xiongantaoli.background.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.xiongantaoli.background.entity.ProductRepair;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.mapper.ProductRepairMapper;





/**
*  王喜壮
* 维修项目管理
*/
@Controller
@RequestMapping("/productRepair")
public class ProductRepairController {


	
	    private static final Logger logger = LoggerFactory.getLogger(DicBrandController.class);

	    @Autowired
	    private ProductRepairMapper productRepairMapper;
	   
	    /**
	     * 进入维修管理界面界面
	     *
	     * @return
	     */
//	    @PreAuthorize("hasAuthority('/user')")
	    @RequestMapping(value = { "", "/" })
	    public String index(Model model,
	                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
	                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
	                        @RequestParam(value = "name", required = false, defaultValue = "") String name) {
	    	ProductRepair productRepair=new ProductRepair();
			if (name != null && !"".equals(name.trim())) {
				productRepair.setName(name.trim());
				model.addAttribute("name", name.trim());
			}
			name=name.trim();
			PageHelper.startPage(pageNo, pageSize);
			List<ProductRepair> list = productRepairMapper.select(name);
	       
			try {	
				logger.info("============查询出materiels ： " + list.toString() + " ===========");
				PageInfo<ProductRepair> pageInfo = new PageInfo<>(list);
				if (pageNo > pageInfo.getPages()) {
					pageNo = pageInfo.getPages();
					PageHelper.startPage(pageNo, pageSize);
					list = productRepairMapper.select(name);
					pageInfo = new PageInfo<>(list);
				}
				model.addAttribute("pageInfo", pageInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "productRepair/productrepair";
	    }
	        
	    /**
		 * 添加维修类别
		 * 
		 * @param model
		 * @param resource
		 * @return
		 */
		// 进入添加页面
		@RequestMapping(value = "/add")
		public String addRoleBefore(Model model, @ModelAttribute(value = "productRepair") SysUser productRepair) {
			return "/productRepair/productRepair-add";
		}
		// 添加维修类别
		@ResponseBody
		@RequestMapping(value = "/insert")
		public String insertbrand(Model model,
				@RequestParam(value = "code", required = false, defaultValue="") Integer code,
				@RequestParam(value = "productRepairName", required = false, defaultValue="") String productRepairName,
				@RequestParam(value = "price", required = false, defaultValue="") BigDecimal price){
			ProductRepair  productRepairCode = productRepairMapper.selectByCode(Long.valueOf(code));
			if( productRepairCode != null) {
				return "2";
			}else {
			ProductRepair productRepair = new ProductRepair();
			productRepair.setCode(code);
			productRepair.setName(productRepairName);
			productRepair.setPrice(price);
				boolean flag=productRepairMapper.insertproductRepair(productRepair);
				if(flag==true) {
					return "1"; // 插入成功
			}
			}
			return "0";
		}
		 /**
		 * 修改维修类别
		 * 
		 * @param model
		 * @param resource
		 * @return
		 */
		// 进入修改页面
		@RequestMapping(value = "/edit/{id}")
		public String updatebyidBefore(Model model, @ModelAttribute(value = "productRepair") ProductRepair productRepair,
				@PathVariable("id") Long id) {
			productRepair = productRepairMapper.selectByPrimaryKey(id);
			model.addAttribute("productRepair", productRepair);
			return "/productRepair/productRepair-edit";
		}
		// 修改维修类别
		@ResponseBody
		@RequestMapping(value = "/edit")
		public String updatebyid(Model model, @ModelAttribute(value = "productRepair") ProductRepair productRepair,HttpServletRequest request) {
			logger.debug("调用productRepair/edit");
//			ProductRepair productRepairCode = productRepairMapper.selectByCode(Long.valueOf(productRepair.getCode()));
//			boolean flag=false;
//			if(productRepair != null){
//				try {
//					flag=productRepairMapper.updateByid(productRepair);
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
//				return "1";
//			}
//			return "0";
//		}
			Long id=productRepair.getId();
			ProductRepair result=new ProductRepair();
			try {
				productRepairMapper.deletebyid(id);
				result.setCode(Integer.parseInt(request.getParameter("code1")));
				result.setId(productRepair.getId());
				result.setName(request.getParameter("name1"));
				 BigDecimal bd=new BigDecimal(request.getParameter("price1"));
				result.setPrice(bd);
				ProductRepair productRepair2=productRepairMapper.selectByCode(Long.valueOf(productRepair.getCode()));
				if(productRepair2==null) {
					productRepairMapper.insert(productRepair);
					return "1";
				}
				else {
					productRepairMapper.insert(result); //此处由于业务需求执行的是insert操作
					return "2";
				}
			}
			catch (Exception e) {
				productRepairMapper.updateByid(result);
				return "2";
			}
			}
		
		// 单个删除
		@RequestMapping("/delete/{id}")
		@ResponseBody
		public String deletebrand(@ModelAttribute ProductRepair productRepair){
			logger.debug("调用productRepair/delete");
			String result="0";
			boolean flag=false;
			try {
				
				flag=productRepairMapper.deletebyid(productRepair.getId());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			if(flag=true) {
				result="1";
			}
			return result;
		}
		
		// 多选删除
		@ResponseBody
		@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
		public Integer deleteAll(@RequestBody Long[] ids) {
			try {
				for (long id : ids) {
					productRepairMapper.deletebyid(id);
				}
				return 1;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return 0;
		}
	}
