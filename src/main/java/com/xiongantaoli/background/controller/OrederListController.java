package com.xiongantaoli.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiongantaoli.background.entity.*;
import com.xiongantaoli.background.form.*;
import com.xiongantaoli.background.mapper.*;
import com.xiongantaoli.background.model.ResultData;
import com.xiongantaoli.background.service.GetDictionaryListService;
import com.xiongantaoli.background.service.OrderNewService;
import com.xiongantaoli.background.service.PurchaseService;
import com.xiongantaoli.background.util.StringToDate;
import com.xiongantaoli.background.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * 订单管理类，管理员能对每笔订单进行操作
 * @author 周天
 *
 */

@Controller
@RequestMapping("/orderlist")
public class OrederListController {

    @Autowired
    OrderNewService orderNewService;
    @Autowired
    private SysUserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrederListController.class);

    /**
     * 进入订单记录列表页面,
     * 包括筛选, 分页
     *
     * @return
     */
    @RequestMapping(value = {"", "/"})
    public String index(Model model, NewOrderForm newOrderForm, @RequestParam(value = "beginTime", required = false)String beginTime,
                        @RequestParam(value = "endTime", required = false)String endTime,
                        @RequestParam(value = "purchaseTimeString", required = false) String purchaseTimeString,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
/*            //日期字符转化为Date型并set到order对象
            java.util.Date date = StringToDate.toUtilDate(purchaseTimeString);
            orderNew.setPurchaseTime(date);*/
            System.out.println(newOrderForm.toString());
            if(beginTime != null){
                newOrderForm.setBeginTime(beginTime);
            }
            if(endTime != null){
                newOrderForm.setEndTime(endTime);
            }
        //分页
        PageHelper.startPage(pageNo, pageSize);
        List<OrderNew> result = orderNewService.selectOrder(newOrderForm);


        PageInfo<OrderNew> pageInfo = new PageInfo<>(result);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            result = orderNewService.selectOrder(newOrderForm);
            PageHelper.startPage(pageNo, pageSize);
            pageInfo = new PageInfo<OrderNew>(result);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("orderNew", newOrderForm);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);

        return "orderlist/list";
    }

    /**
     * 进入手机信息更新操作,更新单一商品
     * 时间:2019/1/10
     * 最后修改时间:2019/1/10
     * 注意事项:无
     *
     * @author 周天
     */
    @RequestMapping(value = {"/updateNewOrder"}, produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public String updateNewOrder(Model model,OrderNew orderNew,
                                 @RequestParam(value = "beginTime", required = false)String beginTime,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                 @RequestParam(value = "endTime", required = false)String endTime,
                                 @RequestParam(value = "purchaseTime", required = false) String purchaseTime) {
        /*
         * 打印入参日志
         */
        logger.info("调用者为：purchase/check-in，传过来的参数为order=" + orderNew + "##purchaseTimeString=" + purchaseTime);

        //String日期转换为Date
        Date date = StringToDate.toSqlDate(purchaseTime);
        orderNew.setDate(date);

        // 计算利润，利润：出售价格-（收购价格+收购运费+维修价格+出售运费）  （允许为负数）
        BigDecimal profit = new BigDecimal("0");
        if(orderNew.getSellPrice() != null){
            profit = profit.add(orderNew.getSellPrice());
        }
        if(orderNew.getPurchasePrice() != null){
            profit = profit.subtract(orderNew.getPurchasePrice());
        }
        if(orderNew.getPurchaseExpress() != null){
            profit = profit.subtract(orderNew.getPurchaseExpress());
        }
        if(orderNew.getRepairPrice() != null){
            profit =profit.subtract(orderNew.getRepairPrice());
        }
        if(orderNew.getSellExpress() != null){
            profit =  profit.subtract(orderNew.getSellExpress());
        }
        orderNew.setProfit(profit);

        int flag = -1;
        ResultData resultData = new ResultData();
        flag = orderNewService.updateOrder(orderNew);
        if (flag <= 0) {
            resultData.setCode("999999");
            resultData.setMessage("更新失败！");
        } else {
            resultData.setCode("000000");
            resultData.setMessage("更新成功！");
        }
        // 筛选条件为空
        NewOrderForm newOrderForm = new NewOrderForm();
        //分页
        PageHelper.startPage(pageNo, pageSize);
        List<OrderNew> result = orderNewService.selectOrder(newOrderForm);


        PageInfo<OrderNew> pageInfo = new PageInfo<>(result);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            result = orderNewService.selectOrder(newOrderForm);
            PageHelper.startPage(pageNo, pageSize);
            pageInfo = new PageInfo<OrderNew>(result);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("orderNew", newOrderForm);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        return "orderlist/list";
    }

    /**
     * 进入手机删除操作,删除单一或选定的一些的商品
     * 时间:2019/1/10
     * 最后修改时间:2019/1/13
     * 注意事项:无
     *
     * @author 周天
     */
    @ResponseBody
    @RequestMapping(value = {"/deleteNewOrder"}, produces = "application/json", consumes = "application/json")
    public ResultData deleteNewOrder(@RequestBody Long[] id) {
        ResultData resultData = new ResultData();
        int flag = -1;
        for (int i = 0; i < id.length; i++) { // 循环遍历传过来需要删除的id数组
            flag = orderNewService.deleteNewOrder(id[i]);
            if (flag <= 0) {
                resultData.setCode("999999");
                resultData.setMessage("删除失败！");
            } else {
                resultData.setCode("000000");
                resultData.setMessage("删除成功！");
            }
        }
        return resultData;
    }

    /**
     * 进入新订单增加操作,增加订单
     * 时间:2019/1/10
     * 最后修改时间:2019/1/13
     * 注意事项:无
     *
     * @author 周天
     */
    @ResponseBody
    @RequestMapping(value = {"/addNewOrder"})
    public ResultData addNewOrder(OrderNew orderNew, @RequestParam(value = "purchaseTime", required = false) String purchaseTime) {

        /*
         * 打印入参日志
         */
        logger.info("调用者为：purchase/check-in，传过来的参数为order=" + orderNew + "##purchaseTimeString=" + purchaseTime);

        // String日期转换为Date
        Date date = StringToDate.toSqlDate(purchaseTime);
        orderNew.setDate(date);

        // 计算利润，利润：出售价格-（收购价格+收购运费+维修价格+出售运费）  （允许为负数）
        BigDecimal profit = new BigDecimal("0");
        if(orderNew.getSellPrice() != null){
            profit = profit.add(orderNew.getSellPrice());
        }
        if(orderNew.getPurchasePrice() != null){
            profit = profit.subtract(orderNew.getPurchasePrice());
        }
        if(orderNew.getPurchaseExpress() != null){
            profit = profit.subtract(orderNew.getPurchaseExpress());
        }
        if(orderNew.getRepairPrice() != null){
            profit =profit.subtract(orderNew.getRepairPrice());
        }
        if(orderNew.getSellExpress() != null){
            profit =  profit.subtract(orderNew.getSellExpress());
        }
        orderNew.setProfit(profit);

        // 获取用户username, 用户username存到对象的user
        String loginUserName = Utility.getCurrentUsername();
        orderNew.setUser(loginUserName);
        ResultData resultData = new ResultData();
        int flag = -1;
        flag = orderNewService.addNewOrder(orderNew);
        if (flag <= 0) {
            resultData.setCode("999999");
            resultData.setMessage("增加失败！");
        } else {
            resultData.setCode("000000");
            resultData.setMessage("增加成功！");
        }
        return resultData;
    }

    /**
     * 进入订单列表EXCEL导出操作,导出默认全部或选定的一些的订单
     * 时间:2019/2/26
     * 最后修改时间:2019/2/26
     * 注意事项:无
     *
     * @author 周天
     */
    @ResponseBody
    @RequestMapping(value = {"/downloadPartExcel"}, produces = "application/json", consumes = "application/json")
    public Map<String,String> downloadPartExcel(@RequestBody Long[] ids) {
        for(Long id : ids) {
            System.out.println(id);
        }
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(calendar.getTime()));
        String prefix = df.format(calendar.getTime()).toString();
        boolean flag = false;
        Map<String,String> result = new HashMap<String,String>();
        try {
            flag = true;
            File file =orderNewService.downloadPartExcel(ids);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            result.put("file", Base64Utils.encodeToString(buffer));
            result.put("filename", prefix+"交易数据.xlsx");
        }catch (Exception e){
            flag = false;
            logger.info("导出EXCEL失败");
            e.printStackTrace();
        }
        return result;
    }
}
