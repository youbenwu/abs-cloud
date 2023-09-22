package com.outmao.ebs.security.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUserSession {

	private String token;
	@JsonIgnore
	private Long oauthId;
	@JsonIgnore
	private Long sessionId;
	@JsonIgnore
	private String sessionKey;
	@JsonIgnore
	private LocalDateTime expireTime;

}
