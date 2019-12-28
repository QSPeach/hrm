package cn.itsource.hrm.controller;

import cn.itsource.hrm.utli.RedisUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtlis redisUtlis;

    @PostMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("value") String value){
        redisUtlis.set(key, value);
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key){
        return redisUtlis.get(key);
    }
}
