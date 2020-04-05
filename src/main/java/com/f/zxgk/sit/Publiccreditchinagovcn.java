package com.f.zxgk.sit;

import com.f.zxgk.po.Zxgk;
import com.f.zxgk.service.ZxgkService;
import com.f.zxgk.util.Http;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.creditchina.gov.cn/gerenxinyong/personsearch/index.html?tablename=credit_zgf_zrr_sxbzxr&gsName=%E5%A4%B1%E4%BF%A1%E8%A2%AB%E6%89%A7%E8%A1%8C%E4%BA%BA%E5%90%8D%E5%8D%95%E6%9F%A5%E8%AF%A2
 */
@Slf4j
@RestController
@RequestMapping("site/Publiccreditchinagovcn")
public class Publiccreditchinagovcn {

    @Autowired
    private ZxgkService zxgkService;

    @Autowired
    private ObjectMapper om;

    @GetMapping
    @Scheduled(cron = "0 0 */5 * * ?")
    public void read() throws IOException {
        //
        String url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=10000&tableName=credit_zgf_zrr_sxbzxr_jb&scenes=defaultscenario";
        read(url, "失信被执行人");
        //
        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=10000&tableName=credit_fgw_zrr_ffjz_jb&scenes=defaultscenario";
        read(url, "涉金融领域非法集资名单");
        //
        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=10000&tableName=credit_fgw_zrr_qtyzwf_jb&scenes=defaultscenario";
        read(url, "涉金融领域其他严重违法名单");
        //
        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?scenes=defaultscenario&tableName=credit_hmd_zrr_xzhc_jb&searchState=2&page=1&pageSize=10000";
        read(url, "限制乘坐火车名单公示");
        //
        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?scenes=defaultscenario&tableName=credit_hmd_zrr_xzfj_jb&searchState=2&page=1&pageSize=10000";
        read(url, "限制乘坐民用航空器名单");
        //
        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=10000&tableName=credit_wjw_zrr_ynhmd_jb&scenes=defaultscenario";
        read(url, "严重危害正常医疗秩序黑名单查询");

        url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=20&tableName=credit_jtysb_zrr_yzwfcxczyx_jb&scenes=scenario1";
        read(url, "严重违法超限超载运输失信当事人");
        zxgkService.deleteRe();
    }

    public void read(String url, String remark) throws IOException {
        String s = Request.get(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.34 Safari/537.36")
                .execute(Http.NO_TRUESTED_CLIENT).returnContent().asString();
        JsonNode jsonNode = om.readTree(s);
        String s1 = jsonNode.get("data").get("list").toString();
        List<ResponseData> responseData = om.readValue(s1, new TypeReference<List<ResponseData>>() {
        });
        List<Zxgk> list = new ArrayList<>();
        for (ResponseData responseDatum : responseData) {
            Zxgk zxgk = new Zxgk();
            zxgk.setName(responseDatum.getXm());
            zxgk.setIdnumber(responseDatum.getTmzjhm());
            zxgk.setRemark(remark);
            zxgk.setClazz(this.getClass().getName());
            list.add(zxgk);
        }
        zxgkService.saveBatch(list);
    }

}

@Data
class ResponseData {
    private String lrbm;
    private String lrsj;
    private String tmzjhm;
    private String xm;

}

