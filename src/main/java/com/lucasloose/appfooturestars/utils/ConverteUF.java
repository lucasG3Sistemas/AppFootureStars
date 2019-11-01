package com.lucasloose.appfooturestars.utils;

import org.springframework.stereotype.Service;


@Service
public class ConverteUF {
	
	public String converteCodToUF(Integer cod) {
		String uf = "";	
		switch(cod) {
			case 11:
				uf = "RO";
				break;
			case 12:
				uf = "AC";
				break;
			case 13:
				uf = "AM";
				break;
			case 14:
				uf = "RR";
				break;
			case 15:
				uf = "PA";
				break;
			case 16:
				uf = "AP";
				break;
			case 17:
				uf = "TO";
				break;
			case 21:
				uf = "MA";
				break;
			case 22:
				uf = "PI";
				break;
			case 23:
				uf = "CE";
				break;
			case 24:
				uf = "RN";
				break;
			case 25:
				uf = "PB";
				break;
			case 26:
				uf = "PE";
				break;
			case 27:
				uf = "AL";
				break;
			case 28:
				uf = "SE";
				break;
			case 29:
				uf = "BA";
				break;
			case 31:
				uf = "MG";
				break;
			case 32:
				uf = "ES";
				break;
			case 33:
				uf = "RJ";
				break;
			case 35:
				uf = "SP";
				break;
			case 41:
				uf = "PR";
				break;
			case 42:
				uf = "SC";
				break;
			case 43:
				uf = "RS";
				break;
			case 50:
				uf = "MS";
				break;
			case 51:
				uf = "MT";
				break;
			case 52:
				uf = "GO";
				break;
			case 53:
				uf = "DF";
				break;
			default: 
				break;
		}
		return uf;
	}
	
}