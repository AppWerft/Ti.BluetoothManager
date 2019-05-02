package de.appwerft.bluetoothmanager;

/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.TiApplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;

@Kroll.module(parentModule = BluetoothmanagerModule.class)
public class BluetoothLEModule extends BluetoothmanagerModule {

	// Standard Debugging variables
	private static final String LCAT = BluetoothmanagerModule.LCAT;

	private static Context ctx;
	private static BluetoothLeScanner scanner;
	private static BluetoothManager bluetoothManager = null;

	private static BluetoothAdapter adapter;
	private static Object scanCallback = null;

	private static boolean isBleSupported = false;
	private static boolean isLollipopApi = (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH);

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public BluetoothLEModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		ctx = app.getApplicationContext();
		isBleSupported = ctx.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
		if (isBleSupported) {
			initialize();
		} else {
			Log.e(LCAT, "No Bluetooth low energy support ???");
		}

		// put module init code that needs to run when the application is created
	}

	@Kroll.method
	public boolean isBleSupported() {
		return isBleSupported;
	}

	@Kroll.method
	public boolean startScan() {
		return startDiscoveringBle();
	}
	@Kroll.method
	public boolean stopScan() {
		if (isBleSupported){
			return cancelDiscoveringBle();
		} else {
			Log.e(LCAT, "stopScan() : No Bluetooth low energy support ???");
			return false;
		}
	}
	private static boolean initialize() {

		/*
		 * For API level 18 and above, get a reference to BluetoothAdapter through
		 * BluetoothManager.
		 */
		if (null == bluetoothManager) {
			bluetoothManager = (BluetoothManager) ctx.getSystemService(Context.BLUETOOTH_SERVICE);

			if (bluetoothManager == null) {
				Log.e(LCAT, "initialize() : Unable to initialize BluetoothManager.");
				return false;
			}
		}
		adapter = bluetoothManager.getAdapter();

		if (adapter == null) {
			Log.e(LCAT, "initialize() : Unable to obtain a BluetoothAdapter.");
			return false;
		}

		if (isLollipopApi) {
			scanCallback = new ScanCallback() {

				@Override
				public void onScanFailed(int errorCode) {
					Log.e(LCAT, "onScanFailed() : Error = " + errorCode);
				}

				@Override
				public void onScanResult(int callbackType, ScanResult result) {

					if (null != result) {
						BluetoothDevice device = result.getDevice();

						if (null != device) {
							onDeviceFound(device, device.getName(), device.getAddress(), result.getRssi(),
									device.getType());
						} else
							Log.e(LCAT, "onScanResult() : Cannot get BluetoothDevice !!!");
					} else {
						Log.e(LCAT, "onScanResult() : Cannot get ScanResult !!!");
					}
				}
			};
		}

		return true;
	}

	private static void onDeviceFound(BluetoothDevice device, String name, String address, int rssi, int type) {

	}

	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
			// onDeviceFound(device, device.getName(), device.getAddress(), rssi,
			// device.getType());
		}

	};

	private boolean startDiscoveringBle() {

		Log.d(LCAT, "+++ startDiscoveringBle() +++");

		if (adapter.isDiscovering()) {
			Log.i(LCAT, "startDiscoveringBle() : Already in classic discovering mode");
			return true;
		}

		if (isLollipopApi) {
			Log.i(LCAT, "startDiscoveringBle() : Choose startScan()");
			scanner = adapter.getBluetoothLeScanner();

			if (null != scanner) {
				((BluetoothLeScanner) scanner).startScan((ScanCallback) scanCallback);
				return true;
			}

			// TODO
			// return mAdapter.startScan(mScanCallback); ???
		}
		return true;
	}

	private final BroadcastReceiver mBR = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			String action = intent.getAction();

				Log.d(LCAT, "onReceive() : Action = " + action);

			if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
				int prevState = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE, BluetoothAdapter.ERROR);
				int newState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

				if (BluetoothAdapter.STATE_ON == newState) {
					
					startScan();
				} else if (BluetoothAdapter.STATE_OFF == newState) {
					
				}
			} else if (action.equals(BluetoothDevice.ACTION_FOUND)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
				onDeviceFound(device, device.getName(), device.getAddress(), rssi, device.getType());
			} else {
				Log.e(LCAT, "onReceive() : Unknown msg : " + action);
			}
		}

	};
@SuppressWarnings("deprecation")
private boolean cancelDiscoveringBle(){
		
		Log.d(LCAT, "+++ cancelDiscoveringBle() +++");
		
		if (adapter.isDiscovering()){
			Log.i(LCAT, "cancelDiscoveringBle() : In classic discovering mode");
			return false;
		}
		
		if (isLollipopApi){
			Log.i(LCAT, "cancelDiscoveringBle() : Choose stopScan()");
			
			if (null != scanner){
				((BluetoothLeScanner)scanner).stopScan((ScanCallback)scanCallback);
				return true;
			}
			
			// TODO
			// return mAdapter.stopScan(mScanCallback); ???
		} else {
			Log.i(LCAT, "cancelDiscoveringBle() : Choose stopLeScan()");
			adapter.stopLeScan(mLeScanCallback);
		}
		return true;
	}
	
	
protected void register(){
		
		Log.d(LCAT, "+++ register +++");
		
		if (isBleSupported){
			IntentFilter filter = new IntentFilter();
		    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		    filter.addAction(BluetoothDevice.ACTION_FOUND);
		    ctx.registerReceiver(mBR, filter);
		} else {
			Log.e(LCAT, "register() : No Bluetooth low energy support ???");
		}
	}
	
	
	protected void unregister(){
		
		Log.d(LCAT, "+++ register +++");
		
		if (isBleSupported){
			ctx.unregisterReceiver(mBR);
		} else {
			Log.e(LCAT, "unregister() : No Bluetooth low energy support ???");
		}		
	}

}
// https://recordnotfound.com/BLE-MIDI-for-Android-kshoji-80185