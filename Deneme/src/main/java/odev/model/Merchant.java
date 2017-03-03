package odev.model;

public class Merchant {

	private int id;
	private int parentId;
	private String name;
	private String _3dStatus;
	private String mcc;
	private String ipnUrl;
	private String apiKey;
	private String cpgKey;
	private String type;
	private String descriptor;
	private String secretKey;
	private String comType;
	private String createDate;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_3dStatus() {
		return _3dStatus;
	}
	public void set_3dStatus(String _3dStatus) {
		this._3dStatus = _3dStatus;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getIpnUrl() {
		return ipnUrl;
	}
	public void setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getCpgKey() {
		return cpgKey;
	}
	public void setCpgKey(String cpgKey) {
		this.cpgKey = cpgKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescriptor() {
		return descriptor;
	}
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	
}