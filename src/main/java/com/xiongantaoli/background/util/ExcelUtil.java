package com.xiongantaoli.background.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * @Author： LZL
 * @Date： 2019/1/14
 * @Description： This is a class
 */
public class ExcelUtil {
    public static int EXCEL_LOWVERSION = 0; // excel低版本
    public static int EXCEL_HIGHVERSION = 1; // excel高版本
    public static int EXCEL_WRONGFORMAT = 2; // excel格式错误
    public static int EXCEL_WRONGCREATEEXCEL = 3; // excel创建错误
    public static int EXCEL_WRONGIO = 4; // excel io错误
    public static int EXCEL_READSUCCESS = 5; // excel读取成功
    public static int EXCEL_INSERTSUCCESS = 6;

    public static boolean isRowEmpty(Row row) // 判断是否为空行
    {
        boolean flag = true;
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if ((cell != null && cell.getCellTypeEnum() != CellType.BLANK) || cell.getStringCellValue().trim()!="")
            {
                flag = false;
                break;
            }
        }
        return flag;
    }
}