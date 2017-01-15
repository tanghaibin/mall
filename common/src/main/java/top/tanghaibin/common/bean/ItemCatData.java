package top.tanghaibin.common.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCatData  {
	
	@JsonProperty("u") // 序列化成json数据时为 u
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> items;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNname() {
		return name;
	}

	public void setNname(String nname) {
		this.name = nname;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}
	
	

}
