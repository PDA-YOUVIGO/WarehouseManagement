package com.youvigo.wms.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 物料凭证
 */
public class MaterialVoucher implements Parcelable {

    /**
     * 单据编号
     */
    public String orderNumber;

    /**
     * 单据日期
     */
    public String date;

    /**
     * 创建者
     */
    public String creator;

    /**
     * 供应商名称（来源单位）
     */
    public String supplierName;

    // 预留
    public String moveType;
    public String innerOrder;
    public String innerOrderDescription;
    public String employerCode;
    public String employerName;
    public String departmentCode;
    public String departmentName;
    public String memo;

    // 入库上架、成品上架搜索结果
    public List<Shelving> shelvings;
    
    // 出库下架搜索结果
    public List<TakeOff> takeOffs;

    public List<ReservedOutBound> reservedOutBounds;

    public MaterialVoucher() {
    }

    @NonNull
    @Override
    public String toString() {
        return "orderNumber:" + orderNumber + ",date:" + date + ",creator:" + creator;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNumber);
        dest.writeString(this.date);
        dest.writeString(this.creator);
        dest.writeString(this.supplierName);
        dest.writeString(this.moveType);
        dest.writeString(this.innerOrder);
        dest.writeString(this.innerOrderDescription);
        dest.writeString(this.employerCode);
        dest.writeString(this.employerName);
        dest.writeString(this.departmentCode);
        dest.writeString(this.departmentName);
        dest.writeString(this.memo);
        dest.writeTypedList(this.shelvings);
        dest.writeTypedList(this.takeOffs);
        dest.writeTypedList(this.reservedOutBounds);
    }

    protected MaterialVoucher(Parcel in) {
        this.orderNumber = in.readString();
        this.date = in.readString();
        this.creator = in.readString();
        this.supplierName = in.readString();
        this.moveType = in.readString();
        this.innerOrder = in.readString();
        this.innerOrderDescription = in.readString();
        this.employerCode = in.readString();
        this.employerName = in.readString();
        this.departmentCode = in.readString();
        this.departmentName = in.readString();
        this.memo = in.readString();
        this.shelvings = in.createTypedArrayList(Shelving.CREATOR);
        this.takeOffs = in.createTypedArrayList(TakeOff.CREATOR);
        this.reservedOutBounds = in.createTypedArrayList(ReservedOutBound.CREATOR);
    }

    public static final Creator<MaterialVoucher> CREATOR = new Creator<MaterialVoucher>() {
        @Override
        public MaterialVoucher createFromParcel(Parcel source) {return new MaterialVoucher(source);}

        @Override
        public MaterialVoucher[] newArray(int size) {return new MaterialVoucher[size];}
    };
}
