package com.comrades.api.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error implements Serializable{

	private static final long serialVersionUID = 1897635338170593755L;

	private Date timestamp;
	private String path;
	private String method;
	private Integer status;
	private String message;
	private String requestId;
	private List<String> errors;

}
