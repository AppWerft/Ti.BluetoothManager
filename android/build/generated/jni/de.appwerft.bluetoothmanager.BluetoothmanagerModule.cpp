/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This code is generated, do not edit by hand. **/

#include "de.appwerft.bluetoothmanager.BluetoothmanagerModule.h"

#include "AndroidUtil.h"
#include "JNIUtil.h"
#include "JSException.h"
#include "TypeConverter.h"
#include "V8Util.h"




#include "org.appcelerator.kroll.KrollModule.h"

#define TAG "BluetoothmanagerModule"

using namespace v8;

namespace de {
namespace appwerft {
namespace bluetoothmanager {


Persistent<FunctionTemplate> BluetoothmanagerModule::proxyTemplate;
jclass BluetoothmanagerModule::javaClass = NULL;

BluetoothmanagerModule::BluetoothmanagerModule() : titanium::Proxy()
{
}

void BluetoothmanagerModule::bindProxy(Local<Object> exports, Local<Context> context)
{
	Isolate* isolate = context->GetIsolate();

	Local<FunctionTemplate> pt = getProxyTemplate(isolate);

	v8::TryCatch tryCatch(isolate);
	Local<Function> constructor;
	MaybeLocal<Function> maybeConstructor = pt->GetFunction(context);
	if (!maybeConstructor.ToLocal(&constructor)) {
		titanium::V8Util::fatalException(isolate, tryCatch);
		return;
	}

	Local<String> nameSymbol = NEW_SYMBOL(isolate, "Bluetoothmanager"); // use symbol over string for efficiency
	MaybeLocal<Object> maybeInstance = constructor->NewInstance(context);
	Local<Object> moduleInstance;
	if (!maybeInstance.ToLocal(&moduleInstance)) {
		titanium::V8Util::fatalException(isolate, tryCatch);
		return;
	}
	exports->Set(context, nameSymbol, moduleInstance);
}

void BluetoothmanagerModule::dispose(Isolate* isolate)
{
	LOGD(TAG, "dispose()");
	if (!proxyTemplate.IsEmpty()) {
		proxyTemplate.Reset();
	}

	titanium::KrollModule::dispose(isolate);
}

Local<FunctionTemplate> BluetoothmanagerModule::getProxyTemplate(v8::Isolate* isolate)
{
	Local<Context> context = isolate->GetCurrentContext();
	if (!proxyTemplate.IsEmpty()) {
		return proxyTemplate.Get(isolate);
	}

	LOGD(TAG, "BluetoothmanagerModule::getProxyTemplate()");

	javaClass = titanium::JNIUtil::findClass("de/appwerft/bluetoothmanager/BluetoothmanagerModule");
	EscapableHandleScope scope(isolate);

	// use symbol over string for efficiency
	Local<String> nameSymbol = NEW_SYMBOL(isolate, "Bluetoothmanager");

	Local<FunctionTemplate> t = titanium::Proxy::inheritProxyTemplate(
		isolate,
		titanium::KrollModule::getProxyTemplate(isolate),
		javaClass,
		nameSymbol);

	proxyTemplate.Reset(isolate, t);
	t->Set(titanium::Proxy::inheritSymbol.Get(isolate), FunctionTemplate::New(isolate, titanium::Proxy::inherit<BluetoothmanagerModule>));

	// Method bindings --------------------------------------------------------

	Local<ObjectTemplate> prototypeTemplate = t->PrototypeTemplate();
	Local<ObjectTemplate> instanceTemplate = t->InstanceTemplate();

	// Delegate indexed property get and set to the Java proxy.
	instanceTemplate->SetIndexedPropertyHandler(titanium::Proxy::getIndexedProperty,
		titanium::Proxy::setIndexedProperty);

	// Constants --------------------------------------------------------------

	// Dynamic properties -----------------------------------------------------

	// Accessors --------------------------------------------------------------
	Local<String> onsuccess = NEW_SYMBOL(isolate, "onsuccess");
	instanceTemplate->SetAccessor(
		onsuccess,
		titanium::Proxy::getProperty,
		titanium::Proxy::onPropertyChanged);
	DEFINE_PROTOTYPE_METHOD_DATA(isolate, t, "getOnsuccess", titanium::Proxy::getProperty, onsuccess);
	DEFINE_PROTOTYPE_METHOD_DATA(isolate, t, "setOnsuccess", titanium::Proxy::onPropertyChanged, onsuccess);
	Local<String> onerror = NEW_SYMBOL(isolate, "onerror");
	instanceTemplate->SetAccessor(
		onerror,
		titanium::Proxy::getProperty,
		titanium::Proxy::onPropertyChanged);
	DEFINE_PROTOTYPE_METHOD_DATA(isolate, t, "getOnerror", titanium::Proxy::getProperty, onerror);
	DEFINE_PROTOTYPE_METHOD_DATA(isolate, t, "setOnerror", titanium::Proxy::onPropertyChanged, onerror);

	return scope.Escape(t);
}

Local<FunctionTemplate> BluetoothmanagerModule::getProxyTemplate(v8::Local<v8::Context> context)
{
	return getProxyTemplate(context->GetIsolate());
}

// Methods --------------------------------------------------------------------

// Dynamic property accessors -------------------------------------------------


} // bluetoothmanager
} // appwerft
} // de
