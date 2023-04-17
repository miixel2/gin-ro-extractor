package com.miixel2.gin.lua.convertor;

import static org.junit.jupiter.api.Assertions.*;

class JsonConvertorTest {
    public String luaTestFile;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        luaTestFile = JsonConvertor.class.getClassLoader().getResource("testfile.lua").getFile();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getJson() {
        String luaJson = JsonConvertor.getJson(luaTestFile, "tbl");
        System.out.println("GetJson Test: " + luaJson);
    }
}