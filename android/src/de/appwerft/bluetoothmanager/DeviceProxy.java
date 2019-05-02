package de.appwerft.bluetoothmanager;

package de.appwerft.disto;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;

import android.bluetooth.BluetoothClass.Device;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;


@Kroll.proxy(creatableInModule = BluetoothmanagerModule.class)
public class DeviceProxy extends KrollProxy  {
	BluetoothDevice device;
	String name;
	String address;
	int rssi;
	int type;
	private static String LCAT = BluetoothmanagerModule.LCAT;
	

	public DeviceProxy() {
		super();
	}

	public DeviceProxy(BluetoothDevice device) {
		super();
		this.device = device;

		
	}
	public DeviceProxy(BluetoothDevice device,String name,String address,int rssi,int type) {
		super();
		this.device = device;
		this.name = name;
		this.address = address;
		this.rssi = rssi;
		this.type = type;
	}
	
}