package com.shoukailiang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FocusUser {
    private String id;

    private String username;

    private String nickName;

    private String imageUrl;

    private String mobile;

    private String email;
}
