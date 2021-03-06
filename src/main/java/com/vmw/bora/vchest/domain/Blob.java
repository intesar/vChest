package com.vmw.bora.vchest.domain;

import java.nio.ByteBuffer;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("binary_content")
public class Blob {

	@PrimaryKey
	private String id;
	
	private ByteBuffer content;

	public ByteBuffer getBlob() {
		return content;
	}

	public void setBlob(ByteBuffer blob) {
		this.content = blob;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
