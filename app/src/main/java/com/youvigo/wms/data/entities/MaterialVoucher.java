/*
 * Copyright (c) 2020. komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youvigo.wms.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class MaterialVoucher implements Parcelable {

    // 物料编号
    public String materialDocument;
    // 日期
    public String date;
    // 创建者
    public String creator;
    // 来源单位
    public String sourceUnit;

    // 入库上架、成品上架搜索结果
//    @SerializedName("ITEM")
    public List<Shelving> shelvings;
    
    // 出库下架搜索结果
    public List<TakeOff> takeOffs;

    public MaterialVoucher() {
    }

    protected MaterialVoucher(Parcel in) {
        materialDocument = in.readString();
        date = in.readString();
        creator = in.readString();
        sourceUnit = in.readString();
        shelvings = in.createTypedArrayList(Shelving.CREATOR);
        takeOffs = in.createTypedArrayList(TakeOff.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(materialDocument);
        dest.writeString(date);
        dest.writeString(creator);
        dest.writeString(sourceUnit);
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
        return "materialDocument:" + materialDocument + ",date:" + date + ",creator:" + creator;
    }
}
