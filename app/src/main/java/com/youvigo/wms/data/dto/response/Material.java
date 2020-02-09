package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 物料档案
 */
public class Material {

	// 物料编码
	@SerializedName("MATNR")
	private String matnr;

	// 物料描述
	@SerializedName("MAKTX")
	private String maktx;

	// 物料描述（长文本）
	@SerializedName("MAKTXLT")
	private String maktxlt;

	// 物料类型
	@SerializedName("MTART")
	private String mtart;

	// 物料类型描述
	@SerializedName("MTBEZ")
	private String mtbez;

	// 物料组
	@SerializedName("MATKL")
	private String matkl;

	// 物料组描述
	@SerializedName("WGBEZ")
	private String wgbez;

	// 旧物料号
	@SerializedName("BISMT")
	private String bismt;

	// 删除标记
	@SerializedName("LVORM")
	private boolean lvorm;

	// 基本计量单位
	@SerializedName("MEINS")
	private String meins;

	// 基本计量单位描述
	@SerializedName("MSEHT")
	private String mseht;

	// 存储条件
	@SerializedName("RAUBE")
	private String raube;

	// 存储条件描述
	@SerializedName("RBTXT")
	private String rbtxt;

	// 近效期天数
	@SerializedName("MHDRZ")
	private double mhdrz;

	// 总效期天数
	@SerializedName("MHDHB")
	private double mhdhb;

	// 日期标识
	@SerializedName("IPRKZ")
	private String iprkz;

	// 大小/量纲
	@SerializedName("GROES")
	private String groes;

	// 毛重（重量）
	@SerializedName("BRGEW")
	private double brgew;

	// 净重
	@SerializedName("NTGEW")
	private double ntgew;

	// 重量单位
	@SerializedName("GEWEI")
	private String gewei;

	// 业务量 （体积）
	@SerializedName("VOLUM")
	private double volum;

	// 体积单位
	@SerializedName("VOLEH")
	private String voleh;

	// 产品组
	@SerializedName("SPART")
	private String spart;

	// 通用名称
	@SerializedName("ZZCOMMONNAME")
	private String zzcommonname;

	// 规格
	@SerializedName("ZZDRUGSPEC")
	private String zzdrugspec;

	// 包装规格
	@SerializedName("ZZPACKINGSPEC")
	private String zzpackingspec;

	// 商品名
	@SerializedName("ZZMERCHNAME")
	private String zzmerchname;

	// 瓶装量
	@SerializedName("ZZBOTTLED")
	private String zzbottled;

	// 瓶装量单位
	@SerializedName("ZZBOTTLEDUNIT")
	private String zzbottledunit;

	// 内包装规格
	@SerializedName("ZZINNERPACK")
	private String zzinnerpack;

	// 内包装计量单位
	@SerializedName("ZZINNERPACKUNIT")
	private String zzinnerpackunit;

	// 外包装规格
	@SerializedName("ZZOUTERPACK")
	private String zzouterpack;

	// 外包装计量单位
	@SerializedName("ZZOUTERPACKUNIT")
	private String zzouterpackunit;

	// 质量标准
	@SerializedName("ZZDRUGSTANDARD")
	private String zzdrugstandard;

	// 货号
	@SerializedName("ZZPROCODE")
	private String zzprocode;

	// 型号
	@SerializedName("ZZPROMODEL")
	private String zzpromodel;

	// 性状
	@SerializedName("ZZCHARACTER")
	private String zzcharacter;

	// 备用字段
	@SerializedName("ADDITIONAL1")
	private String additional1;
	@SerializedName("ADDITIONAL2")
	private String additional2;
	@SerializedName("ADDITIONAL3")
	private String additional3;
	@SerializedName("ADDITIONAL4")
	private String additional4;
	@SerializedName("ADDITIONAL5")
	private String additional5;

	// 辅计量单位
	@SerializedName("Unit_TAB")
	private List<MaterialUnit> units;

	/**
	 * 物料分类
	 */
	@SerializedName("Classification")
	private MaterialCategory category;

	// 物料特性
	@SerializedName("Characteristic_TAB")
	private List<MaterialFeature> features;

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getMaktx() {
		return maktx;
	}

	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}

	public String getMaktxlt() {
		return maktxlt;
	}

	public void setMaktxlt(String maktxlt) {
		this.maktxlt = maktxlt;
	}

	public String getMtart() {
		return mtart;
	}

	public void setMtart(String mtart) {
		this.mtart = mtart;
	}

	public String getMtbez() {
		return mtbez;
	}

	public void setMtbez(String mtbez) {
		this.mtbez = mtbez;
	}

	public String getMatkl() {
		return matkl;
	}

	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}

	public String getWgbez() {
		return wgbez;
	}

	public void setWgbez(String wgbez) {
		this.wgbez = wgbez;
	}

	public String getBismt() {
		return bismt;
	}

	public void setBismt(String bismt) {
		this.bismt = bismt;
	}

	public boolean isLvorm() {
		return lvorm;
	}

	public void setLvorm(boolean lvorm) {
		this.lvorm = lvorm;
	}

	public String getMeins() {
		return meins;
	}

	public void setMeins(String meins) {
		this.meins = meins;
	}

	public String getMseht() {
		return mseht;
	}

	public void setMseht(String mseht) {
		this.mseht = mseht;
	}

	public String getRaube() {
		return raube;
	}

	public void setRaube(String raube) {
		this.raube = raube;
	}

	public String getRbtxt() {
		return rbtxt;
	}

	public void setRbtxt(String rbtxt) {
		this.rbtxt = rbtxt;
	}

	public double getMhdrz() {
		return mhdrz;
	}

	public void setMhdrz(double mhdrz) {
		this.mhdrz = mhdrz;
	}

	public double getMhdhb() {
		return mhdhb;
	}

	public void setMhdhb(double mhdhb) {
		this.mhdhb = mhdhb;
	}

	public String getIprkz() {
		return iprkz;
	}

	public void setIprkz(String iprkz) {
		this.iprkz = iprkz;
	}

	public String getGroes() {
		return groes;
	}

	public void setGroes(String groes) {
		this.groes = groes;
	}

	public double getBrgew() {
		return brgew;
	}

	public void setBrgew(double brgew) {
		this.brgew = brgew;
	}

	public double getNtgew() {
		return ntgew;
	}

	public void setNtgew(double ntgew) {
		this.ntgew = ntgew;
	}

	public String getGewei() {
		return gewei;
	}

	public void setGewei(String gewei) {
		this.gewei = gewei;
	}

	public double getVolum() {
		return volum;
	}

	public void setVolum(double volum) {
		this.volum = volum;
	}

	public String getVoleh() {
		return voleh;
	}

	public void setVoleh(String voleh) {
		this.voleh = voleh;
	}

	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public String getZzcommonname() {
		return zzcommonname;
	}

	public void setZzcommonname(String zzcommonname) {
		this.zzcommonname = zzcommonname;
	}

	public String getZzdrugspec() {
		return zzdrugspec;
	}

	public void setZzdrugspec(String zzdrugspec) {
		this.zzdrugspec = zzdrugspec;
	}

	public String getZzpackingspec() {
		return zzpackingspec;
	}

	public void setZzpackingspec(String zzpackingspec) {
		this.zzpackingspec = zzpackingspec;
	}

	public String getZzmerchname() {
		return zzmerchname;
	}

	public void setZzmerchname(String zzmerchname) {
		this.zzmerchname = zzmerchname;
	}

	public String getZzbottled() {
		return zzbottled;
	}

	public void setZzbottled(String zzbottled) {
		this.zzbottled = zzbottled;
	}

	public String getZzbottledunit() {
		return zzbottledunit;
	}

	public void setZzbottledunit(String zzbottledunit) {
		this.zzbottledunit = zzbottledunit;
	}

	public String getZzinnerpack() {
		return zzinnerpack;
	}

	public void setZzinnerpack(String zzinnerpack) {
		this.zzinnerpack = zzinnerpack;
	}

	public String getZzinnerpackunit() {
		return zzinnerpackunit;
	}

	public void setZzinnerpackunit(String zzinnerpackunit) {
		this.zzinnerpackunit = zzinnerpackunit;
	}

	public String getZzouterpack() {
		return zzouterpack;
	}

	public void setZzouterpack(String zzouterpack) {
		this.zzouterpack = zzouterpack;
	}

	public String getZzouterpackunit() {
		return zzouterpackunit;
	}

	public void setZzouterpackunit(String zzouterpackunit) {
		this.zzouterpackunit = zzouterpackunit;
	}

	public String getZzdrugstandard() {
		return zzdrugstandard;
	}

	public void setZzdrugstandard(String zzdrugstandard) {
		this.zzdrugstandard = zzdrugstandard;
	}

	public String getZzprocode() {
		return zzprocode;
	}

	public void setZzprocode(String zzprocode) {
		this.zzprocode = zzprocode;
	}

	public String getZzpromodel() {
		return zzpromodel;
	}

	public void setZzpromodel(String zzpromodel) {
		this.zzpromodel = zzpromodel;
	}

	public String getZzcharacter() {
		return zzcharacter;
	}

	public void setZzcharacter(String zzcharacter) {
		this.zzcharacter = zzcharacter;
	}

	public String getAdditional1() {
		return additional1;
	}

	public void setAdditional1(String additional1) {
		this.additional1 = additional1;
	}

	public String getAdditional2() {
		return additional2;
	}

	public void setAdditional2(String additional2) {
		this.additional2 = additional2;
	}

	public String getAdditional3() {
		return additional3;
	}

	public void setAdditional3(String additional3) {
		this.additional3 = additional3;
	}

	public String getAdditional4() {
		return additional4;
	}

	public void setAdditional4(String additional4) {
		this.additional4 = additional4;
	}

	public String getAdditional5() {
		return additional5;
	}

	public void setAdditional5(String additional5) {
		this.additional5 = additional5;
	}

	public List<MaterialUnit> getUnits() {
		return units;
	}

	public void setUnits(List<MaterialUnit> units) {
		this.units = units;
	}

	public MaterialCategory getCategory() {
		return category;
	}

	public void setCategory(MaterialCategory category) {
		this.category = category;
	}

	public List<MaterialFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<MaterialFeature> features) {
		this.features = features;
	}

	public Material(String matnr, String maktx, String maktxlt, String mtart, String mtbez, String matkl, String wgbez, String bismt, boolean lvorm, String meins, String mseht, String raube, String rbtxt, double mhdrz, double mhdhb, String iprkz, String groes, double brgew, double ntgew, String gewei, double volum, String voleh, String spart, String zzcommonname, String zzdrugspec, String zzpackingspec, String zzmerchname, String zzbottled, String zzbottledunit, String zzinnerpack, String zzinnerpackunit, String zzouterpack, String zzouterpackunit, String zzdrugstandard, String zzprocode, String zzpromodel, String zzcharacter, String additional1, String additional2, String additional3, String additional4, String additional5, List<MaterialUnit> units, MaterialCategory category, List<MaterialFeature> features) {
		this.matnr = matnr;
		this.maktx = maktx;
		this.maktxlt = maktxlt;
		this.mtart = mtart;
		this.mtbez = mtbez;
		this.matkl = matkl;
		this.wgbez = wgbez;
		this.bismt = bismt;
		this.lvorm = lvorm;
		this.meins = meins;
		this.mseht = mseht;
		this.raube = raube;
		this.rbtxt = rbtxt;
		this.mhdrz = mhdrz;
		this.mhdhb = mhdhb;
		this.iprkz = iprkz;
		this.groes = groes;
		this.brgew = brgew;
		this.ntgew = ntgew;
		this.gewei = gewei;
		this.volum = volum;
		this.voleh = voleh;
		this.spart = spart;
		this.zzcommonname = zzcommonname;
		this.zzdrugspec = zzdrugspec;
		this.zzpackingspec = zzpackingspec;
		this.zzmerchname = zzmerchname;
		this.zzbottled = zzbottled;
		this.zzbottledunit = zzbottledunit;
		this.zzinnerpack = zzinnerpack;
		this.zzinnerpackunit = zzinnerpackunit;
		this.zzouterpack = zzouterpack;
		this.zzouterpackunit = zzouterpackunit;
		this.zzdrugstandard = zzdrugstandard;
		this.zzprocode = zzprocode;
		this.zzpromodel = zzpromodel;
		this.zzcharacter = zzcharacter;
		this.additional1 = additional1;
		this.additional2 = additional2;
		this.additional3 = additional3;
		this.additional4 = additional4;
		this.additional5 = additional5;
		this.units = units;
		this.category = category;
		this.features = features;
	}

	public Material() {
	}
}
