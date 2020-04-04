package com.f.zxgk.sit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PubliccreditchinagovcnTest {

    @Autowired
    Publiccreditchinagovcn publiccreditchinagovcn;
    @Test
    void read() throws IOException {
        publiccreditchinagovcn.read();
    }
}