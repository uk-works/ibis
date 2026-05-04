package com.integratedbookinfo.system.shared.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.integratedbookinfo.system.shared.enums.BookSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private String bookId;

	@NotBlank
	@Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "ISBNは10桁または13桁の半角数字で入力してください")
	private String isbn;
	
	@NotBlank
	@Pattern(regexp="\\d{4}", message="Cコードは4桁の半角数字で入力してください")
	private String cCode;
	
	private String cCodeName;
	
	@NotBlank
	@Pattern(regexp = "^\\d{13}$", message = "JANコードは13桁の半角数字で入力してください")
	private String janCode;
	
	private Genre genre;
	
	private Publisher publisher;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String bookTitle;
	
	private BookSize bookSize;
	
	@NotNull
	@Min(1)
	private int listPrice;

	@NotBlank
	@Pattern(regexp = "^\\d{1,2}$", message = "版は最大2桁の半角数字で入力してください")
	private String edition;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private  LocalDate releaseDate;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@JsonIgnore
	private MultipartFile coverImageFile;
	
	private String coverImagePath;
	
	private boolean deletedFlag;
}
