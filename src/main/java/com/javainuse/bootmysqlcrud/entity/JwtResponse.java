package com.javainuse.bootmysqlcrud.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = -8091879091924046844L;

	private String jwttoken;
}