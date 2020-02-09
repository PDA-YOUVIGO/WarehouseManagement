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

package com.youvigo.wms.data.dto.response;

import com.google.gson.annotations.SerializedName;

/**
 * 物料特性
 */
public class MaterialFeature {

	/**
	 * 特性名
	 */
	@SerializedName("CHARACTER")
	private String character;

	/**
	 * 特性名描述
	 */
	@SerializedName("CHARACTER_DESC")
	private String characterDesc;

	/**
	 * 特性值
	 */
	@SerializedName("CHARACTER_VAL")
	private String characterValue;

	/**
	 * 特性值描述
	 */
	@SerializedName("CHARACTER_VAL_DESC")
	private String characterValueDesc;

	/**
	 * 特性单位
	 */
	@SerializedName("CHARACTER_MEINS")
	private String characterMeins;

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getCharacterDesc() {
		return characterDesc;
	}

	public void setCharacterDesc(String characterDesc) {
		this.characterDesc = characterDesc;
	}

	public String getCharacterValue() {
		return characterValue;
	}

	public void setCharacterValue(String characterValue) {
		this.characterValue = characterValue;
	}

	public String getCharacterValueDesc() {
		return characterValueDesc;
	}

	public void setCharacterValueDesc(String characterValueDesc) {
		this.characterValueDesc = characterValueDesc;
	}

	public String getCharacterMeins() {
		return characterMeins;
	}

	public void setCharacterMeins(String characterMeins) {
		this.characterMeins = characterMeins;
	}

	public MaterialFeature(String character, String characterDesc, String characterValue, String characterValueDesc, String characterMeins) {
		this.character = character;
		this.characterDesc = characterDesc;
		this.characterValue = characterValue;
		this.characterValueDesc = characterValueDesc;
		this.characterMeins = characterMeins;
	}

	public MaterialFeature() {
	}
}
