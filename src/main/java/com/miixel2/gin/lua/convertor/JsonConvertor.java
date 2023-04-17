package com.miixel2.gin.lua.convertor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonConvertor {

    public static String getJson(String luaFile, String rootElement) {
        // Create a Lua interpreter
        Globals globals = JsePlatform.standardGlobals();
        // Load the Lua file
        globals.loadfile(luaFile).call();

        // Get the tbl data
        LuaValue luaTbl = globals.get(rootElement);

        Map<Integer, Map<String, Object>> resultMap = new HashMap<>();

        LuaValue key = LuaValue.NIL;
        while (true) {
            Varargs nextKey = luaTbl.next(key);
            if ((key = nextKey.arg1()).isnil()) {
                break;
            }

            int numericKey = key.checkint();
            Map<String, Object> innerMap = new HashMap<>();
            LuaValue luaInnerTable = luaTbl.get(key);

            LuaValue innerKey = LuaValue.NIL;
            while (true) {
                Varargs innerN = luaInnerTable.next(innerKey);
                if ((innerKey = innerN.arg1()).isnil()) {
                    break;
                }

                String innerMapKey = innerKey.checkjstring();
                LuaValue innerValue = luaInnerTable.get(innerKey);
                if (innerValue.isstring()) {
                    innerMap.put(innerMapKey, innerValue.tojstring());
                } else if (innerValue.isnumber()) {
                    innerMap.put(innerMapKey, innerValue.toint());
                }
            }

            resultMap.put(numericKey, innerMap);
        }
        // Convert the Lua table to a Java object
        Gson gson = new GsonBuilder().create();
        return gson.toJson(resultMap);
    }
}
