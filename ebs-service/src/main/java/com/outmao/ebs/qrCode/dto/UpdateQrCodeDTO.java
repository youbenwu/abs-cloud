package com.outmao.ebs.qrCode.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data

public class UpdateQrCodeDTO {

	private Long   id;

	//目标URL
	private String url;

	private String business;


}
