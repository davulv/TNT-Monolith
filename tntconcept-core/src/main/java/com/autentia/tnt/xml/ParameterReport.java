/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.xml;


public class ParameterReport {

	private String name;
	private String description;
	private String defaultValue;
	
	public ParameterReport(){
		
	}
	
	public ParameterReport(String name, String description, String defaultValue) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Report Details - ");
		sb.append("Name:" + getName());
		sb.append(", ");
		sb.append("Description:" + getDescription());
		sb.append(", ");
		sb.append("Default Value:" + getDefaultValue());
		sb.append(".");
		
		return sb.toString();
	}

}
