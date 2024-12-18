# DeviceInfoSDK

An Android SDK for collecting device information including contacts, SMS messages, and call logs.

## Features

- Contact list retrieval
- SMS message access
- Call log collection
- Automatic permission handling
- Data serialization and remote logging

## Installation

Add the following dependency to your app's `build.gradle`:

```gradle
dependencies {
    implementation 'com.example:deviceinfosdk:1.0.0'
}
```

## Required Permissions

The SDK requires the following permissions in your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.READ_SMS" />
<uses-permission android:name="android.permission.READ_CALL_LOG" />
<uses-permission android:name="android.permission.INTERNET" />
```

## Usage

### Initialization

Initialize the SDK in your activity:

```java
if (DeviceInfoSDK.initialize(activity)) {
    // Permissions granted, proceed with data collection
} else {
    // Handle permission denial
}
```

### Setting Server URL

Before logging data, set the server URL where data will be sent:

```java
DataLogger.setServerUrl("https://your-server.com/api/log");
```

### Data Collection

Collect specific data types:

```java
// Get contacts
List<String> contacts = DeviceInfoSDK.getContacts(context);

// Get messages
List<String> messages = DeviceInfoSDK.getMessages(context);

// Get call logs
List<String> callLogs = DeviceInfoSDK.getCallLogs(context);
```

### Logging Data

Log all collected data to the server:

```java
DeviceInfoSDK.logData(context);
```

## Security Considerations

- All collected data is sensitive personal information. Ensure proper user consent is obtained.
- Implement secure data transmission using HTTPS.
- Handle and store collected data in accordance with privacy regulations (GDPR, CCPA, etc.).
- Consider implementing data encryption before transmission.

## Error Handling

The SDK includes basic error handling:
- Permission denial handling
- Network error handling for data logging
- Null safety checks for data collection

## Contributing

Please submit issues and pull requests for any improvements.

## License

TBD