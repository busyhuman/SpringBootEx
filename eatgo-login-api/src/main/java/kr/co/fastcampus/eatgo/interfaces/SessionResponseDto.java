package kr.co.fastcampus.eatgo.interfaces;
// DTO는 순수하게 데이터만 가지고 있다.

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {

    private String accessToken;
}
