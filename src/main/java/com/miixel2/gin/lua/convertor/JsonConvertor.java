package com.miixel2.gin.lua.convertor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class JsonConvertor {
    public static void main(String[] args) {
        // Create a Lua interpreter
        LuaValue lua = JsePlatform.standardGlobals();

        // Load the Lua code into the interpreter
        lua.get("dofile").call(LuaValue.valueOf("E:\\Work\\Workspace\\MiiXel2\\GinReab\\gin-lua-extractor\\src\\test\\testfile.lua"));
        System.out.println(lua.tojstring());
        // Extract the Lua table from the interpreter
        LuaValue tbl = lua.get("tbl");
        System.out.println(tbl.tojstring());
        // Convert the Lua table to a Java object
        Gson gson = new GsonBuilder().create();
        Object obj = gson.fromJson(tbl.tojstring(), Object.class);

        // Convert the Java object to a JSON string
        String json = gson.toJson(obj);

        System.out.println(json);
    }
}
