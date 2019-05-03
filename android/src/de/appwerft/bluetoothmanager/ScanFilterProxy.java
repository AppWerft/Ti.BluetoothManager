package de.appwerft.bluetoothmanager;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;

@Kroll.proxy(creatableInModule = BLEModule.class)
public class ScanFilterProxy extends KrollProxy {
	BluetoothDevice device;
	String name;
	String address;
	int rssi;
	int type;
	ScanFilter scanFilter ;
	
	 
	private static String LCAT = BLEModule.LCAT;

	public ScanFilterProxy() {
		super();
	}

	public ScanFilterProxy(BluetoothDevice device) {
		super();
		this.device = device;
	}

	@Kroll.method
	public ScanFilterProxy addUUID(String uuid) {
		scanFilter = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(uuid)).build();
		return this;
	}
	
	
	@Override
	public void handleCreationDict(KrollDict opts) {
		 List<ScanFilter> filters = new ArrayList<>();
		
		if (opts.containsKeyAndNotNull("uuid")) {
			ScanFilter.Builder builder = new ScanFilter.Builder();
			scanFilter = builder.setServiceUuid(ParcelUuid.fromString(opts.getString("uuid"))).build();
			filters.add(scanFilter);
		}
		if (opts.containsKeyAndNotNull("devicename")) {
			ScanFilter.Builder builder = new ScanFilter.Builder();
			scanFilter = builder.setDeviceName(opts.getString("devicename")).build();
			filters.add(scanFilter);
		}
		if (opts.containsKeyAndNotNull("deviceaddress")) {
			ScanFilter.Builder builder = new ScanFilter.Builder();
			scanFilter = builder.setDeviceAddress(opts.getString("deviceaddress")).build();
			filters.add(scanFilter);
		}
		if (opts.containsKeyAndNotNull("manufacturer")) {
			ScanFilter.Builder builder = new ScanFilter.Builder();
			KrollDict manufacturer = opts.getKrollDict("manufacturer");
			Object data = manufacturer.get("data");
			scanFilter = builder.setManufacturerData(manufacturer.getInt("id"),(byte[]) data).build();
			filters.add(scanFilter);
		}
		super.handleCreationDict(opts);
	}

}