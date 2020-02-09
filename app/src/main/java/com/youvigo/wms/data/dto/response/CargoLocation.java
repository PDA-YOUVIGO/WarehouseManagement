
package com.youvigo.wms.data.dto.response;

public class CargoLocation {

	private String lgnum;
	private String lgtyp;
	private String lgpla;
	private String lgber;
	private boolean zkzue;
	private boolean zkzua;

	public String getLgnum() {
		return lgnum;
	}

	public void setLgnum(String lgnum) {
		this.lgnum = lgnum;
	}

	public String getLgtyp() {
		return lgtyp;
	}

	public void setLgtyp(String lgtyp) {
		this.lgtyp = lgtyp;
	}

	public String getLgpla() {
		return lgpla;
	}

	public void setLgpla(String lgpla) {
		this.lgpla = lgpla;
	}

	public String getLgber() {
		return lgber;
	}

	public void setLgber(String lgber) {
		this.lgber = lgber;
	}

	public boolean isZkzue() {
		return zkzue;
	}

	public void setZkzue(boolean zkzue) {
		this.zkzue = zkzue;
	}

	public boolean isZkzua() {
		return zkzua;
	}

	public void setZkzua(boolean zkzua) {
		this.zkzua = zkzua;
	}

	public CargoLocation(String lgnum, String lgtyp, String lgpla, String lgber, boolean zkzue, boolean zkzua) {
		this.lgnum = lgnum;
		this.lgtyp = lgtyp;
		this.lgpla = lgpla;
		this.lgber = lgber;
		this.zkzue = zkzue;
		this.zkzua = zkzua;
	}

	public CargoLocation() {
	}
}
