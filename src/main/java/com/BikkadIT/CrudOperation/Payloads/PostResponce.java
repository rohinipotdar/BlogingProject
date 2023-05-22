package com.BikkadIT.CrudOperation.Payloads;

import java.util.List;

import com.BikkadIT.CrudOperation.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponce {
	
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalElement;
	private int totalPages;
	private boolean lastPage;

}
