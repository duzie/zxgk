package com.f.zxgk.sit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PubliccreditchinagovcnTest {

    @Autowired
    Publiccreditchinagovcn publiccreditchinagovcn;

    @Test
    void read() throws IOException {
       String  url = "https://public.creditchina.gov.cn/private-api/catalogSearchPerson?searchState=2&page=1&pageSize=10000&tableName=credit_fgw_zrr_ffjz_jb&scenes=defaultscenario";
        publiccreditchinagovcn.read(url);
    }
}