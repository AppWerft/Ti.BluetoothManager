# Ti.BluetootManager


## Methods

### isAvailable() : boolean

### getAvailability() : int

* NOTAVAILABLE
* DISABLEED\BY\_AIRPLANE_MODE
* DISABLED
* ENBLED

### enable()

If Bluetooth is available, but disabled, you can enable. 

For getting the result the result there are 3 ways:

#### Callback as parameter properties.

```js
import BT FROM 'de.appwerft.bluetoothmanager';
BT.enable({
	onsucccess : e => {
		console.log(e);
	},
	onerror : e => {
		console.log(e);
	}
})
```

#### Callback as module property

```js 
import BT FROM 'de.appwerft.bluetoothmanager';
BT.enable();
BT.onsuccess = e => {
	console.log(e);
}
```

#### Module event

```js 
import BT FROM 'de.appwerft.bluetoothmanager';
BT.addEventListener('onsuccess',e => {
	console.log(e);
});
BT.addEventListener('onerror',e => {
	console.log(e);
});
BT.enable();
```

### getBoundedDevices()


## BluetoothLE

This methods are in submodule `BluetoothLE`.

### Sample usage
```
const BLE = require('de.appwerft.bluetoothmanager').BLE;
if (BLE.isBleSupported()) {
	BLE.startScan({
		scanmode : BLE.SCAN_MODE_BALANCED,
		onfound : function(e) {
			console.log(e.device);
			console.log(e.name);
			console.log(e.address);
			console.log(e.rssi);
			console.log(e.type);
		},
		onstatechanged : function(e) {
		},
		onerror : function(e) {
		}
	});
}
```

### ScanFilter
Criteria for filtering result from Bluetooth LE scans. A ScanFilter allows clients to restrict scan results to only those that are of interest to them.

Current filtering on the following fields are supported:

* Service UUIDs which identify the bluetooth gatt services running on the device. You find the [list here](https://www.bluetooth.com/specifications/assigned-numbers/16-bit-uuids-for-members/)
* Name of remote Bluetooth LE device.
* Mac address of the remote device.
* Service data which is the data associated with a service.
* Manufacturer specific data which is the data associated with a particular manufacturer.

```js
const BLE = require('de.appwerft.bluetoothmanager').BLE;

const filters = BLE.createScanfilter({
			uuid : ['64929','64934'],
			devicename : "headphone",
			manufactor : ""
});

BLE.startScan({
		scanfilters : filters,
		onfound : function() {
		} 
	});

```