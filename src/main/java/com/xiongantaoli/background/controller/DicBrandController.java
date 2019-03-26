package com.xiongantaoli.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.ProductBrand;
import com.xiongantaoli.background.entity.SysUser;
import com.xiongantaoli.background.mapper.ProductBrandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * 品牌字典控制类
 */
@Controller
@RequestMapping("/dicBrand")
public class DicBrandController {
    private static final Logger logger = LoggerFactory.getLogger(DicBrandController.class);

    @Autowired
    private ProductBrandMapper productBrandMapper;


    /**
     * 进入管理员管理界面
     *
     * @return
     */
//    @PreAuthorize("hasAuthority('/user')")
    @RequestMapping
    public String index(Model model,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                        @RequestParam(value = "name", required = false, defaultValue = "") String name) {

        PageHelper.startPage(pageNo, pageSize);
        List<ProductBrand> list = productBrandMapper.selectAll();
        PageInfo<ProductBrand> pageInfo = new PageInfo<>(list);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            PageHelper.startPage(pageNo, pageSize);
            list = productBrandMapper.selectAll();
            pageInfo = new PageInfo<>(list);
        }
        model.addAttribute("pageInfo", pageInfo);

        return "dic/branch/list";
    }






}
