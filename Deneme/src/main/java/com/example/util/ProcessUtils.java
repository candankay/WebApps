package com.example.util;

import org.json.JSONException;
import org.json.JSONObject;

public class ProcessUtils {

		public static final String URL_LOGIN ="https://testreportingapi.clearsettle.com/api/v3/merchant/user/login";
		public static final String URL_LIST = "https://testreportingapi.clearsettle.com/api/v3/transaction/list";
		public static final String URL_TRANSACTION="​https://testreportingapi.clearsettle.com/api/v3/transaction";
		public static final String URL_REPORT="https://testreportingapi.clearsettle.com/api/v3/transactions/report";
		public static final String URL_CLIENT="https://testreportingapi.clearsettle.com/api/v3/client";
		public static final String URL_MERCHANT="https://testreportingapi.clearsettle.com/api/v3/merchant";

	
	class Status{
		public static final String APPROVED ="APPROVED";
		public static final String WAITING ="WAITING";
		public static final String DECLINED ="DECLINED";
		public static final String ERROR ="ERROR";
	}
	
	class Operation{
		public static final String DIRECT ="DIRECT";
		public static final String REFUND ="REFUND";
		public static final String _3D ="3D";
		public static final String _3DAUTH ="3DAUTH";
		public static final String STORED ="STORED";
	}
	
	class Payment{
		public static final String CREDITCARD ="CREDITCARD";
		public static final String CUP ="CUP";
		public static final String IDEAL ="IDEAL";
		public static final String MISTERCASH ="MISTERCASH";
		public static final String STORED ="STORED";
		public static final String PAYTOCARD ="PAYTOCARD";
	}

	class ErrorCode{
		int DO_NOT_HONOR=0;
		int INVALID_TRANSACTION=1;
		int INVALID_CARD=2;
		int NO_SUFFICENT_FUND=3;
		int INCORRECT_PIN=4;
		int INVALID_COUNTRY_ASSOCIATION=5;
		int CURRENCY_NOT_ALLOWED=6;
		int SECURE_TRANSPORT_ERROR=7;
		int TRANSACTION_NOT_PERMITTED=8;
	}
	String MsjError[] = {"Do not honor", "Invalid Transaction", "Invalid Card", "Not sufficient funds", "Incorrect PIN", 
	        		"Invalid country association", "Currency not allowed", "3-D Secure Transport Error", 
	        		"Transaction not permitted to cardholder"};
	
	class FilterField{
		int TRANSACTION_UUID=0;
		int CUSTOMER_EMAIL=1;
		int REFERENCE_NO=2;
		int CUSTOM_DATA=3;
		int CARD_PAN=4;
	}
	String MsjFilter[] = {"Transaction UUID", "Customer Email", "Reference No", "Custom Data",  "Card PAN" };

	public static JSONObject returnError(String errorDsc){
		try {
			return new JSONObject("{error : '"+errorDsc+"'}");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
