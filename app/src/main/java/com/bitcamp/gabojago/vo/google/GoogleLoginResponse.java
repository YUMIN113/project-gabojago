package com.bitcamp.gabojago.vo.google;

// 일회성 토큰을 통해 얻은 Response VO
// idToken을 전달해 사용자 정보를 얻을 예정

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleLoginResponse {

  private String accessToken; // 애플리케이션이 Google API 요청을 승인하기 위해 보내는 토큰
  private String expiresIn;   // Access Token의 남은 수명
  private String refreshToken;    // 새 액세스 토큰을 얻는 데 사용할 수 있는 토큰
  private String scope;
  private String tokenType;   // 반환된 토큰 유형(Bearer 고정)
  private String idToken;

  @Override
  public String toString() {
    return "GoogleOAuthResponse{" +
        "accessToken='" + accessToken + '\'' +
        ", expiresIn='" + expiresIn + '\'' +
        ", refreshToken='" + refreshToken + '\'' +
        ", scope='" + scope + '\'' +
        ", tokenType='" + tokenType + '\'' +
        ", idToken='" + idToken + '\'' +
        '}';
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(String expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getIdToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }
}
