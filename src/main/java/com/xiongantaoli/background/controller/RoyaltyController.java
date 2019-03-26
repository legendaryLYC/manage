package com.xiongantaoli.background.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiongantaoli.background.entity.RoyaltyModel;
import com.xiongantaoli.background.service.RoyaltyService;

/**
 * 用来处理提成显示
 * @author HRX
 *
 */
@Controller
@RequestMapping("/royalty")
public class RoyaltyController {
	
	@Autowired
	private RoyaltyService royaltyService;
	
	@RequestMapping("/caculate")
	public String caculate(Model model,@RequestParam(value = "beginTime", required = false)String beginTime,@RequestParam(value = "endTime", required = false)String endTime) {
		System.out.println(beginTime);
		RoyaltyModel royaltyModel = royaltyService.caculate(beginTime,endTime);
		model.addAttribute("royaltyModel",royaltyModel);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "statistics/statistics";
	}
}
