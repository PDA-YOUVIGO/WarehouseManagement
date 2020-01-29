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

public class OutOfStock implements Parcelable {
    // 物料编号
    public String itemNumber;
    // 物料名
    public String materialName;
    // 规格
    public String specification;
    // 基本单
    public String basicOrder;
    // 批号
    public String lotNumber;

    public OutOfStock() {
    }

    protected OutOfStock(Parcel in) {
        itemNumber = in.readString();
        materialName = in.readString();
        specification = in.readString();
        basicOrder = in.readString();
        lotNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemNumber);
        dest.writeString(materialName);
        dest.writeString(specification);
        dest.writeString(basicOrder);
        dest.writeString(lotNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutOfStock> CREATOR = new Creator<OutOfStock>() {
        @Override
        public OutOfStock createFromParcel(Parcel in) {
            return new OutOfStock(in);
        }

        @Override
        public OutOfStock[] newArray(int size) {
            return new OutOfStock[size];
        }
    };
}
