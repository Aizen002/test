package com.wh.chatroom.req;

import lombok.Data;

/**
 * Author: wanghao
 * Date: 2023/10/8 23:51
 * Description:
 */
@Data
public class EnterChatroomReq {

    private Long roomId;

    private String openid;

    private String appid;

    private String deviceid;

    private String account;

    private String nickName;

    private String avatar;


}
