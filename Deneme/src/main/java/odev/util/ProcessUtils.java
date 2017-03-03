package odev.util;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import odev.model.Acquirer;
import odev.model.CustomerBilling;
import odev.model.CustomerClient;
import odev.model.CustomerPerson;
import odev.model.CustomerShipping;
import odev.model.Merchant;
import odev.model.list.ListData;
import odev.model.list.ListFx;
import odev.model.list.ListFxMerchant;
import odev.model.list.ListIpn;
import odev.model.list.ListIpnMerchant;
import odev.model.list.ListMerchant;
import odev.model.list.ListTransaction;
import odev.model.list.ListTransactionMerchant;

public class ProcessUtils {

	public static final String URL_LOGIN ="https://testreportingapi.clearsettle.com/api/v3/merchant/user/login";
	public static final String URL_LIST = "https://testreportingapi.clearsettle.com/api/v3/transaction/list";
	public static final String URL_TRANSACTION="​https://testreportingapi.clearsettle.com/api/v3/transaction";
	public static final String URL_REPORT="https://testreportingapi.clearsettle.com/api/v3/transactions/report";
	public static final String URL_CLIENT="https://testreportingapi.clearsettle.com/api/v3/client";
	public static final String URL_MERCHANT="https://testreportingapi.clearsettle.com/api/v3/merchant";

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
