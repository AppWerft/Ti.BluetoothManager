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

```
const BLE = require('de.appwerft.bluetoothmanager').BLE;
if (BLE.isBleSupported()) {
	BLE.startScan();
	BLE.onFound = function(e) {
		e.devices.forEach(function(d){
			console.log(d);
		});
		BLE.stopScan();
	}
}
```