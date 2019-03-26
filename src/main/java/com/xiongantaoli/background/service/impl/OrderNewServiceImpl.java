package com.xiongantaoli.background.service.impl;

import com.xiongantaoli.background.entity.OrderNew;
import com.xiongantaoli.background.form.NewOrderForm;
import com.xiongantaoli.background.form.ProductInfoForm;
import com.xiongantaoli.background.mapper.OrderNewMapper;
import com.xiongantaoli.background.service.OrderNewService;
import com.xiongantaoli.background.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderNewServiceImpl implements OrderNewService {


    private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);


    @Autowired
    private OrderNewMapper orderNewMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<OrderNew> selectOrder(NewOrderForm newOrderForm) {
        List<OrderNew> orderAllList = null;
        orderAllList = orderNewMapper.selectOrder(newOrderForm);
        return orderAllList;
    }
    @Override
    public int updateOrder(OrderNew record){
        int flag = -1;
        flag = orderNewMapper.updateByPrimaryKeySelective(record);
        return flag;
    }
    @Override
    public int deleteNewOrder(Long id){
        int flag = -1;
        flag = orderNewMapper.deleteByPrimaryKey(id);
        return flag;
    }
    @Override
    public int addNewOrder(OrderNew record){
        int flag = -1;
        flag = orderNewMapper.insertSelective(record);
        return flag;
    }
    @Override
    public File downloadPartExcel(Long[] ids) {
        Workbook wb = null;
        OutputStream fileOut = null;
        File file = null;
        String path = request.getServletContext().getRealPath("/") + "download";
        File fileDir = new File(path);

        if(!fileDir.exists())//如果目录不存在，创建下载文件的根目录
        {
            fileDir.mkdir();
        }

        // 生成excel的版本
            wb = new XSSFWorkbook();
            file = new File(path + File.separator + "workbook.xlsx");

        CreationHelper creationHelper = wb.getCreationHelper();

        Sheet sheet = wb.createSheet("new sheet"); // 建excel中的一个表

        List<OrderNew> orderNewList = new ArrayList<OrderNew>();
        // 从数据库中查询出所有学生
        if(ids == null || ids.length == 0) {
            orderNewList = orderNewMapper.selcetAllOrder();
        }
        else {
            for(Long id : ids) {
                OrderNew orderNew = orderNewMapper.selectByPrimaryKey(id);
                orderNewList.add(orderNew);
            }
        }

        int rowCount = 1;

        Row rowTile = sheet.createRow(0);

        // 输入列名
        rowTile.createCell(0).setCellValue("订单编号");
        rowTile.createCell(1).setCellValue("日期");
        rowTile.createCell(2).setCellValue("型号");
        rowTile.createCell(3).setCellValue("颜色");
        rowTile.createCell(4).setCellValue("版本");
        rowTile.createCell(5).setCellValue("内存");
        rowTile.createCell(6).setCellValue("卖家账户");
        rowTile.createCell(7).setCellValue("快递单号");
        rowTile.createCell(8).setCellValue("收购价格");
        rowTile.createCell(9).setCellValue("收购运费");
        rowTile.createCell(10).setCellValue("维修价格");
        rowTile.createCell(11).setCellValue("出售价格");
        rowTile.createCell(12).setCellValue("出售运费");
        rowTile.createCell(13).setCellValue("利润");
        rowTile.createCell(14).setCellValue("买家账号");
        rowTile.createCell(15).setCellValue("售出快递单号");
        rowTile.createCell(16).setCellValue("交易状态");
        rowTile.createCell(17).setCellValue("备注");
        rowTile.createCell(18).setCellValue("登记员");

        for(OrderNew s : orderNewList) // 遍历查询出的学生
        {
            System.out.println(s.toString());
            Row row = sheet.createRow(rowCount); // 新建一行
            for(int j = 0;j < 18;j++)
            {
                switch (j) // 新建单元格并赋值
                {
                    case 0:
                        if(null != s.getNum()) {
                            row.createCell(j).setCellValue(s.getNum());
                        }
                        break;
                    case 1:
                        if(null !=s.getDate()){
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            row.createCell(j).setCellValue(df.format(s.getDate()).toString());
                        }
                        break;
                    case 2:
                        if(null != s.getModel()){
                            row.createCell(j).setCellValue(s.getModel());
                        }
                        break;
                    case 3:
                        if(null != s.getColor()){
                            row.createCell(j).setCellValue(s.getColor());
                        }
                        break;
                    case 4:
                        if(null != s.getVersion()){
                            row.createCell(j).setCellValue(s.getVersion());
                        }
                        break;
                    case 5:
                        if(null != s.getMemory()){
                            row.createCell(j).setCellValue(s.getMemory());
                        }
                        break;
                    case 6:
                        if(null != s.getSeller()){
                            row.createCell(j).setCellValue(s.getSeller());
                        }
                        break;
                    case 7:
                        if(null != s.getExpress()){
                            row.createCell(j).setCellValue(s.getExpress());
                        }
                        break;
                    case 8:
                        if(null != s.getPurchasePrice()){
                            row.createCell(j).setCellValue(s.getPurchasePrice().toString());
                        }
                        break;
                    case 9:
                        if(null != s.getPurchaseExpress()){
                            row.createCell(j).setCellValue(s.getPurchaseExpress().toString());
                        }
                        break;
                    case 10:
                        if(null != s.getRepairPrice()){
                            row.createCell(j).setCellValue(s.getRepairPrice().toString());
                        }
                        break;
                    case 11:
                        if(null !=s.getSellPrice()){
                            row.createCell(j).setCellValue(s.getSellPrice().toString());
                        }
                        break;
                    case 12:
                        if(null != s.getSellExpress()){
                            row.createCell(j).setCellValue(s.getSellExpress().toString());
                        }
                        break;
                    case 13:
                        if(null != s.getProfit()){
                            row.createCell(j).setCellValue(s.getProfit().toString());
                        }
                        break;
                    case 14:
                        if(null != s.getBuyer()){
                            row.createCell(j).setCellValue(s.getBuyer());
                        }
                        break;
                    case 15:
                        if(null != s.getSellNumber()){
                            row.createCell(j).setCellValue(s.getSellNumber());
                        }
                        break;
                    case 16:
                        if(null != s.getState()){
                            row.createCell(j).setCellValue(s.getState());
                        }
                        break;
                    case 17:
                        if(null != s.getRemark()){
                            row.createCell(j).setCellValue(s.getRemark());
                        }
                        break;
                    case 18:
                        if(null != s.getUser()){
                            row.createCell(j).setCellValue(s.getUser());
                        }
                        break;
                    default:
                        break;
                }
            }
            rowCount++;
        }

        try {
            fileOut = new FileOutputStream(file);
            // 写入excel文件
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        try {
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
