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

import com.google.gson.annotations.SerializedName;
import com.youvigo.wms.data.dto.base.Additional;

public class InternalOrder implements Parcelable {

    // 订单号
    @SerializedName("AUFNR")
    private String number;

    // 描述
    @SerializedName("KTEXT")
    private String description;

    // 工厂
    @SerializedName("WERKS")
    private String factory;

    private Additional additional;

    protected InternalOrder(Parcel in) {
        number = in.readString();
        description = in.readString();
        factory = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(description);
        dest.writeString(factory);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InternalOrder> CREATOR = new Creator<InternalOrder>() {
        @Override
        public InternalOrder createFromParcel(Parcel in) {
            return new InternalOrder(in);
        }

        @Override
        public InternalOrder[] newArray(int size) {
            return new InternalOrder[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Additional getAdditional() {
        return additional;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }
}
