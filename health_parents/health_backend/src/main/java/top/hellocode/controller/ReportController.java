package top.hellocode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.Result;
import top.hellocode.service.MemberService;
import top.hellocode.service.ReportService;
import top.hellocode.service.SetmealService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月30日 13:03
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;

    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport() {
        LocalDate now = LocalDate.now();
        LocalDate months = now.plusMonths(-12);
        List<String> list = new ArrayList<>();
        for(int i=0;i<12;i++){
            LocalDate month = months.plusMonths(i);
            list.add(month.format(DateTimeFormatter.ofPattern("yyyy.MM")));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("months",list);
        try {
            List<Integer> memberCount = memberService.findMemberCountByMonth(list);
            map.put("memberCount",memberCount);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
        } catch (Exception e) {
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
//        "setmealNames":["套餐1","套餐2","套餐3"],
//        "setmealCount":[
//        {"name":"套餐1","value":10},
//        {"name":"套餐2","value":30},
//        {"name":"套餐3","value":25}

        Map<String,List> data = new HashMap<>();
        try{
            List<Map<String,Object>> setmealCount = setmealService.findSetmealCount();
            List<String> setmealNames = new ArrayList<>();
            for (Map<String, Object> map : setmealCount) {
                String name = (String) map.get("name");
                setmealNames.add(name);
            }
            data.put("setmealCount",setmealCount);
            data.put("setmealNames",setmealNames);
            return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map<String, Object> result = reportService.getBusinessReport();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response){
        try{
            //远程调用报表服务获取报表数据
            Map<String, Object> result = reportService.getBusinessReport();

            //取出返回结果数据，准备将报表数据写入到Excel文件中
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            //获得Excel模板文件绝对路径
            String temlateRealPath = request.getSession().getServletContext().getRealPath("template") +
                    File.separator + "report_template.xlsx";

            //读取模板文件创建Excel表格对象
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(temlateRealPath)));
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDate);//日期

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int rowNum = 12;
            for(Map map : hotSetmeal){//热门套餐
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum ++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmeal_count);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }

            //通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            workbook.write(out);

            out.close();
            workbook.close();
            return null;
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport4PDF")
    public Result exportBusinessReport4PDF(HttpServletRequest request, HttpServletResponse response){
        try{
            //远程调用报表服务获取报表数据
            Map<String, Object> result = reportService.getBusinessReport();

            // 取出返回结果数据，准备将报表数据写入到PDF文件中
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            //动态获取模板文件绝对磁盘路径
            String jrxmlPath =
                    request.getSession().getServletContext().
                            getRealPath("template") + File.separator + "health_business.jrxml";
            String jasperPath =
                    request.getSession().getServletContext().
                            getRealPath("template") + File.separator + "health_business.jasper";

            // 编译模板
            JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

            //填充数据---使用JavaBean数据源方式填充
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperPath, result, new JRBeanCollectionDataSource(hotSetmeal));

            //通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setHeader("content-Disposition", "attachment;filename=report.pdf");

            //输出文件
            JasperExportManager.exportReportToPdfStream(jasperPrint,out);
            out.close();

            return null;
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
