package com.example.util;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.model.Acquirer;
import com.example.model.CustomerBilling;
import com.example.model.CustomerClient;
import com.example.model.CustomerPerson;
import com.example.model.CustomerShipping;
import com.example.model.Merchant;
import com.example.model.list.ListData;
import com.example.model.list.ListFx;
import com.example.model.list.ListFxMerchant;
import com.example.model.list.ListIpn;
import com.example.model.list.ListIpnMerchant;
import com.example.model.list.ListMerchant;
import com.example.model.list.ListTransaction;
import com.example.model.list.ListTransactionMerchant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static CustomerClient createClientDataFromJSON(JSONObject obj){

		ObjectMapper mapper = new ObjectMapper().configure(
			    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CustomerClient cObj = null;
		CustomerShipping sObj = null;
		CustomerBilling bObj = null;
		try {
			String mr = obj.get("customerInfo").toString();
			sObj = mapper.readValue(mr.getBytes() , CustomerShipping.class);
			bObj = mapper.readValue(mr.getBytes() , CustomerBilling.class);
			cObj = mapper.readValue(mr.getBytes() , CustomerClient.class);
			cObj.setBillPerson(sObj);
			cObj.setShipPerson(bObj);
			return cObj;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Merchant createMerchantDataFromJSON(JSONObject obj){

		ObjectMapper mapper = new ObjectMapper().configure(
			    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Merchant mrObj = null;
		
		String mr;
		try {
			mr = obj.get("merchant").toString();
			mrObj = mapper.readValue(mr.getBytes() , Merchant.class);
			return mrObj;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ListData createListDataFromJSON(JSONObject obj){
		ObjectMapper mapper = new ObjectMapper().configure(
			    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ListFx listFxObj=null;
		ListIpn listIpnObj=null;
		CustomerPerson customerInfoObj=null;
		ListMerchant merchantObj=null;
		Acquirer acquirerObj=null;
		ListTransaction transactionObj =null;
        try {
        	if(obj.has("fx")){
            	String fx = obj.get("fx").toString();
            	String fxMerchant = (new JSONObject(fx)).get("merchant").toString();
            	ListFxMerchant listFxMerchantObj = mapper.readValue(fxMerchant.getBytes() , ListFxMerchant.class);
            	listFxObj = new ListFx(listFxMerchantObj);
        	}
        	if(obj.has("ipn")){
            	String ipn = obj.get("ipn").toString();
            	String ipnMerchant = (new JSONObject(ipn)).get("merchant").toString();
            	ListIpnMerchant listIpnMerchantObj = mapper.readValue(ipnMerchant.getBytes() , ListIpnMerchant.class);
            	listIpnObj =  mapper.readValue(ipn.getBytes() , ListIpn.class);
            	listIpnObj.setMerchant(listIpnMerchantObj);
        	}
        	if(obj.has("customerInfo")){
        		String customerInfo = obj.get("customerInfo").toString();
            	customerInfoObj = mapper.readValue(customerInfo.getBytes() , CustomerPerson.class);
        	}
        	if(obj.has("merchant")){
        		String merchant = obj.get("merchant").toString();
            	merchantObj = mapper.readValue(merchant.getBytes() , ListMerchant.class);
        	}
        	if(obj.has("acquirer")){
        		String acquirer = obj.get("acquirer").toString();
            	acquirerObj = mapper.readValue(acquirer.getBytes() , Acquirer.class);
        	}
        	if(obj.has("transaction")){
            	String transaction = obj.get("transaction").toString();
            	String trMerchant = (new JSONObject(transaction)).get("merchant").toString();
            	ListTransactionMerchant trMerchantObj = mapper.readValue(trMerchant.getBytes() ,
            			ListTransactionMerchant.class);
            	transactionObj = new ListTransaction(trMerchantObj);
        	}
        	
        	ListData listData = new ListData();
        	listData.setCreated_at(obj.get("created_at").toString());
        	listData.setUpdated_at(obj.get("updated_at").toString());
        	listData.setAcquirer(acquirerObj);
        	listData.setTransaction(transactionObj);
        	listData.setCustomerInfo(customerInfoObj);
        	listData.setFx(listFxObj);
        	listData.setIpn(listIpnObj);
        	listData.setMerchant(merchantObj);
        	listData.setTransaction(transactionObj);
        	return listData;
        	
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
