package com.unbosque.info.util;

import com.unbosque.info.util.GeneraClave;

public class CifrarClave {

	public String cifradoClave(String claveTextoPlano) {
		String claveTextoCifrado = GeneraClave.getStringMessageDigest(
				claveTextoPlano, GeneraClave.MD5);

		return claveTextoCifrado;

	}
}
