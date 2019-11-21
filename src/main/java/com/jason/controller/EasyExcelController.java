package com.jason.controller;

import com.alibaba.excel.EasyExcel;
import com.jason.entity.ConsumeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description: easyexcel导出导入控制器
 *
 *导出【10000】条数据耗时【201】毫秒
 * 导出【50000】条数据耗时【1638】毫秒
 * 导出【100000】条数据耗时【2631】毫秒。【注意：】阿里的easyExcel导出超过6万条不会新建sheet表格
 * 导出【500000】条数据耗时【11902】毫秒
 *
 * @author :  Raymond
 * @date :  2019-11-21 17:53
 */
@Controller
public class EasyExcelController {
    Logger logger = LoggerFactory.getLogger(EasyExcelController.class);

    /**
     * 导出消费记录
     * @param response
     */
    @RequestMapping("exportConsume")
    public void export(HttpServletResponse response) {
        Long start = System.currentTimeMillis();
        //模拟从数据库获取需要导出的数据
        List<ConsumeVo> consumeVoList = new ArrayList<>();
        ConsumeVo consumeVo;
        int total = 100000;
        for (int i = 0; i < total; i++) {
            consumeVo = new ConsumeVo();
            consumeVo.setAccessType(new Byte("2"));
            consumeVo.setBatchNo("1000" + i);
            consumeVo.setAmount(new BigDecimal(10 + i));
            consumeVo.setProductName("商品" + i);
            consumeVo.setMerchantName("商户" + i);
            consumeVoList.add(consumeVo);
        }

        //导出操作
        OutputStream outputStream = null;
        try {
            List<ConsumeVo> list = consumeVoList;
            String fileName = UUID.randomUUID().toString().replace("-", "");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, ConsumeVo.class).sheet("表格明细").doWrite(list);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("导出【{}】条数据耗时【{}】毫秒", total, System.currentTimeMillis() - start);
    }
}
