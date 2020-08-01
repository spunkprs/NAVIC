package arcesium;

public class Solution {

	public static void main(String[] args) {
		
	}
	
	class MarketingResult {
		private int totalRecords;
		private MarketingValue data[];
		public int getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}
		public MarketingValue[] getData() {
			return data;
		}
		public void setData(MarketingValue[] data) {
			this.data = data;
		}
	}
	
	class HoldingResult {
		private int totalRecords;
		private HoldingValue data[];
		
		public int getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}
		public HoldingValue[] getData() {
			return data;
		}
		public void setData(HoldingValue[] data) {
			this.data = data;
		}
		
	} 
	
	class MarketingValue {
		private String date;
	    private String security;
	    private Double price;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getSecurity() {
			return security;
		}
		public void setSecurity(String security) {
			this.security = security;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
	}
	
	class HoldingValue {
	    private String date;
	    private String security;
	    private int quantity;
	    private String portfolio;
	    
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getSecurity() {
			return security;
		}
		public void setSecurity(String security) {
			this.security = security;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getPortfolio() {
			return portfolio;
		}
		public void setPortfolio(String portfolio) {
			this.portfolio = portfolio;
		}
	    
	}

}
