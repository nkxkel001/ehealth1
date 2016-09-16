package org.nkxkel001.thesis.ehealth;

public class HealthCheck {

	/**
	 * @param args
	 */
	private String status;
	private String deviceName;
	private Data data;
	private boolean checked;
	
	public HealthCheck(){}
	
	public HealthCheck(Data data){
		this.data = data;
		this.deviceName = data.getDevice();
		
	}
	
	
	
	public String getStatus() {
		return status;
	}

	public boolean isChecked() {
		return checked;
	}

	public String CheckStatus(){
		//check sensor retrieve id..ideally switch sensor id
		
		if (deviceName.equalsIgnoreCase("hxx")){
			
			status = HeartRateCheck(data.getArray());	
			checked = true;
		}
		
		else{
			
			status = "device health check method not available";
			checked = false;
		}
		
		return status;
	}
	
	public String HeartRateCheck(int [] data){
		String stat="normal";
		int total = 0;
		for (int i = 0; i<data.length; i++){
			total += data[i];
		}
		int averageHR = total/data.length;
		
		if (!(averageHR>=60 && averageHR<=100)){
			stat = "abnormal";
			
		}
				
		return stat;
	}
	
	
	
}
