package ua.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
//@NamedQueries({
//	@NamedQuery(name = "findAllCitys", query = "select a from City a"),
//	@NamedQuery(name = "findOneCity", query = "select a from City a.name =:param")
//})


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Назва міста в якому знаходиться готель
@Entity
@Table(name = "city", indexes = @Index(columnList = "name"))
public class City extends AbstractEntity{
	@Column(name = "name")
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private List<HotelName> hotelNames = new ArrayList<>();
	
	private int version;
	@Transient
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public City(String name, List<HotelName> hotelNames) {
		this.name = name;
		this.hotelNames = hotelNames;
	}
	public City(String name) {
		super();
		this.name = name;
	}
	public City() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HotelName> getHotelNames() {
		return hotelNames;
	}
	public void setHotelNames(List<HotelName> hotelNames) {
		this.hotelNames = hotelNames;
	}
	
}
