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

    // 入库上架、成品上架搜索结果
    public List<Shelving> shelvings;
    
    // 出库下架搜索结果
    public List<TakeOff> takeOffs;

    public MaterialVoucher() {
    }

    protected MaterialVoucher(Parcel in) {
        orderNumber = in.readString();
        date = in.readString();
        creator = in.readString();
        supplierName = in.readString();
        shelvings = in.createTypedArrayList(Shelving.CREATOR);
        takeOffs = in.createTypedArrayList(TakeOff.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNumber);
        dest.writeString(date);
        dest.writeString(creator);
        dest.writeString(supplierName);
        dest.writeTypedList(shelvings);
        dest.writeTypedList(takeOffs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MaterialVoucher> CREATOR = new Creator<MaterialVoucher>() {
        @Override
        public MaterialVoucher createFromParcel(Parcel in) {
            return new MaterialVoucher(in);
        }

        @Override
        public MaterialVoucher[] newArray(int size) {
            return new MaterialVoucher[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "orderNumber:" + orderNumber + ",date:" + date + ",creator:" + creator;
    }
}
