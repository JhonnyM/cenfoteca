package com.cenfotec.cenfoteca.contracts;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;

public class RentRequest extends BaseRequest {
	
	private AlquilerPOJO alquiler;
	
	public RentRequest() {
		super();
	}
	
	public AlquilerPOJO getAlquiler() {
		return alquiler;
	}
	
	public void setAlquiler(AlquilerPOJO alquiler) {
		this.alquiler = alquiler;
	}

	@Override
	public String toString() {
		return "RentRequest [alquiler=" + alquiler + "]";
	}
	
}
