package com.xxxx.yeb.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Excel表格工具类
 *
 * @author zoult on 2019/5/20
 */
public class ReadExcelUtil {

    private Workbook workbook;

    public ReadExcelUtil (Workbook workbook) {
        this.workbook = workbook;
    }

    public ReadExcelUtil (File file) throws FileNotFoundException, IOException {
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            if (file.getName().endsWith(".xls")) {
                // Excel2003及以前版本
                workbook = new HSSFWorkbook(is);
            } else {
                // Excel2007及以后版本
                workbook = new XSSFWorkbook(is);
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public ReadExcelUtil (InputStream is, String fileName) throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(is);
    }

    /**
     * 读取Excel第一页所有数据
     *
     * @return
     * @throws Exception
     */
    public List <List <String>> read () throws Exception {
        return read(0, 0, getRowCount(0));
    }

    /**
     * 读取Excel指定页所有数据
     *
     * @param sheetIx
     * @return
     * @throws Exception
     */
    public List <List <String>> read (Integer sheetIx) throws Exception {
        return read(sheetIx, 0, getRowCount(0));
    }

    /**
     * 写入数据到Excel，指定sheet页名称
     * 新创建sheet页，不覆盖
     *
     * @param rowList
     * @param sheetName
     * @return
     * @throws IOException
     */
    public Boolean write (List <List <String>> rowList, String sheetName) throws IOException {
        Sheet sheet = workbook.createSheet(sheetName);
        int sheetIx = workbook.getSheetIndex(sheet);
        Boolean success = write(sheetIx, rowList, 0, true);
        return success;
    }

    /**
     * 追加数据至Excel，指定sheet页
     *
     * @param sheetIx
     * @param rowData
     * @return
     * @throws IOException
     */
    public Boolean writeAppend (Integer sheetIx, List <List <String>> rowData) throws IOException {
        return write(sheetIx, rowData, getRowCount(sheetIx), true);
    }

    /**
     * 将Excel的内容写入指定文件
     *
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void flush (File file) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(file);
        try {
            workbook.write(os);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 将Excel的内容写入指定文件
     *
     * @param os
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void flush (OutputStream os) throws FileNotFoundException, IOException {
        try {
            workbook.write(os);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 获取指定sheet页行数
     *
     * @param sheetIx
     * @return
     */
    private Integer getRowCount (int sheetIx) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        if (sheet.getPhysicalNumberOfRows() == 0) {
            return 0;
        }
        return sheet.getLastRowNum() + 1;
    }

    /**
     * 读取指定sheet页指定行数据
     *
     * @param sheetIx 指定sheet页，从0开始
     * @param start   指定开始行，从0开始
     * @param end     指定结束行，从0开始
     * @return
     * @throws Exception
     */
    private List <List <String>> read (int sheetIx, int start, int end) throws Exception {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        List <List <String>> rowList = new ArrayList <>();

        if (end > getRowCount(sheetIx)) {
            end = getRowCount(sheetIx);
        }
        // 第一行总列数
        int colNum = sheet.getRow(0).getLastCellNum();
        for (int i = start; i < end; i++) {
            List <String> colList = new ArrayList <>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++) {
                if (row == null) {
                    colList.add(null);
                    continue;
                }
                colList.add(getCellValue(row.getCell(j)));
            }
            rowList.add(colList);
        }
        return rowList;
    }

    /**
     * 数据写入
     *
     * @param sheetIx
     * @param rowList
     * @param startRow
     * @return
     * @throws IOException
     */
    private Boolean write (int sheetIx, List <List <String>> rowList, int startRow,
                           Boolean autoSize) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        int rowSize = rowList.size();
        // 如果小于等于0，则一行都不存在
        if (getRowCount(sheetIx) > 0) {
            sheet.shiftRows(startRow, getRowCount(sheetIx), rowSize);
        }
        for (int i = 0; i < rowSize; i++) {
            Row row = sheet.createRow(i + startRow);
            for (int j = 0; j < rowList.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(Optional.ofNullable(rowList.get(i).get(j)).orElse(""));
            }
        }
        if (autoSize && CollectionUtils.isNotEmpty(rowList)) {
            for (int i = 0; i < rowList.get(0).size(); i++) {
                sheet.autoSizeColumn(i);
            }
        }
        return true;
    }

    /**
     * 单元格内容解析
     *
     * @param cell
     * @return
     */
    private String getCellValue (Cell cell) {
        String cellValue = null;
        switch (cell.getCellTypeEnum()) {
            // 数字
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            // 字符串
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            // Boolean
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            // 空值
            case BLANK:
                cellValue = null;
                break;
            // 错误
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
