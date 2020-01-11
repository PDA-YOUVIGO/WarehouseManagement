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

package com.koma.pdaassistant.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Shelving implements Parcelable {
    // 物料编号
    public String itemNumber;
    // 通用名称
    public String commonName;
    // 批号
    public String lotNumber;

    public Shelving() {
    }

    protected Shelving(Parcel in) {
        itemNumber = in.readString();
        commonName = in.readString();
        lotNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemNumber);
        dest.writeString(commonName);
        dest.writeString(lotNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shelving> CREATOR = new Creator<Shelving>() {
        @Override
        public Shelving createFromParcel(Parcel in) {
            return new Shelving(in);
        }

        @Override
        public Shelving[] newArray(int size) {
            return new Shelving[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "shelving:itemNumber:" + itemNumber + ",commonName:" + commonName
                + ",lotNumber:" + lotNumber;
    }
}
