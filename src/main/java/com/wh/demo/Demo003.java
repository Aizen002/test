package com.wh.demo;

import lombok.Builder;
import lombok.Data;

import java.util.*;

/**
 * Author: wanghao
 * Date: 2023/9/29 13:27
 * Description:
 */
public class Demo003 {

    public static void main(String[] args) throws InterruptedException {
        Map<String, List<UserDto>> map = getMap();
        List<UserDto> userDtos = map.get("normal");

        Iterator<UserDto> iterator = userDtos.iterator();
        while (iterator.hasNext()){
            UserDto next = iterator.next();
            System.out.println("移除id:" + next.getId());
            next.setName(next.getName() + "xxxxx");
        }


        for (UserDto userDto : userDtos){

            System.out.println(userDto.getName());


        }





    }

    public static Map<String,List<UserDto>> getMap(){
        Map<String,List<UserDto>> map = new HashMap<>();

        List<UserDto> normalUsers = new ArrayList<>();

        normalUsers.add(UserDto.builder()
                        .id(1L)
                        .type("normal")
                        .name("张三")
                        .age(22)
                        .baseInfo("hahha11111123123")
                .build());

        normalUsers.add(UserDto.builder()
                .id(2L)
                .type("normal")
                .name("李四")
                .age(23)
                .baseInfo("hahha11111123123")
                .build());

        normalUsers.add(UserDto.builder()
                .id(3L)
                .type("normal")
                .name("王五")
                .age(24)
                .baseInfo("hahha11111123123")
                .build());

        normalUsers.add(UserDto.builder()
                .id(4L)
                .type("normal")
                .name("陈留")
                .age(25)
                .baseInfo("hahha11111123123")
                .build());

        List<UserDto> superUsers = new ArrayList<>();
        map.put("normal",normalUsers);
        map.put("super",superUsers);
        return map;
    }

}

@Data
@Builder
class UserDto {

    private Long id;

    private String type;

    private String name;

    private Integer age;

    private String baseInfo;
}
