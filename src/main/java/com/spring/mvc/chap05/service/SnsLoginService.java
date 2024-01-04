package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.KakaoUserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnsLoginService {

    private final MemberService memberService;

    // 카카오 로그인 처리
    public void kakaoLogin(Map<String, String> requestParam, HttpSession session) {

        // 인가코드를 가지고 토큰을 발급받는 요청
        String accessToken = getKakaoAccessToken(requestParam);
        log.debug("access_token {}", accessToken);

        // 토큰으로 사용자 정보 가져오기
        KakaoUserResponseDTO dto = getKakaoUserInfo(accessToken);

        // 카카오에서 받은 회원정보로 우리 사이트 회원가입
        String nickname = dto.getProperties().getNickname();

        // 회원 중복확인
        if (!memberService.checkDuplicateValue("account", nickname)) {
            memberService.join(
                    SignUpRequestDTO.builder()
                            .account(nickname)
                            .password("0000")
                            .name(nickname)
                            .email(nickname + "@abc.com")
                            .build(),
                    dto.getProperties().getProfileImage()
            );
        }
        memberService.maintainLoginState(session, nickname);
    }

    // 토큰으로 사용자 정보 요청
    private KakaoUserResponseDTO getKakaoUserInfo(String accessToken) {

        String requestUri = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate template = new RestTemplate();
        ResponseEntity<KakaoUserResponseDTO> responseEntity = template.exchange(
                requestUri,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                KakaoUserResponseDTO.class
        );

        // 응답 정보 꺼내기
        KakaoUserResponseDTO responseJSON = responseEntity.getBody();
        log.debug("user profile : {}", responseJSON);

        assert responseJSON != null;
        responseJSON.getProperties().getNickname();

        return responseJSON;
    }

    // 토큰발급 요청
    private String getKakaoAccessToken(Map<String, String> requestParam) {

        // 요청 URI
        String requsetUri = "https://kauth.kakao.com/oauth/token";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8");

        // 요청 바디에 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", requestParam.get("appkey"));
        params.add("redirect_uri", requestParam.get("redirect"));
        params.add("code", requestParam.get("code"));

        RestTemplate template = new RestTemplate();

        HttpEntity<Object> requestEntity = new HttpEntity<>(params, headers);

        /*
            - RestTemplate객체가 REST API 통신을 위한 API인데(js -> fetch)
            - 서버에 통신을 보내면서 응답을 받을 수 있는 메서드가 exchange임

            param1 : 요청 URL
            param2 : 요청 방식 (get, post, put, patch, delete...)
            param3 : 요청 헤더와 요청 바디정보 - HttpEntity로 포장해서 줘야 함
            param4 : 응답결과(JSON)를 어떤 타입으로 받아낼 것이지 (ex : DTO로 받을건지 Map으로 받을건지)
         */
        ResponseEntity<Map> responseEntity = template.exchange(requsetUri, HttpMethod.POST, requestEntity, Map.class);

        // 응답 데이터에서 JSON 추출
        Map<String, Object> responseJSON = (Map<String, Object>) responseEntity.getBody();
        log.debug("데이터 : {}", responseJSON);

        // access token 추출
        return (String) responseJSON.get("access_token");
    }
}
